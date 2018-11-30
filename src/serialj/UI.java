/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialj;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import jssc.SerialPortList;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.Histogram;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

/**
 *
 * @author Xiaoxing
 */
public class UI extends javax.swing.JFrame implements WindowListener {

    final private String[] portNames;
    private LogUpdator u;
    private PortAccessor p;
    private String statusFilePath;
    final private String ver = "ZX Serial2 0.70 @" + getPID();
    private String statusFileParent = "E:\\ZXX\\StatusServer\\";
    private String savePath = "E:\\ZXX\\2018\\";

    private XYChart chart = new XYChart(350, 120);
    private CategoryChart histoChart = new CategoryChart(350, 120);
    final private String dataNameA = "data_A";
    final private String dataNameB = "data_B";
    private LinkedList<Double> ydata_A = new LinkedList<>();
    private LinkedList<Double> ydata_B = new LinkedList<>();
    private LinkedList<Double> hist_A = new LinkedList<>();
    private LinkedList<Double> hist_B = new LinkedList<>();
    private Histogram histo_A = new Histogram(hist_A, 50, 0, 1000);
    private Histogram histo_B = new Histogram(hist_B, 50, 0, 1000);
    final private LinkedBlockingQueue<String> logTxtQue;

    /**
     * Creates new form UI
     */
    public UI() {
        try {
            URL iconUrl = getClass().getResource("/rsrc/icon.png");
            this.setIconImage(ImageIO.read(iconUrl));
            initLogger();
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        portNames = SerialPortList.getPortNames();
        FileLib flib = new FileLib();
        String s = flib.readPath("[BroadcastPath]");
        if (s != null) {
            statusFileParent = s;
        }
        String sp = flib.readPath("[SavePath]");
        if (sp != null) {
            savePath = sp;
        }

        logTxtQue = new LinkedBlockingQueue<>(50);
        ses = new ScheduledThreadPoolExecutor(1);
        u = new LogUpdator();
        chart.getStyler().setPlotMargin(2).setAxisTicksVisible(false)
                .setChartBackgroundColor(Color.white).setLegendVisible(false);
//        chart.addSeries(SerialName, null,ydata,null);
        histoChart.addSeries("histoA", histo_A.getxAxisData(), histo_A.getyAxisData());
        histoChart.addSeries("histoB", histo_B.getxAxisData(), histo_B.getyAxisData());
        histoChart.getStyler().setXAxisDecimalPattern("0").setXAxisLabelRotation(90).setLegendVisible(false).setChartBackgroundColor(Color.white);

        initComponents();

        btnEnableGrp = new JComponent[]{jButton0, jButton1, jButton2, jButton3, jButton4, jButton5,
            jButton6, jButton7, jButton8, jButton9, btnStop, jButtonReset, btnScript};
        btnDisableGrp = new JComponent[]{btnRecord, btnClear,
            btnDate, btnType, btnSlash, btnCOM, btnTemp};
        txtFileName.setEditable(true);
        frmHuman = new FrmHuman();
    }

    private void initLogger() throws IOException {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.FINE);
        Handler fh = new FileHandler("errors.log", true);
        fh.setFilter((LogRecord record) -> record.getLevel().intValue() > 700 || record.getMessage().contains("Exception"));
        fh.setFormatter(new SimpleFormatter());
        rootLogger.addHandler(fh);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        TopPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cboxCOMList = new javax.swing.JComboBox();
        btnRecord = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnTemp = new javax.swing.JButton();
        lblEmpty = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnHumanPnl = new javax.swing.JButton();
        jScrollFilePath = new javax.swing.JScrollPane();
        txtFileName = new javax.swing.JTextArea();
        pnlBottom = new javax.swing.JPanel();
        LBPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnDate = new javax.swing.JButton();
        btnType = new javax.swing.JButton();
        btnSlash = new javax.swing.JButton();
        btnCOM = new javax.swing.JButton();
        scrollPerf = new javax.swing.JScrollPane();
        txtPerf = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCurrPref = new javax.swing.JTextArea();
        jTxtPermText = new javax.swing.JTextField();
        RBPanel = new javax.swing.JPanel();
        pnlNumButton = new javax.swing.JPanel();
        pnlNum = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton0 = new javax.swing.JButton();
        pnlBtn = new javax.swing.JPanel();
        btnScript = new javax.swing.JToggleButton();
        jButtonReset = new javax.swing.JButton();
        pnlLogLCD = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLCD = new javax.swing.JTextArea();
        jScrollTxtLog = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jTxtLickFreq = new javax.swing.JTextField();
        jButtonClearLickFreq = new javax.swing.JButton();
        btnHisto = new javax.swing.JToggleButton();
        pnlChart = new javax.swing.JPanel();
        pnlLineChart = new XChartPanel<XYChart>(chart);
        pnlHisto = new XChartPanel(histoChart);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(ver);
        setMaximumSize(new java.awt.Dimension(512, 2147483647));
        setMinimumSize(new java.awt.Dimension(300, 240));
        setPreferredSize(new java.awt.Dimension(375, 470));
        getContentPane().setLayout(new java.awt.BorderLayout(6, 6));

        TopPanel.setMinimumSize(new java.awt.Dimension(350, 75));
        TopPanel.setPreferredSize(new java.awt.Dimension(350, 75));
        TopPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        cboxCOMList.setModel(new javax.swing.DefaultComboBoxModel(portNames));
        cboxCOMList.setMinimumSize(new java.awt.Dimension(70, 24));
        cboxCOMList.setPreferredSize(new java.awt.Dimension(70, 24));
        cboxCOMList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxCOMListActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(cboxCOMList, gridBagConstraints);

        btnRecord.setText("|>");
        btnRecord.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnRecord.setMaximumSize(new java.awt.Dimension(1000, 24));
        btnRecord.setMinimumSize(new java.awt.Dimension(50, 24));
        btnRecord.setPreferredSize(new java.awt.Dimension(50, 24));
        btnRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(btnRecord, gridBagConstraints);

        btnStop.setText("[]");
        btnStop.setEnabled(false);
        btnStop.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnStop.setMaximumSize(new java.awt.Dimension(1000, 24));
        btnStop.setMinimumSize(new java.awt.Dimension(50, 24));
        btnStop.setPreferredSize(new java.awt.Dimension(50, 24));
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(btnStop, gridBagConstraints);

        btnTemp.setText("TEMP");
        btnTemp.setMargin(new java.awt.Insets(2, 4, 2, 4));
        btnTemp.setMaximumSize(new java.awt.Dimension(1000, 24));
        btnTemp.setMinimumSize(new java.awt.Dimension(50, 24));
        btnTemp.setPreferredSize(new java.awt.Dimension(50, 24));
        btnTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTempActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(btnTemp, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(lblEmpty, gridBagConstraints);

        btnOpen.setText("Open");
        btnOpen.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnOpen.setMaximumSize(new java.awt.Dimension(1000, 24));
        btnOpen.setMinimumSize(new java.awt.Dimension(50, 24));
        btnOpen.setPreferredSize(new java.awt.Dimension(50, 24));
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(btnOpen, gridBagConstraints);

        btnClear.setText("Clear");
        btnClear.setEnabled(false);
        btnClear.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnClear.setMaximumSize(new java.awt.Dimension(1000, 24));
        btnClear.setMinimumSize(new java.awt.Dimension(50, 24));
        btnClear.setPreferredSize(new java.awt.Dimension(50, 24));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(btnClear, gridBagConstraints);

        btnHumanPnl.setText("H");
        btnHumanPnl.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnHumanPnl.setMaximumSize(new java.awt.Dimension(50, 24));
        btnHumanPnl.setMinimumSize(new java.awt.Dimension(50, 24));
        btnHumanPnl.setPreferredSize(new java.awt.Dimension(50, 24));
        btnHumanPnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHumanPnlActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(btnHumanPnl, gridBagConstraints);

        TopPanel.add(jPanel3, java.awt.BorderLayout.NORTH);

        jScrollFilePath.setMinimumSize(new java.awt.Dimension(200, 48));
        jScrollFilePath.setPreferredSize(new java.awt.Dimension(370, 60));

        txtFileName.setColumns(20);
        txtFileName.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtFileName.setLineWrap(true);
        txtFileName.setRows(2);
        txtFileName.setText(savePath);
        txtFileName.setMinimumSize(new java.awt.Dimension(200, 60));
        txtFileName.setPreferredSize(null);
        jScrollFilePath.setViewportView(txtFileName);

        TopPanel.add(jScrollFilePath, java.awt.BorderLayout.CENTER);

        getContentPane().add(TopPanel, java.awt.BorderLayout.NORTH);

        pnlBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        pnlBottom.setPreferredSize(new java.awt.Dimension(389, 600));
        pnlBottom.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        LBPanel.setMinimumSize(new java.awt.Dimension(100, 100));
        LBPanel.setPreferredSize(new java.awt.Dimension(190, 240));
        LBPanel.setLayout(new java.awt.BorderLayout(2, 2));

        jPanel2.setLayout(new java.awt.BorderLayout(2, 2));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnDate.setText("Date");
        btnDate.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnDate.setMaximumSize(new java.awt.Dimension(32767, 32767));
        btnDate.setMinimumSize(new java.awt.Dimension(40, 24));
        btnDate.setPreferredSize(new java.awt.Dimension(50, 24));
        btnDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDateActionPerformed(evt);
            }
        });
        jPanel1.add(btnDate);

        btnType.setText("Type");
        btnType.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnType.setMaximumSize(new java.awt.Dimension(32767, 32767));
        btnType.setMinimumSize(new java.awt.Dimension(40, 24));
        btnType.setPreferredSize(new java.awt.Dimension(50, 24));
        btnType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeActionPerformed(evt);
            }
        });
        jPanel1.add(btnType);

        btnSlash.setText(File.separator);
        btnSlash.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSlash.setMaximumSize(new java.awt.Dimension(32767, 32767));
        btnSlash.setMinimumSize(new java.awt.Dimension(40, 24));
        btnSlash.setPreferredSize(new java.awt.Dimension(50, 24));
        btnSlash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSlashActionPerformed(evt);
            }
        });
        jPanel1.add(btnSlash);

        btnCOM.setText("COM");
        btnCOM.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCOMActionPerformed(evt);
            }
        });
        jPanel1.add(btnCOM);

        jPanel2.add(jPanel1, java.awt.BorderLayout.NORTH);

        scrollPerf.setMinimumSize(new java.awt.Dimension(100, 100));
        scrollPerf.setPreferredSize(new java.awt.Dimension(100, 145));

        txtPerf.setEditable(false);
        txtPerf.setColumns(12);
        txtPerf.setRows(5);
        scrollPerf.setViewportView(txtPerf);

        jPanel2.add(scrollPerf, java.awt.BorderLayout.CENTER);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setMinimumSize(new java.awt.Dimension(100, 64));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(150, 64));

        txtCurrPref.setEditable(false);
        txtCurrPref.setColumns(20);
        txtCurrPref.setRows(2);
        jScrollPane4.setViewportView(txtCurrPref);

        jPanel2.add(jScrollPane4, java.awt.BorderLayout.SOUTH);

        LBPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        jTxtPermText.setEditable(false);
        jTxtPermText.setBackground(new java.awt.Color(255, 255, 255));
        jTxtPermText.setMinimumSize(new java.awt.Dimension(100, 24));
        jTxtPermText.setPreferredSize(new java.awt.Dimension(150, 24));
        jTxtPermText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtPermTextActionPerformed(evt);
            }
        });
        LBPanel.add(jTxtPermText, java.awt.BorderLayout.SOUTH);

        pnlBottom.add(LBPanel);

        RBPanel.setLayout(new java.awt.BorderLayout(0, 3));

        pnlNumButton.setLayout(new java.awt.BorderLayout());

        pnlNum.setLayout(new java.awt.GridLayout(2, 5, 3, 3));

        jButton1.setText("1");
        jButton1.setEnabled(false);
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton1.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton1.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton1);

        jButton2.setText("2");
        jButton2.setEnabled(false);
        jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton2.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton2.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton2.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton2);

        jButton3.setText("3");
        jButton3.setEnabled(false);
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton3.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton3.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton3);

        jButton4.setText("4");
        jButton4.setEnabled(false);
        jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton4.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton4.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton4.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton4);

        jButton5.setText("5");
        jButton5.setEnabled(false);
        jButton5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton5.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton5.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton5.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton5);

        jButton6.setText("6");
        jButton6.setEnabled(false);
        jButton6.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton6.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton6.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton6.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton6);

        jButton7.setText("7");
        jButton7.setEnabled(false);
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton7.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton7.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton7);

        jButton8.setText("8");
        jButton8.setEnabled(false);
        jButton8.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton8.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton8.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton8.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton8);

        jButton9.setText("9");
        jButton9.setEnabled(false);
        jButton9.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton9.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton9.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton9.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton9);

        jButton0.setText("0");
        jButton0.setEnabled(false);
        jButton0.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton0.setMaximumSize(new java.awt.Dimension(15, 24));
        jButton0.setMinimumSize(new java.awt.Dimension(15, 24));
        jButton0.setPreferredSize(new java.awt.Dimension(15, 24));
        jButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton0ActionPerformed(evt);
            }
        });
        pnlNum.add(jButton0);

        pnlNumButton.add(pnlNum, java.awt.BorderLayout.CENTER);

        pnlBtn.setLayout(new java.awt.GridLayout(2, 1, 3, 3));

        btnScript.setText("Script");
        btnScript.setEnabled(false);
        btnScript.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnScript.setMaximumSize(new java.awt.Dimension(32767, 32767));
        btnScript.setMinimumSize(new java.awt.Dimension(40, 24));
        btnScript.setPreferredSize(new java.awt.Dimension(40, 24));
        btnScript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScriptActionPerformed(evt);
            }
        });
        pnlBtn.add(btnScript);

        jButtonReset.setText("RESET");
        jButtonReset.setEnabled(false);
        jButtonReset.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonReset.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButtonReset.setMinimumSize(new java.awt.Dimension(40, 24));
        jButtonReset.setPreferredSize(new java.awt.Dimension(40, 24));
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });
        pnlBtn.add(jButtonReset);

        pnlNumButton.add(pnlBtn, java.awt.BorderLayout.EAST);

        RBPanel.add(pnlNumButton, java.awt.BorderLayout.NORTH);

        pnlLogLCD.setLayout(new java.awt.BorderLayout(0, 3));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(146, 45));

        txtLCD.setColumns(20);
        txtLCD.setRows(5);
        jScrollPane1.setViewportView(txtLCD);

        pnlLogLCD.add(jScrollPane1, java.awt.BorderLayout.SOUTH);

        jScrollTxtLog.setAlignmentX(0.0F);
        jScrollTxtLog.setAlignmentY(0.0F);
        jScrollTxtLog.setMinimumSize(new java.awt.Dimension(100, 200));
        jScrollTxtLog.setPreferredSize(new java.awt.Dimension(100, 200));

        txtLog.setEditable(false);
        txtLog.setColumns(12);
        txtLog.setRows(5);
        jScrollTxtLog.setViewportView(txtLog);

        pnlLogLCD.add(jScrollTxtLog, java.awt.BorderLayout.CENTER);

        RBPanel.add(pnlLogLCD, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.BorderLayout(3, 0));

        jTxtLickFreq.setEditable(false);
        jTxtLickFreq.setBackground(new java.awt.Color(255, 255, 255));
        jTxtLickFreq.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jTxtLickFreq.setMinimumSize(new java.awt.Dimension(100, 24));
        jTxtLickFreq.setPreferredSize(new java.awt.Dimension(135, 24));
        jTxtLickFreq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtLickFreqActionPerformed(evt);
            }
        });
        jPanel4.add(jTxtLickFreq, java.awt.BorderLayout.CENTER);

        jButtonClearLickFreq.setText("C");
        jButtonClearLickFreq.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jButtonClearLickFreq.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButtonClearLickFreq.setMinimumSize(new java.awt.Dimension(40, 24));
        jButtonClearLickFreq.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonClearLickFreq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearLickFreqActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonClearLickFreq, java.awt.BorderLayout.EAST);

        btnHisto.setText("H");
        btnHisto.setMargin(new java.awt.Insets(2, 3, 2, 3));
        btnHisto.setMinimumSize(new java.awt.Dimension(40, 24));
        btnHisto.setPreferredSize(new java.awt.Dimension(24, 24));
        btnHisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoActionPerformed(evt);
            }
        });
        jPanel4.add(btnHisto, java.awt.BorderLayout.WEST);

        RBPanel.add(jPanel4, java.awt.BorderLayout.SOUTH);

        pnlBottom.add(RBPanel);

        getContentPane().add(pnlBottom, java.awt.BorderLayout.CENTER);

        pnlChart.setLayout(new java.awt.CardLayout());

        pnlLineChart.setMinimumSize(new java.awt.Dimension(100, 100));
        pnlLineChart.setPreferredSize(new java.awt.Dimension(350, 120));
        pnlLineChart.setLayout(new java.awt.GridLayout(1, 0));
        pnlChart.add(pnlLineChart, "line");

        pnlHisto.setLayout(new java.awt.GridLayout(1, 0));
        pnlChart.add(pnlHisto, "bar");

        getContentPane().add(pnlChart, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtPermTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtPermTextActionPerformed

    }//GEN-LAST:event_jTxtPermTextActionPerformed

    private void jButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton0ActionPerformed
        p.writeByte((byte) 0x30);
    }//GEN-LAST:event_jButton0ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        p.writeByte((byte) 0x39);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        p.writeByte((byte) 0x38);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        p.writeByte((byte) 0x37);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        p.writeByte((byte) 0x36);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        p.triggerDTR();
        p.writeByte((byte) 0x2a);

    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        p.writeByte((byte) 0x35);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        p.writeByte((byte) 0x34);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        p.writeByte((byte) 0x33);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        p.writeByte((byte) 0x32);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        p.writeByte((byte) 0x31);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSlashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSlashActionPerformed
        txtFileName.append(File.separator);
        txtFileName.requestFocusInWindow();

    }//GEN-LAST:event_btnSlashActionPerformed

    private void btnTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeActionPerformed
        String[] expLists = new FileLib().getExperimentConditions();
        new ExperimentsForm(expLists, txtFileName).setVisible(true);
        txtFileName.requestFocusInWindow();
    }//GEN-LAST:event_btnTypeActionPerformed

    private void btnDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDateActionPerformed
        SimpleDateFormat fmt = new SimpleDateFormat("_yyyy_MM_dd_");
        txtFileName.append(fmt.format(new Date()));
        txtFileName.requestFocusInWindow();
    }//GEN-LAST:event_btnDateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        u = new LogUpdator();
        txtCurrPref.setText("");
        txtLog.setText("");
        txtPerf.setText("");
//        ydata_A.clear();
//        ydata_B.clear();
        if (!chart.getSeriesMap().isEmpty()) {
            Set<String> keyset = new HashSet<>(chart.getSeriesMap().keySet());
            keyset.forEach((key) -> {
                chart.removeSeries(key);
            });
        }
        pnlLineChart.repaint();
        pnlLineChart.revalidate();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        File f = new File(txtFileName.getText());
        if (f.exists()) {
            try {
                Desktop.getDesktop().open(new File(f.getParent()));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            txtLog.append("File does not exist.\r\n");
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        p.stop();
        if (null != refreshTask && !refreshTask.isCancelled()) {
            refreshTask.cancel(true);
        }
        if (null != redBgTimerTask && !redBgTimerTask.isCancelled()) {
            redBgTimerTask.cancel(true);
        }
        TopPanel.setBackground((new Color(240, 240, 240)));
        for (JComponent jc : btnDisableGrp) {
            jc.setEnabled(true);
        }
        txtFileName.setEditable(true);
        for (JComponent jc : btnEnableGrp) {
            jc.setEnabled(false);
        }
        this.setTitle(portNames[cboxCOMList.getSelectedIndex()] + " " + ver);
        u.updatePerf();

    }//GEN-LAST:event_btnStopActionPerformed

    private void btnRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordActionPerformed

        if (cboxCOMList.getSelectedIndex() < 0
                || txtFileName.getText().endsWith("\\")) {
            return;
        }
        txtFileName.setText(txtFileName.getText().replaceAll("\\[COM\\]", portNames[cboxCOMList.getSelectedIndex()]));
        if (!txtFileName.getText().endsWith(".ser")) {
            txtFileName.setText(txtFileName.getText() + ".ser");
        }
        p = new PortAccessor(portNames[cboxCOMList.getSelectedIndex()]);
        p.setUpdater(u);
        ((FrmHuman) frmHuman).setPort(p);
        if (p.setFileToPath(txtFileName.getText()) && p.start()) {
            for (JComponent jc : btnDisableGrp) {
                jc.setEnabled(false);
            }
            txtFileName.setEditable(false);
            for (JComponent jc : btnEnableGrp) {
                jc.setEnabled(true);
            }
            String comPort = portNames[cboxCOMList.getSelectedIndex()];
            this.setTitle(comPort + " " + ver);
            this.statusFilePath = statusFileParent + comPort + "Status.txt";
            refreshTask = ses.scheduleWithFixedDelay(new Refresh(), 200, 200, TimeUnit.MILLISECONDS);
            redBgTimerTask = ses.scheduleWithFixedDelay((new Alarm()), 60, 60, TimeUnit.SECONDS);
        }
    }//GEN-LAST:event_btnRecordActionPerformed

    private void cboxCOMListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxCOMListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxCOMListActionPerformed

    private void jTxtLickFreqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtLickFreqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtLickFreqActionPerformed

    private void jButtonClearLickFreqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearLickFreqActionPerformed
        u.clearFreq();
        u.updateChart(0, -1);

    }//GEN-LAST:event_jButtonClearLickFreqActionPerformed

    private void btnScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScriptActionPerformed
        if (btnScript.isSelected()) {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory((new File(".")));
            fc.setFileFilter(new javax.swing.filechooser.FileFilter() {

                @Override
                public String getDescription() {
                    return "Script Files (.txt)";
                }

                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getAbsolutePath().endsWith(".txt");
                }

            });
            int result = fc.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                System.out.println("File selected");
                u.updateString("Running Script " + f.getName());
                System.out.println("String updated");
                se = new ScriptExecutor(f, p);
                new Thread(se).start();
            }
        } else {
            if (null != se) {
                se.stop();
            }
            u.updateString("Script stopped");
        }
    }//GEN-LAST:event_btnScriptActionPerformed

    private void btnCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCOMActionPerformed
        txtFileName.append("[COM]");
        txtFileName.requestFocusInWindow();
    }//GEN-LAST:event_btnCOMActionPerformed

    private void btnTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTempActionPerformed
        if (!txtFileName.getText().endsWith("TEMP_[COM].ser")) {
            txtFileName.append("TEMP_[COM].ser");
        }
        btnRecordActionPerformed(evt);
    }//GEN-LAST:event_btnTempActionPerformed

    private void btnHumanPnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHumanPnlActionPerformed
        frmHuman.setVisible(true);
    }//GEN-LAST:event_btnHumanPnlActionPerformed

    private void btnHistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoActionPerformed
        if (ydata_A.isEmpty() || ydata_B.isEmpty() || hist_A.isEmpty() || hist_B.isEmpty()) {
            return;
        }
        if (btnHisto.isSelected()) {
            if (histoChart.getSeriesMap().keySet().size() != 2) {
                btnHisto.setSelected(false);
            } else {
                CardLayout cl = (CardLayout) (pnlChart.getLayout());
                cl.show(pnlChart, "bar");
            }
        } else {
            CardLayout cl = (CardLayout) (pnlChart.getLayout());
            cl.show(pnlChart, "line");
        }
    }//GEN-LAST:event_btnHistoActionPerformed

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        refreshTask.cancel(true);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

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
        java.awt.EventQueue.invokeLater(() -> {
            new UI().setVisible(true);
        });
    }

    public class LogUpdator {

        final private ArrayList<int[][]> perfHist;
        final private String[] hName;
        final private boolean updateStatusFile;
//        final private StringBuilder logTxt;

        private int[][] currSta;
        private int lFreq = 0;
        private int lFreqMax = 0;
        private int rFreq = 0;
        private int rFreqMax = 0;
        private final StringBuilder freqText;
        private int lickCountFlag = 0;
        private int lickCountTimeCount;
        private int[] lickCount = new int[4];
        private int lcdX;
        private int lcdY;
        final private char[][] lcdText = new char[2][16];
        private boolean chartHighSet = false;
        private int chartVal;
        final private AtomicBoolean txtUpdated = new AtomicBoolean(false);

//        private LinkedList<Double> xdata = new LinkedList<>();
        public LogUpdator() {

            perfHist = new ArrayList<>();
            hName = EventNames.init();
            currSta = new int[3][5];//Hit,Miss,False,Reject,Abort
            updateStatusFile = (new File(statusFileParent)).exists();
            freqText = new StringBuilder();
        }
//        private int sumCorrect(int[] in){
//            return 
//        }

        public void updatePerf() {
//            Arrays.stream(currSta[0]).sum();
            if (Arrays.stream(currSta[0]).sum()
                    + Arrays.stream(currSta[1]).sum()
                    + Arrays.stream(currSta[2]).sum() > 0) {
                perfHist.add(currSta);
                final StringBuilder perf = new StringBuilder();
                for (int i = 0; i < perfHist.size(); i++) {
                    perf.append("S").append(String.format("%2d", i + 1)).append("\r\n");
                    int[][] histSta = perfHist.get(i);
                    int performance = (histSta[0][0] + histSta[0][3] + histSta[1][0] + histSta[1][3]) * 100
                            / (Arrays.stream(histSta[0]).sum() + Arrays.stream(histSta[1]).sum());

                    perf.append(String.format("P %3d", performance)).append(",")
                            .append(genPerfStr(histSta[0])).append("\r\n");
                    if (Arrays.stream(histSta[1]).sum() > 0) {
                        perf.append("2AFC,").append(genPerfStr(histSta[1])).append("\r\n");
                    }
                    if (Arrays.stream(histSta[2]).sum() > 0) {
                        perf.append("DUAL,").append(genPerfStr(histSta[2])).append("\r\n");
                    }
                    perf.append("\r\n");
                }
                final String s = perf.toString();
                txtPerf.setText(s);
                JScrollBar vbar = scrollPerf.getVerticalScrollBar();
                scrollPerf.validate();
                vbar.setValue(vbar.getMaximum());
                scrollPerf.validate();

                if (updateStatusFile) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(statusFilePath))) {
                        bw.write(s);
                    } catch (IOException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                currSta = new int[3][5];
            }
        }

        private String genPerfStr(int[] in) {
            StringBuilder sb = new StringBuilder();
            sb.append("H").append(String.format("%2d", in[0])).append("  ")
                    .append("M").append(String.format("%2d", in[1])).append("  ")
                    .append("F").append(String.format("%2d", in[2])).append("  ")
                    .append("C").append(String.format("%2d", in[3])).append("  ")
                    .append("A").append(String.format("%2d", in[4]));
            return sb.toString();
        }

        private void updateCurrSta() {
            final StringBuilder currStaStr = new StringBuilder();
            currStaStr.append(genPerfStr(currSta[0]));
            if (Arrays.stream(currSta[1]).sum() > 0) {
                currStaStr.append("\r\n").append(genPerfStr(currSta[1]));
            }
            if (Arrays.stream(currSta[2]).sum() > 0) {
                currStaStr.append("\r\n").append(genPerfStr(currSta[2]));
            }
            final String s = currStaStr.toString();
            try {
                SwingUtilities.invokeAndWait(() -> {
                    txtCurrPref.setText(s);
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        synchronized public void updateEvent(int[] event) {

            if (alarm) {
                alarm = false;
                try {
                    SwingUtilities.invokeAndWait(() -> {
                        if (TopPanel.getBackground().equals(Color.red)) {
                            TopPanel.setBackground(new Color(240, 240, 240));
                        }
                    });
                } catch (InterruptedException | InvocationTargetException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String s = evt2Str(event);
            if (null != s) {
                updateString(s);
            }

            if (lickCountFlag > 0 && event[0] - lickCountTimeCount > 1000) {
                switch (lickCountFlag) {
                    case 1:
                        lFreq = lickCount[0];
                        lFreqMax = lFreqMax < lFreq ? lFreq : lFreqMax;
                        break;
                    case 2:
                        rFreq = lickCount[1];
                        rFreqMax = rFreqMax < rFreq ? rFreq : rFreqMax;
                        break;
                }
                updateFreq();
                lickCountFlag = 0;
            }

            switch (event[1]) {
                case 0:
                    if (lickCountFlag > 0) {
                        //TODO:Lick Count
                        if (event[2] == (int) 'L') {
                            lickCount[0]++;
                        } else if (event[2] == (int) 'R') {
                            lickCount[1]++;
                        }
                    }
                    break;
                case 1:
                    if (event[2] >= 0 && event[2] <= 9) {
                        SwingUtilities.invokeLater(()
                                -> jTxtPermText.setText(jTxtPermText.getText() + Integer.toString(event[2])));
                    }

                    break;
                case 4:
                    currSta[event[2] - 1][2]++;//false
                    updateCurrSta();
                    break;
                case 5:
                    currSta[event[2] - 1][3]++;//reject
                    updateCurrSta();
                    break;
                case 6:
                    if (event[2] < 4 && event[2] > 0) {
                        currSta[event[2] - 1][1]++;//Miss
                        updateCurrSta();
                    }
                    break;
                case 7:
                    currSta[event[2] - 1][0]++; //hit
                    updateCurrSta();
                    break;
                case 84:
                    currSta[event[2] - 1][4]++; //abort
                    updateCurrSta();
                    break;
                case 20:
                    if (event[2] == 1) {
                        p.writeByte((byte) 0x31);
                    }
                    break;
                case 25:
//                    System.out.println("lcd_cmd " + event[2]);
                    switch ((event[2] & 0xE0) >> 5) {
                        case 0:
//                            System.out.println(event[2]);
                            this.lcdX = (event[2] & 0x1F) >> 1;
                            this.lcdY = event[2] & 0x01;
//                            System.out.println("x="+this.lcdX+", y="+this.lcdY);
                            break;
                        case 1:
//                            System.out.println("home_clear");
                            txtLCD.setText("");
                            this.lcdText[0] = new char[16];
                            this.lcdText[1] = new char[16];
                            this.lcdX = 0;
                            this.lcdY = 0;
                            break;
                    }
                    break;
                case 26:
//                    System.out.println("lcd_data " + event[2]);
                    if (this.lcdX < 16 && this.lcdY < 2) {
//                        System.out.println("x@"+this.lcdX+", "+"y@"+this.lcdY+", "+(char) event[2]);
                        this.lcdText[this.lcdY][this.lcdX] = (char) event[2];
                        txtLCD.setText(String.valueOf(this.lcdText[0]) + "\n" + String.valueOf(this.lcdText[1]));
                        this.lcdX++;
                    }

                    if (frmHuman.isVisible()) {

                        ((FrmHuman) frmHuman).updateTipTxt(
                                String.valueOf(this.lcdText[0]), String.valueOf(this.lcdText[1]));

                    }
                    break;
//                case 21:
//                    updatePermString(event[2]);
//                    break;
                case 22://
                    lickCountFlag = event[2];
                    lickCount = new int[2];
                    lickCountTimeCount = event[0];
                    break;
                case 61:
                    if (event[2] == 0) {
                        updatePerf();
                    }
                    break;
                case 27:
                    chartVal = (event[2] & 0x3f) << 6;
                    chartHighSet = true;
                    break;
                case 28:
                    chartVal += (event[2] & 0x3f);
                    if (chartHighSet) {
                        chartHighSet = false;
                        updateChart(chartVal, (event[2] & 0x40) >> 6);
                    }
                    break;

            }
        }

        public void updateChart(int val, int grp) {
            if (grp < 0) {
                hist_A.clear();
                hist_B.clear();
                ydata_A.clear();
                ydata_B.clear();
            } else if (grp == 0) {
                hist_A.add((double) val);
                ydata_A.add((double) val);
                if (ydata_A.size() > 30) {
                    ydata_A.subList(0, ydata_A.size() - 30).clear();
                }
            } else {
                hist_B.add((double) val);
                ydata_B.add((double) val);
                if (ydata_B.size() > 30) {
                    ydata_B.subList(0, ydata_B.size() - 30).clear();
                }
            }
            histo_A = new Histogram(hist_A, 50, 0, 1000);
            histo_B = new Histogram(hist_B, 50, 0, 1000);

            SwingUtilities.invokeLater(() -> {
                if (chart.getSeriesMap().size() != 2) {
                    Set<String> keyset = new HashSet<>(chart.getSeriesMap().keySet());
                    keyset.forEach((key) -> {
                        chart.removeSeries(key);
                    });
                    if (!ydata_A.isEmpty()) {
                        chart.addSeries(dataNameA, null, ydata_A, null);
                    }
                    if (!ydata_B.isEmpty()) {
                        chart.addSeries(dataNameB, null, ydata_B, null);
                    }
                }
                if (histoChart.getSeriesMap().size() != 2) {
                    Set<String> histoKeyset = new HashSet<>(histoChart.getSeriesMap().keySet());
                    histoKeyset.forEach((key) -> {
                        histoChart.removeSeries(key);
                    });
                    histoChart.addSeries("histoA", histo_A.getxAxisData(), histo_A.getyAxisData());
                    histoChart.addSeries("histoB", histo_B.getxAxisData(), histo_B.getyAxisData());
                }
                if (!ydata_A.isEmpty()) {
                    chart.updateXYSeries(dataNameA, null, ydata_A, null);
                }
                if (!ydata_B.isEmpty()) {
                    chart.updateXYSeries(dataNameB, null, ydata_B, null);
                }
                histoChart.updateCategorySeries("histoA", histo_A.getxAxisData(), histo_A.getyAxisData(), null);
                histoChart.updateCategorySeries("histoB", histo_B.getxAxisData(), histo_B.getyAxisData(), null);

            });
        }

        synchronized private void updateFreq() {
//            if (lFreqMax + rFreqMax == 0) {
//                return;
//            }
            freqText.setLength(0);
            freqText.append("L:").append(lFreq).append("/").append(lFreqMax)
                    .append("; ")
                    .append("R:").append(rFreq).append("/").append(rFreqMax);
            final String s = freqText.toString();
            try {
                SwingUtilities.invokeAndWait(
                        () -> {
                            jTxtLickFreq.setText(s);
                        });
            } catch (InterruptedException | InvocationTargetException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void updateString(String str) {

            while (!logTxtQue.offer(str)) {
                logTxtQue.poll();
            }
            this.txtUpdated.set(true);

        }

        private String evt2Str(int[] evt) {
            String s;
            switch (evt[1]) {
                case 0x7f:
                    s = "Error during parsing";
                    break;
                case 25:
                case 26:
                case 27:
                case 28:
                    s = null;
                    break;
                default:
                    s = (Integer.toString(evt[0]) + ","
                            + ((evt[1] >= hName.length) ? evt[1] : hName[evt[1]]) + ","
                            + Integer.toString(evt[2]));
            }
            return s;
        }

        public void clearFreq() {
            lFreq = 0;
            rFreq = 0;
            rFreqMax = 0;
            lFreqMax = 0;
            SwingUtilities.invokeLater(
                    () -> {
                        jTxtLickFreq.setText("");
                    });
        }
    }

    private long getPID() {
        String processName
                = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        return Long.parseLong(processName.split("@")[0]);
    }

    class Alarm implements Runnable {

        @Override
        public void run() {
            if (alarm) {
                try {
                    SwingUtilities.invokeAndWait(
                            () -> {
                                TopPanel.setBackground(Color.red);
                            });
                } catch (InterruptedException | InvocationTargetException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            alarm = true;
        }
    }

    class Refresh implements Runnable {

        @Override
        public void run() {
            SwingUtilities.invokeLater(() -> {
                if (u.txtUpdated.getAndSet(false)) {
                    StringBuilder sb = new StringBuilder();
                    logTxtQue.iterator().forEachRemaining((s) -> {
                        sb.append(s);
                        sb.append("\r\n");
                    });
                    txtLog.setText(sb.toString());
                }
                pnlLineChart.revalidate();
                pnlLineChart.repaint();
                pnlHisto.revalidate();
                pnlHisto.repaint();
            });
//            }
        }
    }

    @Override
    final public void setIconImage(Image image) {
        super.setIconImage(image);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LBPanel;
    private javax.swing.JPanel RBPanel;
    private javax.swing.JPanel TopPanel;
    private javax.swing.JButton btnCOM;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDate;
    private javax.swing.JToggleButton btnHisto;
    private javax.swing.JButton btnHumanPnl;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnRecord;
    private javax.swing.JToggleButton btnScript;
    private javax.swing.JButton btnSlash;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnTemp;
    private javax.swing.JButton btnType;
    private javax.swing.JComboBox cboxCOMList;
    private javax.swing.JButton jButton0;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonClearLickFreq;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollFilePath;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollTxtLog;
    private javax.swing.JTextField jTxtLickFreq;
    private javax.swing.JTextField jTxtPermText;
    private javax.swing.JLabel lblEmpty;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlBtn;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlHisto;
    private javax.swing.JPanel pnlLineChart;
    private javax.swing.JPanel pnlLogLCD;
    private javax.swing.JPanel pnlNum;
    private javax.swing.JPanel pnlNumButton;
    private javax.swing.JScrollPane scrollPerf;
    private javax.swing.JTextArea txtCurrPref;
    private javax.swing.JTextArea txtFileName;
    private javax.swing.JTextArea txtLCD;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextArea txtPerf;
    // End of variables declaration//GEN-END:variables

    private final JComponent[] btnEnableGrp;
    private final JComponent[] btnDisableGrp;
    private boolean alarm = false;
    final private ScheduledExecutorService ses;
    private ScheduledFuture redBgTimerTask;
    private ScheduledFuture refreshTask;
    private ScriptExecutor se;
    final private JFrame frmHuman;
}
