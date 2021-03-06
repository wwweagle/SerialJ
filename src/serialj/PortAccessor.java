/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialj;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Xiaoxing
 */
public class PortAccessor {

    private SerialPort serialPort;
    final private DataParser dp;
    final private String portName;
    private UI.LogUpdator updator;
    private Thread dpThread;

    public PortAccessor(String s) {
        this.portName = s;
        dp = new DataParser();
    }

    public boolean setFileToPath(String pathToFile) {
        return dp.setPathToFile(pathToFile);
    }

    public void setUpdater(UI.LogUpdator u) {
        dp.setUpdater(u);
        this.updator = u;
    }

    public void triggerDTR() {
        try {
            serialPort.setDTR(true);
            Thread.sleep(50);
            serialPort.setDTR(false);

        } catch (SerialPortException | InterruptedException ses) {
            System.out.println("Exception during DTR triggered reset," + ses.toString());
        }
    }

    public boolean start() {
        dpThread=new Thread(dp, "data");
        dpThread.start();
        try {
            serialPort = new SerialPort(portName);
            serialPort.openPort();//Open port
//            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.setParams(SerialPort.BAUDRATE_19200, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
            return true;
        } catch (SerialPortException ex) {
            updator.updateString(ex.toString() + "\r\n");
            return false;
        }
    }

    public void stop() {
        int tryCount = 0;
        try {
            while (serialPort.isOpened() && tryCount < 10) {
                serialPort.closePort();
                tryCount++;
                Thread.sleep(50);
            }
            dp.stop();
            dpThread.join();
        } catch (SerialPortException | InterruptedException ex) {
            updator.updateString(ex.toString() + "\r\n");
        }
    }

    public synchronized boolean writeByte(byte b) {
        try {
            return serialPort.writeByte(b);
        } catch (SerialPortException e) {
            updator.updateString(e.toString() + "\r\n");
            return false;
        }
    }

    class SerialPortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            try {
                int[] bytes = serialPort.readIntArray();
                if (bytes != null) {
                    dp.put(bytes);
                }
            } catch (SerialPortException ex) {
                updator.updateString(ex.toString() + "\r\n");
            }

        }
    }

}
