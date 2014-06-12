/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialj;

import java.util.ArrayList;
import java.util.List;
import jssc.SerialPortList;

/**
 *
 * @author Xiaoxing
 */
public class UI extends javax.swing.JFrame {

    String[] portNames;

    /**
     * Creates new form UI
     */
    public UI() {
        portNames = SerialPortList.getPortNames();
        u = new LogUpdator();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboxCOMList = new javax.swing.JComboBox();
        btnRecord = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtFileName = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPerf = new javax.swing.JTextArea();
        txtCurrSta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ZX Serial  V1");

        cboxCOMList.setModel(new javax.swing.DefaultComboBoxModel(portNames));

        btnRecord.setText("|>");
        btnRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordActionPerformed(evt);
            }
        });

        btnStop.setText("[]");
        btnStop.setEnabled(false);
        btnStop.setPreferredSize(new java.awt.Dimension(45, 23));
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        txtLog.setEditable(false);
        txtLog.setColumns(12);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        txtFileName.setColumns(20);
        txtFileName.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtFileName.setLineWrap(true);
        txtFileName.setRows(2);
        txtFileName.setText("I:\\temp1\\temp2\\temp3.ser");
        jScrollPane2.setViewportView(txtFileName);

        txtPerf.setEditable(false);
        txtPerf.setColumns(12);
        txtPerf.setRows(5);
        jScrollPane3.setViewportView(txtPerf);

        txtCurrSta.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(txtCurrSta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cboxCOMList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRecord, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnStop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRecord)
                            .addComponent(cboxCOMList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCurrSta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordActionPerformed
        p = new PortReader(portNames[cboxCOMList.getSelectedIndex()]);
        p.setUpdater(u);
        if (p.setFileToPath(txtFileName.getText())) {
            p.start();
            btnRecord.setEnabled(false);
            txtFileName.setEditable(false);
            btnStop.setEnabled(true);
            this.setTitle(portNames[cboxCOMList.getSelectedIndex()] + " " + "ZX Serial V1");
        }
    }//GEN-LAST:event_btnRecordActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        p.stop();
        btnRecord.setEnabled(true);
        btnStop.setEnabled(false);
        txtFileName.setEditable(true);
        this.setTitle(portNames[cboxCOMList.getSelectedIndex()] + " " + "ZX Serial V1");
    }//GEN-LAST:event_btnStopActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    public class LogUpdator {

        final private List<String> logList;
//        private boolean updating;
        private final List<Integer> performance;
        private final List<Integer> hit;
        private final List<Integer> miss;
        private final List<Integer> falseAlarm;
        private final List<Integer> correctRejection;
        final private String[] hName;
        private int[] currSta;//

        public LogUpdator() {
            logList = new ArrayList<>();
//            updating = true;
            performance = new ArrayList<>();
            hit = new ArrayList<>();
            miss = new ArrayList<>();
            falseAlarm = new ArrayList<>();
            correctRejection = new ArrayList<>();
            hName = eventNames.init();
            currSta = new int[4];//Hit,Miss,False,Reject

        }

        private void updatePerf() {
            String perf = "";
            for (int i = performance.size(); i > 0; i--) {
                perf += "S" + String.format("%2d", i) + ",";
                int idx = i - 1;
                perf += "P" + String.format("%3d", performance.get(idx)) + ",";
                perf += "H" + String.format("%2d", hit.get(idx)) + ",";
                perf += "M" + String.format("%2d", miss.get(idx)) + ",";
                perf += "F" + String.format("%2d", falseAlarm.get(idx)) + ",";
                perf += "C" + String.format("%2d", correctRejection.get(idx)) + "\n";
            }
            txtPerf.setText(perf);
            currSta = new int[4];
        }

        private void updateCurrSta() {
            String currStaStr = "";
            currStaStr += "H" + String.format("%2d", currSta[0]) + "  ";
            currStaStr += "M" + String.format("%2d", currSta[1]) + "  ";
            currStaStr += "F" + String.format("%2d", currSta[2]) + "  ";
            currStaStr += "C" + String.format("%2d", currSta[3]);
            txtCurrSta.setText(currStaStr);
        }

        public void updateEvent(int[] event) {
            updateString(evt2Str(event));
            switch (event[2]) {
                case 50://perf
                    performance.add(event[3]);
                    updatePerf();
                    break;
                case 47://FA
                    falseAlarm.add(event[3]);
                    break;
                case 48://CR
                    correctRejection.add(event[3]);
                    break;
                case 46://Miss
                    miss.add(event[3]);
                    break;
                case 45://hit
                    hit.add(event[3]);
                    break;
                case 4:
                    currSta[2]++;//false
                    updateCurrSta();
                    break;
                case 5:
                    currSta[3]++;//reject
                    updateCurrSta();
                    break;
                case 6:
                    currSta[1]++;//Miss
                    updateCurrSta();
                    break;
                case 7:
                    currSta[0]++;
                    updateCurrSta();
                    break;
            }
        }

        public void updateString(String str) {
            logList.add(str);
            if (logList.size() > 20) {
                for (int i = 0; i < logList.size() - 20; i++) {
                    logList.remove(0);
                }
            }
            String status = "";
            for (int idx = logList.size(); idx > 0; idx--) {
                status = status + logList.get(idx - 1) + "\r\n";
            }
            txtLog.setText(status);
        }

        private String evt2Str(int[] evt) {
            switch (evt[1]) {
                case 0x55:
                    return Integer.toString(evt[0]) + "," + hName[evt[2]] + "," + Integer.toString(evt[3]);
                case 0x00:
                    if (evt[2] == 0x01 && evt[3] == 0x02 && evt[4] == 0x03) {
                        return Integer.toString(evt[0]) + "," + "Manual Reset";
                    }
                default:
                    return "unknown";
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRecord;
    private javax.swing.JButton btnStop;
    private javax.swing.JComboBox cboxCOMList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtCurrSta;
    private javax.swing.JTextArea txtFileName;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextArea txtPerf;
    // End of variables declaration//GEN-END:variables

    final private LogUpdator u;
    private PortReader p;
}
