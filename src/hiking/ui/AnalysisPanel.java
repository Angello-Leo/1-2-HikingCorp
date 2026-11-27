package hiking.ui;

import hiking.dao.StatsDao;
import hiking.ml.*;
import hiking.model.Perm;
import hiking.model.ProductSnapshot;
import hiking.security.Session;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * Knowledge analysis screen for ONE task (demand / sales / slow-moving / best-selling).
 * Edit the layout in the Design tab; the logic is in the hand-written part below the generated code.
 */
public class AnalysisPanel extends javax.swing.JPanel implements hiking.ui.PageView {

    public AnalysisPanel(hiking.ml.Task task) {
        initComponents();
        this.task = task;
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblBanner = new javax.swing.JLabel();
        pnlHead = new javax.swing.JPanel();
        pnlModel = new javax.swing.JPanel();
        lblModelCaption = new javax.swing.JLabel();
        cmbModel = new javax.swing.JComboBox<>();
        lblStatus = new javax.swing.JLabel();
        pnlRetrain = new javax.swing.JPanel();
        btnRetrain = new javax.swing.JButton();
        tabs = new javax.swing.JTabbedPane();
        scrPrediction = new javax.swing.JScrollPane();
        pnlPrediction = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        cardInputs = new javax.swing.JPanel();
        lblInputsTitle = new javax.swing.JLabel();
        pnlInputsBody = new javax.swing.JPanel();
        pnlPick = new javax.swing.JPanel();
        lblPickCaption = new javax.swing.JLabel();
        cmbProduct = new javax.swing.JComboBox<>();
        pnlMiddle = new javax.swing.JPanel();
        pnlDate = new javax.swing.JPanel();
        lblDateCaption = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        pnlFields = new javax.swing.JPanel();
        pnlPredictBtn = new javax.swing.JPanel();
        btnPredict = new javax.swing.JButton();
        pnlResultWrap = new javax.swing.JPanel();
        cardResult = new javax.swing.JPanel();
        lblResultTitle = new javax.swing.JLabel();
        pnlResultBody = new javax.swing.JPanel();
        pnlResultTop = new javax.swing.JPanel();
        lblResultModel = new javax.swing.JLabel();
        lblResultValue = new javax.swing.JLabel();
        lblResultUnit = new javax.swing.JLabel();
        pnlBadge = new javax.swing.JPanel();
        lblResultBadge = new javax.swing.JLabel();
        lblResultNote = new javax.swing.JLabel();
        lblResultBoth = new javax.swing.JLabel();
        pnlBatchWrap = new javax.swing.JPanel();
        pnlBatchPad = new javax.swing.JPanel();
        cardBatch = new javax.swing.JPanel();
        pnlBatchHead = new javax.swing.JPanel();
        pnlBatchTitles = new javax.swing.JPanel();
        lblBatchTitle = new javax.swing.JLabel();
        lblBatchHint = new javax.swing.JLabel();
        pnlBatchCtl = new javax.swing.JPanel();
        cmbHorizon = new javax.swing.JComboBox<>();
        btnRun = new javax.swing.JButton();
        pnlBatchOut = new javax.swing.JPanel();
        lblBatchSummary = new javax.swing.JLabel();
        pnlBatchBody = new javax.swing.JPanel();
        pnlBatchTable = new javax.swing.JPanel();
        pnlBatchChart = new javax.swing.JPanel();
        scrEval = new javax.swing.JScrollPane();
        pnlEvalHost = new javax.swing.JPanel();
        scrData = new javax.swing.JScrollPane();
        pnlDataHost = new javax.swing.JPanel();

        setBackground(new java.awt.Color(214, 223, 234));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 28, 24, 28));
        setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(214, 223, 234));
        pnlHeader.setLayout(new java.awt.BorderLayout());

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(220, 38, 38));

        pnlHead.setBackground(new java.awt.Color(214, 223, 234));
        pnlHead.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 12, 0));
        pnlHead.setLayout(new java.awt.BorderLayout());

        pnlModel.setBackground(new java.awt.Color(214, 223, 234));
        pnlModel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        lblModelCaption.setText("Model");
        lblModelCaption.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblModelCaption.setForeground(new java.awt.Color(63, 75, 95));

        cmbModel.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbModel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Best model (automatic)", "Random Forest Regression", "Simple Linear Regression" }));
        cmbModel.setPreferredSize(new java.awt.Dimension(250, 38));

        lblStatus.setText(" ");
        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblStatus.setForeground(new java.awt.Color(63, 75, 95));

        pnlRetrain.setBackground(new java.awt.Color(214, 223, 234));
        pnlRetrain.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        btnRetrain.setText("Retrain & save all models");
        btnRetrain.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnRetrain.setBackground(new java.awt.Color(31, 41, 55));
        btnRetrain.setForeground(new java.awt.Color(255, 255, 255));
        btnRetrain.setContentAreaFilled(false);
        btnRetrain.setBorderPainted(false);
        btnRetrain.setFocusPainted(false);
        btnRetrain.setOpaque(true);
        btnRetrain.setPreferredSize(new java.awt.Dimension(230, 38));
        btnRetrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrainActionPerformed(evt);
            }
        });

        tabs.setFont(new java.awt.Font("Segoe UI", 1, 14));


        pnlPrediction.setBackground(new java.awt.Color(214, 223, 234));
        pnlPrediction.setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(214, 223, 234));
        pnlTop.setLayout(new java.awt.BorderLayout());

        cardInputs.setBackground(new java.awt.Color(255, 255, 255));
        cardInputs.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardInputs.setLayout(new java.awt.BorderLayout());

        lblInputsTitle.setText("Inputs");
        lblInputsTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblInputsTitle.setForeground(new java.awt.Color(31, 41, 55));
        lblInputsTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));

        pnlInputsBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlInputsBody.setLayout(new java.awt.BorderLayout());

        pnlPick.setBackground(new java.awt.Color(255, 255, 255));
        pnlPick.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlPick.setLayout(new java.awt.BorderLayout());

        lblPickCaption.setText("MENU ITEM");
        lblPickCaption.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblPickCaption.setForeground(new java.awt.Color(63, 75, 95));
        lblPickCaption.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 2, 4, 0));

        cmbProduct.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(loading menu items...)" }));
        cmbProduct.setPreferredSize(new java.awt.Dimension(300, 40));
        cmbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductActionPerformed(evt);
            }
        });

        pnlMiddle.setBackground(new java.awt.Color(255, 255, 255));
        pnlMiddle.setLayout(new java.awt.BorderLayout());

        pnlDate.setBackground(new java.awt.Color(255, 255, 255));
        pnlDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDate.setLayout(new java.awt.BorderLayout());

        lblDateCaption.setText("DATE TO PREDICT (yyyy-mm-dd)");
        lblDateCaption.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblDateCaption.setForeground(new java.awt.Color(63, 75, 95));
        lblDateCaption.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 2, 4, 0));

        txtDate.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtDate.setColumns(12);
        txtDate.setPreferredSize(new java.awt.Dimension(300, 40));

        pnlFields.setBackground(new java.awt.Color(255, 255, 255));
        pnlFields.setLayout(new java.awt.BorderLayout());

        pnlPredictBtn.setBackground(new java.awt.Color(255, 255, 255));
        pnlPredictBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        pnlPredictBtn.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        btnPredict.setText("Predict");
        btnPredict.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnPredict.setBackground(new java.awt.Color(214, 110, 46));
        btnPredict.setForeground(new java.awt.Color(255, 255, 255));
        btnPredict.setContentAreaFilled(false);
        btnPredict.setBorderPainted(false);
        btnPredict.setFocusPainted(false);
        btnPredict.setOpaque(true);
        btnPredict.setPreferredSize(new java.awt.Dimension(150, 40));
        btnPredict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPredictActionPerformed(evt);
            }
        });

        pnlResultWrap.setBackground(new java.awt.Color(214, 223, 234));
        pnlResultWrap.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 0));
        pnlResultWrap.setPreferredSize(new java.awt.Dimension(456, 10));
        pnlResultWrap.setLayout(new java.awt.BorderLayout());

        cardResult.setBackground(new java.awt.Color(255, 255, 255));
        cardResult.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardResult.setLayout(new java.awt.BorderLayout());

        lblResultTitle.setText("Prediction result");
        lblResultTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblResultTitle.setForeground(new java.awt.Color(31, 41, 55));
        lblResultTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));

        pnlResultBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlResultBody.setLayout(new java.awt.BorderLayout());

        pnlResultTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlResultTop.setPreferredSize(new java.awt.Dimension(100, 150));
        pnlResultTop.setLayout(new java.awt.GridLayout(4, 1, 0, 2));

        lblResultModel.setText("Enter the inputs and press Predict.");
        lblResultModel.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblResultModel.setForeground(new java.awt.Color(63, 75, 95));

        lblResultValue.setText("-");
        lblResultValue.setFont(new java.awt.Font("Segoe UI", 1, 44));
        lblResultValue.setForeground(new java.awt.Color(31, 41, 55));
        lblResultValue.setPreferredSize(new java.awt.Dimension(100, 66));

        lblResultUnit.setText(" ");
        lblResultUnit.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblResultUnit.setForeground(new java.awt.Color(63, 75, 95));

        pnlBadge.setBackground(new java.awt.Color(255, 255, 255));
        pnlBadge.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 4));

        lblResultBadge.setText(" ");
        lblResultBadge.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblResultBadge.setForeground(new java.awt.Color(63, 75, 95));

        lblResultNote.setText(" ");
        lblResultNote.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblResultNote.setForeground(new java.awt.Color(63, 75, 95));
        lblResultNote.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 0, 0));
        lblResultNote.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblResultBoth.setText(" ");
        lblResultBoth.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblResultBoth.setForeground(new java.awt.Color(63, 75, 95));
        lblResultBoth.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 0, 0));
        lblResultBoth.setPreferredSize(new java.awt.Dimension(100, 70));

        pnlBatchWrap.setBackground(new java.awt.Color(214, 223, 234));
        pnlBatchWrap.setLayout(new java.awt.BorderLayout());

        pnlBatchPad.setBackground(new java.awt.Color(214, 223, 234));
        pnlBatchPad.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 0, 24, 0));
        pnlBatchPad.setLayout(new java.awt.BorderLayout());

        cardBatch.setBackground(new java.awt.Color(255, 255, 255));
        cardBatch.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardBatch.setLayout(new java.awt.BorderLayout());

        pnlBatchHead.setBackground(new java.awt.Color(255, 255, 255));
        pnlBatchHead.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        pnlBatchHead.setLayout(new java.awt.BorderLayout());

        pnlBatchTitles.setBackground(new java.awt.Color(255, 255, 255));
        pnlBatchTitles.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblBatchTitle.setText("Batch forecast");
        lblBatchTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblBatchTitle.setForeground(new java.awt.Color(31, 41, 55));

        lblBatchHint.setText(" ");
        lblBatchHint.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblBatchHint.setForeground(new java.awt.Color(63, 75, 95));

        pnlBatchCtl.setBackground(new java.awt.Color(255, 255, 255));
        pnlBatchCtl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        cmbHorizon.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbHorizon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Next 7 days", "Next 14 days", "Next 30 days" }));
        cmbHorizon.setPreferredSize(new java.awt.Dimension(160, 38));

        btnRun.setText("Run forecast");
        btnRun.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnRun.setBackground(new java.awt.Color(31, 41, 55));
        btnRun.setForeground(new java.awt.Color(255, 255, 255));
        btnRun.setContentAreaFilled(false);
        btnRun.setBorderPainted(false);
        btnRun.setFocusPainted(false);
        btnRun.setOpaque(true);
        btnRun.setPreferredSize(new java.awt.Dimension(150, 38));
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        pnlBatchOut.setBackground(new java.awt.Color(255, 255, 255));
        pnlBatchOut.setLayout(new java.awt.BorderLayout());

        lblBatchSummary.setText(" ");
        lblBatchSummary.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblBatchSummary.setForeground(new java.awt.Color(31, 41, 55));
        lblBatchSummary.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));

        pnlBatchBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlBatchBody.setPreferredSize(new java.awt.Dimension(300, 400));
        pnlBatchBody.setLayout(new java.awt.BorderLayout());

        pnlBatchTable.setBackground(new java.awt.Color(255, 255, 255));
        pnlBatchTable.setLayout(new java.awt.BorderLayout());

        pnlBatchChart.setBackground(new java.awt.Color(214, 223, 234));
        pnlBatchChart.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 0));
        pnlBatchChart.setPreferredSize(new java.awt.Dimension(480, 10));
        pnlBatchChart.setLayout(new java.awt.BorderLayout());


        pnlEvalHost.setBackground(new java.awt.Color(214, 223, 234));
        pnlEvalHost.setLayout(new java.awt.BorderLayout());


        pnlDataHost.setBackground(new java.awt.Color(214, 223, 234));
        pnlDataHost.setLayout(new java.awt.BorderLayout());

        pnlModel.add(lblModelCaption);
        pnlModel.add(cmbModel);
        pnlModel.add(lblStatus);
        pnlRetrain.add(btnRetrain);
        pnlHead.add(pnlModel, java.awt.BorderLayout.WEST);
        pnlHead.add(pnlRetrain, java.awt.BorderLayout.EAST);
        pnlHeader.add(lblBanner, java.awt.BorderLayout.NORTH);
        pnlHeader.add(pnlHead, java.awt.BorderLayout.CENTER);
        pnlPick.add(lblPickCaption, java.awt.BorderLayout.NORTH);
        pnlPick.add(cmbProduct, java.awt.BorderLayout.CENTER);
        pnlDate.add(lblDateCaption, java.awt.BorderLayout.NORTH);
        pnlDate.add(txtDate, java.awt.BorderLayout.CENTER);
        pnlMiddle.add(pnlDate, java.awt.BorderLayout.NORTH);
        pnlMiddle.add(pnlFields, java.awt.BorderLayout.CENTER);
        pnlPredictBtn.add(btnPredict);
        pnlInputsBody.add(pnlPick, java.awt.BorderLayout.NORTH);
        pnlInputsBody.add(pnlMiddle, java.awt.BorderLayout.CENTER);
        pnlInputsBody.add(pnlPredictBtn, java.awt.BorderLayout.SOUTH);
        cardInputs.add(lblInputsTitle, java.awt.BorderLayout.NORTH);
        cardInputs.add(pnlInputsBody, java.awt.BorderLayout.CENTER);
        pnlBadge.add(lblResultBadge);
        pnlResultTop.add(lblResultModel);
        pnlResultTop.add(lblResultValue);
        pnlResultTop.add(lblResultUnit);
        pnlResultTop.add(pnlBadge);
        pnlResultBody.add(pnlResultTop, java.awt.BorderLayout.NORTH);
        pnlResultBody.add(lblResultNote, java.awt.BorderLayout.CENTER);
        pnlResultBody.add(lblResultBoth, java.awt.BorderLayout.SOUTH);
        cardResult.add(lblResultTitle, java.awt.BorderLayout.NORTH);
        cardResult.add(pnlResultBody, java.awt.BorderLayout.CENTER);
        pnlResultWrap.add(cardResult, java.awt.BorderLayout.CENTER);
        pnlTop.add(cardInputs, java.awt.BorderLayout.CENTER);
        pnlTop.add(pnlResultWrap, java.awt.BorderLayout.EAST);
        pnlBatchTitles.add(lblBatchTitle);
        pnlBatchTitles.add(lblBatchHint);
        pnlBatchCtl.add(cmbHorizon);
        pnlBatchCtl.add(btnRun);
        pnlBatchHead.add(pnlBatchTitles, java.awt.BorderLayout.WEST);
        pnlBatchHead.add(pnlBatchCtl, java.awt.BorderLayout.EAST);
        pnlBatchBody.add(pnlBatchTable, java.awt.BorderLayout.CENTER);
        pnlBatchBody.add(pnlBatchChart, java.awt.BorderLayout.EAST);
        pnlBatchOut.add(lblBatchSummary, java.awt.BorderLayout.NORTH);
        pnlBatchOut.add(pnlBatchBody, java.awt.BorderLayout.CENTER);
        cardBatch.add(pnlBatchHead, java.awt.BorderLayout.NORTH);
        cardBatch.add(pnlBatchOut, java.awt.BorderLayout.CENTER);
        pnlBatchPad.add(cardBatch, java.awt.BorderLayout.CENTER);
        pnlBatchWrap.add(pnlBatchPad, java.awt.BorderLayout.NORTH);
        pnlPrediction.add(pnlTop, java.awt.BorderLayout.NORTH);
        pnlPrediction.add(pnlBatchWrap, java.awt.BorderLayout.CENTER);
        scrPrediction.setViewportView(pnlPrediction);
        scrEval.setViewportView(pnlEvalHost);
        scrData.setViewportView(pnlDataHost);
        tabs.addTab("Prediction", scrPrediction);
        tabs.addTab("Evaluation & metrics", scrEval);
        tabs.addTab("Data set", scrData);
        add(pnlHeader, java.awt.BorderLayout.NORTH);
        add(tabs, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrainActionPerformed
        retrain();
    }//GEN-LAST:event_btnRetrainActionPerformed

    private void cmbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductActionPerformed
        if (loadingPicker) return;
        ProductSnapshot sp = selectedProduct();
        if (sp != null) autofill(sp);
    }//GEN-LAST:event_cmbProductActionPerformed

    private void btnPredictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPredictActionPerformed
        predict();
    }//GEN-LAST:event_btnPredictActionPerformed

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        runBatch();
    }//GEN-LAST:event_btnRunActionPerformed


    // =====================================================================================================
    //  HAND-WRITTEN LOGIC (outside the generated block - safe to edit; the Design tab only owns initComponents)
    // =====================================================================================================
    private final Task task;
    private final AnalysisService svc = AnalysisService.get();
    private boolean details, canRetrain, built, loadingPicker;
    private final Map<String, JComponent> in = new LinkedHashMap<>();       // dynamic input editors by field name
    private final List<ProductSnapshot> snaps = new ArrayList<>();
    private final VPanel evalPanel = new VPanel(), dataPanel = new VPanel();

    @Override public String title() { return task.title; }
    @Override public String subtitle() { return task.about; }

    private void postInit() {
        details = Session.can(Perm.ANALYSIS_DETAILS); canRetrain = Session.can(Perm.MODEL_RETRAIN);
        Util.clearBanner(lblBanner); lblStatus.setText(" ");
        btnRetrain.setVisible(canRetrain);
        if (!details) { tabs.remove(scrEval); tabs.remove(scrData); }
        // scroll views follow the viewport width
        scrPrediction.setViewportView(new TrackWidth(pnlPrediction)); scrEval.setViewportView(new TrackWidth(pnlEvalHost)); scrData.setViewportView(new TrackWidth(pnlDataHost));
        pnlEvalHost.add(evalPanel, BorderLayout.NORTH); pnlDataHost.add(dataPanel, BorderLayout.NORTH);
        // card outlines
        for (JPanel p : new JPanel[]{cardInputs, cardResult, cardBatch})
            p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Theme.BORDER), p.getBorder()));
        // task specific captions
        txtDate.setText(LocalDate.now().plusDays(1).toString());
        pnlDate.setVisible(task.usesDate());
        pnlPick.setVisible(task != Task.SALES);
        lblPickCaption.setText(task == Task.DEMAND ? "MENU ITEM" : "FILL FROM A MENU ITEM (OPTIONAL - uses live database figures)");
        cmbHorizon.setVisible(task == Task.SALES);
        lblBatchTitle.setText(batchTitle()); lblBatchHint.setText(batchHint());
        lblBatchSummary.setText("Press \"Run forecast\" to score every menu item with the figures stored in the database."); lblBatchSummary.setForeground(Theme.MUTED); lblBatchSummary.setFont(Theme.plain(13.5f));
        pnlBatchChart.setVisible(false);
        lblResultBoth.setVisible(details); lblResultBadge.setVisible(false);
        buildInputs();
        Util.skin(this);
        for (JScrollPane sp : new JScrollPane[]{scrPrediction, scrEval, scrData}) { sp.setBorder(null); sp.getViewport().setBackground(Theme.BG); sp.setOpaque(false); }
        tabs.setOpaque(false); tabs.setBackground(Theme.BG);
    }

    @Override public void onShow() {
        loadPicker();
        if (task == Task.SALES) Util.async(lblBanner, () -> StatsDao.salesOn(LocalDate.now().minusDays(1)), v -> { if (v > 0) setVal("prev_day_sales", s(Math.round(v))); });
        if (!svc.isReady()) {
            lblStatus.setText("Loading saved models...");
            Util.async(lblBanner, () -> { svc.ensureReady(null); return Boolean.TRUE; }, ok -> { lblStatus.setText(" "); buildDetails(); });
        } else if (!built) buildDetails();
    }

    private static String s(double v) { return v == Math.rint(v) && Math.abs(v) < 1e12 ? String.valueOf((long) v) : String.format(Locale.ROOT, "%.2f", v); }

    /** Scroll-pane view that follows the viewport width (so content stretches and never needs a horizontal scroll bar). */
    static final class TrackWidth extends JPanel implements Scrollable {
        TrackWidth(JComponent c) { super(new BorderLayout()); setBackground(Theme.BG); add(c); }
        public Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
        public int getScrollableUnitIncrement(Rectangle r, int o, int d) { return 28; }
        public int getScrollableBlockIncrement(Rectangle r, int o, int d) { return 160; }
        public boolean getScrollableTracksViewportWidth() { return true; }
        public boolean getScrollableTracksViewportHeight() { return false; }
    }
    /** Vertical stack used by the Evaluation and Data set tabs. */
    static final class VPanel extends JPanel {
        VPanel() { setOpaque(false); setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); }
    }
    private static <T extends JComponent> T fixH(T c, int h) { c.setPreferredSize(new Dimension(10, h)); c.setMinimumSize(new Dimension(10, h)); c.setMaximumSize(new Dimension(Integer.MAX_VALUE, h)); c.setAlignmentX(Component.LEFT_ALIGNMENT); return c; }
    private static <T extends JComponent> T stretch(T c) { c.setAlignmentX(Component.LEFT_ALIGNMENT); return c; }
    private static Component gap(int h) { return Box.createRigidArea(new Dimension(1, h)); }

    // =====================================================================================================  PREDICTION TAB
    /** Builds the input editors that depend on the task (numbers, tick boxes, drop-downs) into pnlFields. */
    private void buildInputs() {
        JPanel grid = new JPanel(new GridLayout(0, 2, 18, 0)); grid.setOpaque(false);
        for (Task.F f : task.fields) {
            JComponent c;
            switch (f.kind) {
                case NUMBER: { JTextField t = new JTextField(s(f.def), 12); t.setFont(Theme.plain(14.5f)); t.setToolTipText(f.label + ": " + s(f.min) + " to " + s(f.max)); c = t; break; }
                case BOOL: { JCheckBox b = new JCheckBox(f.label); b.setFont(Theme.plain(14)); b.setOpaque(false); c = b; break; }
                case CATEGORY: { JComboBox<String> b = new JComboBox<>(Task.CATEGORIES); b.setFont(Theme.plain(14.5f)); c = b; break; }
                case MONTH: { JComboBox<String> m = new JComboBox<>(Task.MONTHS); m.setFont(Theme.plain(14.5f)); m.setSelectedIndex(LocalDate.now().getMonthValue() % 12); c = m; break; }
                default: continue;      // PRODUCT (taken from the picker) and DERIVED (computed from the date)
            }
            in.put(f.name, c); grid.add(cell(f.kind == Task.Kind.BOOL ? " " : f.label.toUpperCase(), c));
        }
        if (grid.getComponentCount() % 2 == 1) grid.add(new JPanel() {{ setOpaque(false); }});
        pnlFields.removeAll(); pnlFields.setLayout(new BorderLayout()); pnlFields.add(grid, BorderLayout.NORTH); pnlFields.revalidate();
    }
    private static JComponent cell(String label, JComponent c) {
        JPanel p = new JPanel(new BorderLayout()); p.setOpaque(false);
        JLabel l = new JLabel(label); l.setFont(Theme.bold(12)); l.setForeground(Theme.MUTED); l.setBorder(new EmptyBorder(4, 2, 4, 0)); p.add(l, BorderLayout.NORTH);
        c.setPreferredSize(new Dimension(200, 40)); p.add(c); p.setBorder(new EmptyBorder(0, 0, 8, 0)); return p;
    }
    private String batchTitle() { switch (task) { case DEMAND: return "Expected demand for every menu item"; case SALES: return "Sales forecast"; case SLOW: return "Slow-moving check for every menu item"; default: return "Best-seller ranking for next month"; } }
    private String batchHint() {
        switch (task) { case DEMAND: return "Uses the date, promo and temperature above plus each item's recent sales. Flags items that may run out."; case SALES: return "Day-by-day forecast starting at the date above (uses the promo and rainfall inputs)."; case SLOW: return "Uses current stock and the last 30 days of sales of every item."; default: return "Uses the month and promo days above plus each item's recent sales."; }
    }

    private ProductSnapshot selectedProduct() { int i = cmbProduct.getSelectedIndex(); return i >= 0 && i < snaps.size() ? snaps.get(i) : null; }
    private void loadPicker() {
        if (task == Task.SALES) return;
        Util.async(lblBanner, StatsDao::snapshots, l -> {
            ProductSnapshot keep = selectedProduct(); loadingPicker = true;
            snaps.clear(); snaps.addAll(l); cmbProduct.removeAllItems(); for (ProductSnapshot p : l) cmbProduct.addItem(p.name);
            if (keep != null) for (int i = 0; i < snaps.size(); i++) if (snaps.get(i).id == keep.id) cmbProduct.setSelectedIndex(i);
            loadingPicker = false;
            if (keep == null && !snaps.isEmpty() && task == Task.DEMAND) autofill(snaps.get(Math.max(0, cmbProduct.getSelectedIndex())));
        });
    }
    private void setVal(String name, String v) { JComponent c = in.get(name); if (c instanceof JTextField) ((JTextField) c).setText(v); }
    private void setIdx(String name, int i) { JComponent c = in.get(name); if (c instanceof JComboBox && i >= 0 && i < ((JComboBox<?>) c).getItemCount()) ((JComboBox<?>) c).setSelectedIndex(i); }
    private void autofill(ProductSnapshot p) {
        setIdx("category_code", p.categoryCode() - 1); setVal("unit_price", s(p.price)); setVal("prev_week_units", s(p.avg7())); setVal("stock_on_hand", String.valueOf(p.stock)); setVal("days_since_last_sale", String.valueOf(p.daysSinceLastSale()));
        setVal("avg_daily_units_30d", s(p.avg30())); setVal("shelf_life_days", String.valueOf(p.shelfLife)); setVal("margin_pct", s(p.margin())); setVal("units_prev_month", s(Math.round(p.u30))); setVal("units_prev_3m_avg", s(Math.round(p.u90 / 3)));
    }

    private String type() { int i = cmbModel.getSelectedIndex(); return i == 1 ? AnalysisService.RF : i == 2 ? AnalysisService.SLR : svc.bestType(task); }
    private static String typeName(String t) { return t.equals(AnalysisService.RF) ? "Random Forest Regression" : "Simple Linear Regression"; }

    /** Reads the input editors into the model's feature vector (throws IllegalArgumentException with a friendly message). */
    private double[] vector() {
        double[] x = new double[task.fields.length]; LocalDate d = null; if (task.usesDate()) d = Util.date(txtDate, "Date");
        for (int i = 0; i < x.length; i++) {
            Task.F f = task.fields[i]; JComponent c = in.get(f.name);
            switch (f.kind) {
                case NUMBER: { double v = Util.num((JTextField) c, f.label); if (v < f.min || v > f.max) throw new IllegalArgumentException(f.label + " must be between " + s(f.min) + " and " + s(f.max) + "."); x[i] = v; break; }
                case BOOL: x[i] = ((JCheckBox) c).isSelected() ? 1 : 0; break;
                case CATEGORY: case MONTH: x[i] = ((JComboBox<?>) c).getSelectedIndex() + 1; break;
                case PRODUCT: { ProductSnapshot p = selectedProduct(); if (p == null) throw new IllegalArgumentException("Choose a menu item."); x[i] = p.code(); break; }
                default: break;
            }
        }
        if (d != null) Cal.fill(task, x, d);
        return x;
    }
    private boolean ready() { if (!svc.isReady()) { Util.banner(lblBanner, "The models are still loading - please try again in a moment.", false); return false; } return true; }
    private void badge(String text, Color c) {
        lblResultBadge.setText(text); lblResultBadge.setForeground(c); lblResultBadge.setBackground(Theme.alpha(c, 28)); lblResultBadge.setOpaque(true); lblResultBadge.setBorder(new EmptyBorder(3, 10, 3, 10)); lblResultBadge.setVisible(true);
    }

    private void predict() {
        double[] x; try { x = vector(); } catch (IllegalArgumentException ex) { Util.banner(lblBanner, ex.getMessage(), false); return; }
        if (!ready()) return; Util.clearBanner(lblBanner);
        String t = type(); AnalysisService.Result r = svc.predict(task, t, x); ModelBundle b = svc.bundle(task, t);
        boolean money = task == Task.SALES;
        lblResultModel.setText("<html>" + typeName(t) + (cmbModel.getSelectedIndex() == 0 ? "  (best model on test data)" : "") + "</html>");
        lblResultValue.setText(money ? Theme.money(r.value) : String.format("%,.1f", r.value)); lblResultUnit.setText(task.unit);
        StringBuilder n = new StringBuilder("<html><div style='width:360px'>");
        switch (task.mode) {
            case SLOW: badge(r.label.toUpperCase(), r.label.startsWith("SLOW") ? Theme.DANGER : Theme.OK); n.append("Items selling at or below <b>").append(String.format("%,.2f", b.eval.classThreshold)).append(" units/day</b> are classed as slow-moving."); if (r.label.startsWith("SLOW")) n.append("<br>Consider a promo, a smaller batch, or removing it from the menu."); break;
            case BEST: badge(r.label.toUpperCase(), r.label.startsWith("BEST") ? Theme.OK : Theme.INFO); n.append("Items selling at or above <b>").append(String.format("%,.0f", b.eval.classThreshold)).append(" units/month</b> are classed as best-sellers."); break;
            default:
                badge("FORECAST", Theme.PRIMARY);
                if (task == Task.DEMAND) n.append("Suggested kitchen preparation: <b>").append((long) Math.ceil(r.value * 1.1)).append(" units</b> (forecast + 10% safety buffer).");
                else n.append("Use this as the daily sales target when planning staff and purchases.");
        }
        if (details) {
            n.append("<br>Typical error on unseen data: &plusmn;").append(money ? Theme.money(b.eval.test.mae) : String.format("%,.2f", b.eval.test.mae)).append(" (MAE), R&sup2; = ").append(String.format("%.3f", b.eval.test.r2)).append('.');
            AnalysisService.Result a = svc.predict(task, AnalysisService.SLR, x), c = svc.predict(task, AnalysisService.RF, x);
            lblResultBoth.setText("<html><b>Both models</b><br>Simple Linear Regression: " + (money ? Theme.money(a.value) : String.format("%,.1f", a.value)) + "<br>Random Forest Regression: " + (money ? Theme.money(c.value) : String.format("%,.1f", c.value)) + "</html>");
        }
        lblResultNote.setText(n.append("</div></html>").toString());
    }

    // ---------------------------------------------------------------------------------------------- batch forecast
    private void runBatch() {
        if (!ready()) return; String t = type();
        try {
            switch (task) {
                case DEMAND: { LocalDate d = Util.date(txtDate, "Date"); boolean promo = checked("is_promo"); double temp = Util.num((JTextField) in.get("temperature_c"), "Temperature");
                    Util.async(lblBanner, () -> Forecasts.demand(t, d, promo, temp), this::showDemand); break; }
                case SALES: { LocalDate d = Util.date(txtDate, "Date"); boolean promo = checked("is_promo"); double rain = Util.num((JTextField) in.get("rain_mm"), "Rainfall"); int days = new int[]{7, 14, 30}[cmbHorizon.getSelectedIndex()];
                    Util.async(lblBanner, () -> Forecasts.sales(t, d, days, promo, rain, null), this::showSales); break; }
                case SLOW: { boolean promo = checked("is_promo"); Util.async(lblBanner, () -> Forecasts.slow(t, promo), this::showSlow); break; }
                default: { int month = ((JComboBox<?>) in.get("target_month")).getSelectedIndex() + 1; int pd = Util.integer((JTextField) in.get("promo_days"), "Planned promo days"); Util.async(lblBanner, () -> Forecasts.best(t, month, pd), this::showBest); }
            }
        } catch (IllegalArgumentException ex) { Util.banner(lblBanner, ex.getMessage(), false); }
    }
    private boolean checked(String name) { JComponent c = in.get(name); return c instanceof JCheckBox && ((JCheckBox) c).isSelected(); }

    /** Shows the batch result: summary line, table (left) and optional chart card (right). */
    private void out(String summary, Color color, DefaultTableModel m, String[] right, JComponent chartCard) {
        lblBatchSummary.setText(summary); lblBatchSummary.setForeground(color); lblBatchSummary.setFont(Theme.bold(14));
        JTable t = new JTable(m); JScrollPane sp = new JScrollPane(t); Util.fill(pnlBatchTable, sp); Util.skin(pnlBatchTable); Util.rightAlign(t, right);
        pnlBatchChart.setVisible(chartCard != null);
        if (chartCard != null) { Util.fill(pnlBatchChart, chartCard); }
        pnlBatchOut.revalidate(); pnlBatchOut.repaint();
    }
    private void showDemand(List<Forecasts.DemandRow> rows) {
        DefaultTableModel m = Util.model("Item", "Category", "In stock", "Expected demand", "Status"); int re = 0; double sum = 0;
        for (Forecasts.DemandRow r : rows) { m.addRow(new Object[]{r.p.name, r.p.category, r.p.stock, String.format("%,.1f", r.predicted), r.status}); if (r.status.equals("REORDER")) re++; sum += r.predicted; }
        out(String.format("Expected total: %,.0f units across %d items  •  %d item(s) may need re-ordering", sum, rows.size(), re), re > 0 ? Theme.WARN : Theme.OK, m, new String[]{"In stock", "Expected demand"}, null);
    }
    private void showSlow(List<Forecasts.SlowRow> rows) {
        DefaultTableModel m = Util.model("Item", "Category", "In stock", "Last 30 d (units/day)", "Predicted (units/day)", "Days of stock", "Class"); int n = 0;
        for (Forecasts.SlowRow r : rows) { m.addRow(new Object[]{r.p.name, r.p.category, r.p.stock, String.format("%,.2f", r.p.avg30()), String.format("%,.2f", r.velocity), r.daysCover >= 999 ? "999+" : String.format("%,.0f", r.daysCover), r.label.toUpperCase().equals("NORMAL") ? "OK" : r.label}); if (r.label.startsWith("SLOW")) n++; }
        out(n + " of " + rows.size() + " items are predicted to be slow-moving (listed first).", n > 0 ? Theme.DANGER : Theme.OK, m, new String[]{"In stock", "Last 30 d (units/day)", "Predicted (units/day)", "Days of stock"}, null);
    }
    private void showBest(List<Forecasts.BestRow> rows) {
        DefaultTableModel m = Util.model("Rank", "Item", "Category", "Price", "Last 30 d (units)", "Predicted next month", "Class"); LinkedHashMap<String, Double> top = new LinkedHashMap<>(); int n = 0;
        for (Forecasts.BestRow r : rows) { m.addRow(new Object[]{r.rank, r.p.name, r.p.category, Theme.money(r.p.price), String.format("%,.0f", r.p.u30), String.format("%,.0f", r.units), r.label.equals("Regular") ? "-" : r.label}); if (top.size() < 8) top.put(r.p.name, r.units); if (r.label.startsWith("BEST")) n++; }
        Charts.HBar bar = new Charts.HBar().format(v -> String.format("%,.0f", v)); bar.setData(top);
        out(n + " item(s) are predicted to be best-sellers next month.", Theme.OK, m, new String[]{"Rank", "Price", "Last 30 d (units)", "Predicted next month"}, Charts.card("Top 8 predicted", "Units next month", bar));
    }
    private void showSales(List<Forecasts.DayRow> rows) {
        DefaultTableModel m = Util.model("Date", "Day", "Predicted sales", "Notes"); double sum = 0; String[] xs = new String[rows.size()]; Double[] vs = new Double[rows.size()]; int i = 0;
        for (Forecasts.DayRow r : rows) { m.addRow(new Object[]{r.date.toString(), r.date.getDayOfWeek().getDisplayName(java.time.format.TextStyle.SHORT, Locale.ENGLISH), Theme.money(r.value), r.note}); sum += r.value; xs[i] = r.date.format(DateTimeFormatter.ofPattern("MMM d")); vs[i++] = r.value; }
        Charts.Line line = new Charts.Line().format(Theme::moneyShort); line.setData(xs, new Charts.Line.Series("Forecast", vs, Theme.PRIMARY, false, true));
        out(String.format("Expected total for %d days: %s  (average %s per day)", rows.size(), Theme.money(sum), Theme.money(rows.isEmpty() ? 0 : sum / rows.size())), Theme.OK, m, new String[]{"Predicted sales"}, Charts.card("Forecast curve", "PHP per day", line));
    }

    // =====================================================================================================  INSTRUCTOR TABS
    private void retrain() {
        btnRetrain.setEnabled(false); lblStatus.setText("Training... this can take a minute.");
        Util.async(() -> { svc.retrainAll((msg, f) -> SwingUtilities.invokeLater(() -> lblStatus.setText(msg + " " + (int) (f * 100) + "%"))); return Boolean.TRUE; },
            ok -> { btnRetrain.setEnabled(true); lblStatus.setText(" "); Util.banner(lblBanner, "All models were retrained on the current data sets and saved to the models folder.", true); buildDetails(); },
            ex -> { btnRetrain.setEnabled(true); lblStatus.setText(" "); Util.banner(lblBanner, Util.message(ex), false); });
    }

    private void buildDetails() {
        built = true; if (!details) return;
        buildEval();
        Util.async(lblBanner, () -> svc.dataset(task), this::buildData);
    }

    private void buildEval() {
        evalPanel.removeAll();
        ModelBundle slr = svc.bundle(task, AnalysisService.SLR), rf = svc.bundle(task, AnalysisService.RF); if (slr == null || rf == null) return;
        String best = svc.bestType(task); boolean money = task == Task.SALES;

        UI.Card about = new UI.Card(new BorderLayout(0, 8), 20); about.add(UI.h2("How the two models work"), BorderLayout.NORTH);
        JLabel txt = UI.label("<html><div style='width:900px'><b>Simple Linear Regression (SLR)</b> draws the best straight line y = b0 + b1·x through the data using ONE predictor. <i>Improvements:</i> the predictor with the highest R² is selected automatically, outliers are winsorised with IQR fences before fitting, and inputs/outputs are clamped to the training range so the line cannot extrapolate wildly.<br><br>"
            + "<b>Random Forest Regression (RF)</b> averages many decision trees, each trained on a bootstrap sample with random feature subsets, so it captures non-linear effects and interactions. <i>Improvements:</i> hyper-parameters (tree depth x minimum leaf size) are tuned by grid search on a validation split, an out-of-bag (OOB) score is reported, feature importance is calculated, and every model is checked with 10-fold cross-validation.<br><br>"
            + "<b>Save / load:</b> after training, each model and its evaluation are saved in the <code>models</code> folder and loaded on the next start. They are retrained automatically only when the data set file changes (SHA-256 check) or when you press <i>Retrain</i>. Data is split 80% training / 20% hold-out test with a fixed random seed, so results are reproducible.</div></html>", 13.5f, false, Theme.TEXT);
        about.add(txt); evalPanel.add(stretch(about)); evalPanel.add(gap(16));

        UI.Card cmp = new UI.Card(new BorderLayout(0, 10), 20); cmp.add(UI.h2("Model comparison (hold-out test set, " + slr.eval.nTest + " unseen rows)"), BorderLayout.NORTH);
        DefaultTableModel cm = Util.model("Model", "MAE", "MSE", "RMSE", "R²", "WAPE %", "10-fold CV R²", "Verdict");
        for (ModelBundle b : new ModelBundle[]{slr, rf}) { Metrics t = b.eval.test; cm.addRow(new Object[]{b.label(), f2(t.mae, money), String.format("%,.2f", t.mse), f2(t.rmse, money), String.format("%.3f", t.r2), String.format("%.1f", t.wape), String.format("%.3f ± %.3f", b.eval.cvR2, b.eval.cvR2Std), b.model.type().equals(best) ? "★ BEST (lowest RMSE)" : "-"}); }
        JTable ct = new JTable(cm); JScrollPane cs = new JScrollPane(ct); cs.setPreferredSize(new Dimension(300, 130)); cmp.add(cs);
        Util.skin(cmp); ct.setRowHeight(42); ct.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); { int[] w = {230, 90, 130, 110, 80, 90, 150, 220}; for (int i = 0; i < w.length; i++) ct.getColumnModel().getColumn(i).setPreferredWidth(w[i]); } Util.rightAlign(ct, "MAE", "MSE", "RMSE", "R²", "WAPE %", "10-fold CV R²");
        evalPanel.add(fixH(cmp, 230)); evalPanel.add(gap(16));

        for (ModelBundle b : new ModelBundle[]{slr, rf}) { evalPanel.add(stretch(modelCard(b, best, money))); evalPanel.add(gap(16)); }

        UI.Card g = new UI.Card(new BorderLayout(0, 10), 20); g.add(UI.h2("Evaluation metrics - what they mean"), BorderLayout.NORTH);
        JTable gt = wrapTable(new String[]{"Metric", "Name", "Formula", "What it tells you", "How to read it"}, Metrics.GLOSSARY, new int[]{110, 190, 230, 380, 330}); g.add((JComponent) gt.getParent().getParent());
        evalPanel.add(stretch(g)); evalPanel.add(gap(24)); evalPanel.revalidate(); evalPanel.repaint();
    }
    private static String f2(double v, boolean money) { return money ? Theme.money(v) : String.format("%,.2f", v); }

    private UI.Card modelCard(ModelBundle b, String best, boolean money) {
        Evaluation e = b.eval; boolean isBest = b.model.type().equals(best); Color c = b.model.type().equals("RF") ? Theme.INFO : Theme.PRIMARY;
        UI.Card card = new UI.Card(new BorderLayout(0, 12), 20);
        JPanel head = UI.vbox(); JPanel t = UI.transparent(new FlowLayout(FlowLayout.LEFT, 10, 0)); t.add(UI.h2(b.label())); if (isBest) t.add(new UI.Badge("BEST ON TEST DATA", Theme.OK)); t.add(UI.muted("trained " + new SimpleDateFormat("MMM d, yyyy h:mm a").format(new Date(e.trainedAt))));
        head.add(UI.left(t)); head.add(UI.left(UI.label("<html><span style='font-family:monospace'>" + e.params.replace("<", "&lt;") + "</span></html>", 12.5f, false, Theme.MUTED)));
        head.add(UI.left(UI.muted(String.format("Rows: %,d valid (%,d training / %,d test)%s", e.nTotal, e.nTrain, e.nTest, e.droppedRows > 0 ? " • " + e.droppedRows + " invalid rows dropped" : ""))));
        card.add(head, BorderLayout.NORTH);

        DefaultTableModel m = Util.model("Metric", "Training", "Test", "10-fold CV");
        m.addRow(new Object[]{"MAE", f2(e.train.mae, money), f2(e.test.mae, money), "-"}); m.addRow(new Object[]{"MSE", String.format("%,.2f", e.train.mse), String.format("%,.2f", e.test.mse), "-"});
        m.addRow(new Object[]{"RMSE", f2(e.train.rmse, money), f2(e.test.rmse, money), f2(e.cvRmse, money)}); m.addRow(new Object[]{"R²", String.format("%.3f", e.train.r2), String.format("%.3f", e.test.r2), String.format("%.3f", e.cvR2)});
        m.addRow(new Object[]{"WAPE %", String.format("%.1f", e.train.wape), String.format("%.1f", e.test.wape), "-"});
        if (!Double.isNaN(e.oobR2)) m.addRow(new Object[]{"OOB R²", "-", String.format("%.3f", e.oobR2), "-"});
        if (e.cls != null) {
            String pos = task.mode == Task.Mode.SLOW ? "slow-moving" : "best-seller";
            m.addRow(new Object[]{"Accuracy (" + pos + ")", "-", String.format("%.1f%%", e.cls.accuracy * 100), "-"}); m.addRow(new Object[]{"Precision", "-", String.format("%.1f%%", e.cls.precision * 100), "-"});
            m.addRow(new Object[]{"Recall", "-", String.format("%.1f%%", e.cls.recall * 100), "-"}); m.addRow(new Object[]{"F1 score", "-", String.format("%.1f%%", e.cls.f1 * 100), "-"});
        }
        JTable mt = new JTable(m); JScrollPane ms = new JScrollPane(mt); JPanel left = UI.transparent(new BorderLayout(0, 8)); left.add(ms);
        Util.skin(left); mt.setAutoCreateRowSorter(false); mt.setRowSorter(null); mt.setRowHeight(34); Util.rightAlign(mt, "Training", "Test", "10-fold CV");
        left.add(UI.label("<html><div style='width:360px'>" + interpret(e, money) + (e.cls == null ? "" : "<br><br>Confusion matrix (positive = " + (task.mode == Task.Mode.SLOW ? "slow-moving" : "best-seller") + ", threshold " + String.format("%,.2f", e.classThreshold) + "): TP " + e.cls.tp + " • FP " + e.cls.fp + " • FN " + e.cls.fn + " • TN " + e.cls.tn) + "</div></html>", 12.5f, false, Theme.MUTED), BorderLayout.SOUTH);

        Charts.Scatter sc = new Charts.Scatter(); sc.setData(e.testActual, e.testPred, c);
        UI.Card scCard = Charts.card("Actual vs predicted", "Hold-out test rows - closer to the diagonal is better", sc);
        Charts.HBar imp = new Charts.HBar().color(c).format(v -> String.format("%.1f%%", v)); LinkedHashMap<String, Double> im = new LinkedHashMap<>(); Integer[] idx = new Integer[e.importance.length]; for (int i = 0; i < idx.length; i++) idx[i] = i;
        Arrays.sort(idx, (x, y) -> Double.compare(e.importance[y], e.importance[x])); for (int i = 0; i < Math.min(8, idx.length); i++) if (e.importance[idx[i]] > 0.0005) im.put(e.featureNames[idx[i]], e.importance[idx[i]] * 100); imp.setData(im);
        UI.Card impCard = Charts.card(b.model.type().equals("SLR") ? "Predictor used" : "Feature importance", b.model.type().equals("SLR") ? "SLR uses one input only" : "Share of the forest's error reduction", imp);

        JPanel mid = UI.transparent(new GridLayout(1, 3, 16, 0)); mid.add(left); mid.add(scCard); mid.add(impCard); mid.setPreferredSize(new Dimension(300, 400)); card.add(mid);
        return card;
    }
    private String interpret(Evaluation e, boolean money) {
        double r2 = e.test.r2; String q = r2 >= .9 ? "excellent" : r2 >= .75 ? "good" : r2 >= .5 ? "moderate" : "weak";
        String gap = e.train.r2 - e.test.r2 > .1 ? " Training R² is clearly higher than test R², which suggests some over-fitting." : " Training and test scores are close, so the model generalises well.";
        return String.format("<b>Reading the numbers:</b> R² = %.2f is a %s fit - the model explains about %.0f%% of the variation in %s. Predictions are off by %s on average (MAE) or %.1f%% of total volume (WAPE).%s",
            r2, q, Math.max(0, r2) * 100, task.target.replace('_', ' '), f2(e.test.mae, money) + " " + (money ? "" : task.unit), e.test.wape, gap);
    }

    private void buildData(Dataset d) {
        dataPanel.removeAll();
        UI.Card info = new UI.Card(new BorderLayout(0, 8), 20); info.add(UI.h2("Data set: " + d.fileName), BorderLayout.NORTH);
        JPanel g = UI.transparent(new GridLayout(0, 4, 24, 6));
        String[][] kv = {{"Records in file", String.format("%,d", d.rows.size())}, {"Valid records used", String.format("%,d", d.size())}, {"Invalid rows dropped", String.valueOf(d.droppedRows)}, {"Target (what is predicted)", d.target + "  (" + task.unit + ")"},
            {"Input features", String.valueOf(d.featureNames.length)}, {"Train / test split", "80% / 20% (seed 2026)"}, {"File fingerprint (SHA-256)", d.sha256.substring(0, 16) + "..."}, {"Location", "data/" + d.fileName}};
        for (String[] r : kv) { JPanel c = UI.vbox(); c.add(UI.left(UI.label(r[0].toUpperCase(), 11, true, Theme.MUTED))); c.add(UI.left(UI.label(r[1], 14.5f, true, Theme.TEXT))); g.add(c); }
        info.add(g); dataPanel.add(fixH(info, 170)); dataPanel.add(gap(16));

        UI.Card sum = new UI.Card(new BorderLayout(0, 10), 20); sum.add(UI.h2("Column summary and meaning"), BorderLayout.NORTH);
        List<String[]> s = d.summary(); String[][] rows = new String[s.size()][];
        for (int i = 0; i < rows.length; i++) { String[] r = s.get(i); String role = r[0].equalsIgnoreCase(d.target) ? "TARGET" : Arrays.asList(d.featureNames).contains(r[0]) ? "feature" : "not used"; rows[i] = new String[]{r[0], role, r[1], r[2], r[3], r[4], Task.COLUMN_INFO.getOrDefault(r[0], "")}; }
        JTable st = wrapTable(new String[]{"Column", "Role", "Min", "Max", "Mean", "Std dev", "Meaning"}, rows, new int[]{170, 80, 80, 80, 80, 80, 520}); sum.add((JComponent) st.getParent().getParent());
        dataPanel.add(stretch(sum)); dataPanel.add(gap(16));

        int n = Math.min(300, d.rows.size()); DefaultTableModel pm = Util.model(d.header); for (int i = 0; i < n; i++) pm.addRow(d.rows.get(i));
        JTable pt = new JTable(pm); JScrollPane ps = new JScrollPane(pt);
        UI.Card prev = new UI.Card(new BorderLayout(0, 10), 20); prev.add(UI.h2("Data preview - first " + n + " of " + String.format("%,d", d.rows.size()) + " records"), BorderLayout.NORTH); prev.add(ps);
        Util.skin(prev); pt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); pt.setRowHeight(32); for (int i = 0; i < pt.getColumnCount(); i++) pt.getColumnModel().getColumn(i).setPreferredWidth(i == 1 && d.header[1].contains("name") ? 190 : 120);
        dataPanel.add(fixH(prev, 470)); dataPanel.add(gap(24)); dataPanel.revalidate(); dataPanel.repaint();
    }

    // ---------------------------------------------------------------------------------------------- wrapped-text table (glossary, column meanings)
    private static JTable wrapTable(String[] head, String[][] rows, int[] widths) {
        DefaultTableModel m = Util.model(head); for (String[] r : rows) m.addRow(r);
        JTable t = new JTable(m); JScrollPane sp = new JScrollPane(t); JPanel holder = new JPanel(new BorderLayout()); holder.setOpaque(false); holder.add(sp); Util.skin(holder);
        t.setRowSorter(null); t.setAutoCreateRowSorter(false); t.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        for (int i = 0; i < widths.length; i++) t.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        t.setDefaultRenderer(Object.class, new WrapRenderer());
        Runnable fit = () -> { for (int r = 0; r < t.getRowCount(); r++) { int h = 30; for (int c = 0; c < t.getColumnCount(); c++) { Component comp = t.prepareRenderer(t.getCellRenderer(r, c), r, c); h = Math.max(h, comp.getPreferredSize().height); } if (t.getRowHeight(r) != h) t.setRowHeight(r, h); } };
        t.addComponentListener(new ComponentAdapter() { @Override public void componentResized(ComponentEvent e) { SwingUtilities.invokeLater(fit); } });
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        sp.setPreferredSize(new Dimension(300, Math.min(48 + rows.length * 64, 40 + rows.length * 62)));
        SwingUtilities.invokeLater(() -> { fit.run(); int h = t.getTableHeader().getPreferredSize().height + t.getPreferredSize().height + 4; sp.setPreferredSize(new Dimension(300, h)); sp.revalidate(); });
        return t;
    }
    private static final class WrapRenderer extends JTextArea implements TableCellRenderer {
        WrapRenderer() { setLineWrap(true); setWrapStyleWord(true); setOpaque(true); setBorder(new EmptyBorder(8, 12, 8, 12)); setFont(Theme.plain(13)); }
        @Override public Component getTableCellRendererComponent(JTable t, Object v, boolean sel, boolean foc, int r, int c) {
            setText(v == null ? "" : v.toString()); setBackground(r % 2 == 0 ? Color.WHITE : Theme.ROW_ALT); setForeground(c == 0 ? Theme.TEXT : new Color(0x374151)); setFont(c == 0 ? Theme.bold(13) : Theme.plain(13));
            setSize(t.getColumnModel().getColumn(c).getWidth(), Short.MAX_VALUE); return this;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlHeader;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlHead;

    private javax.swing.JPanel pnlModel;

    private javax.swing.JLabel lblModelCaption;

    private javax.swing.JComboBox<String> cmbModel;

    private javax.swing.JLabel lblStatus;

    private javax.swing.JPanel pnlRetrain;

    private javax.swing.JButton btnRetrain;

    private javax.swing.JTabbedPane tabs;

    private javax.swing.JScrollPane scrPrediction;

    private javax.swing.JPanel pnlPrediction;

    private javax.swing.JPanel pnlTop;

    private javax.swing.JPanel cardInputs;

    private javax.swing.JLabel lblInputsTitle;

    private javax.swing.JPanel pnlInputsBody;

    private javax.swing.JPanel pnlPick;

    private javax.swing.JLabel lblPickCaption;

    private javax.swing.JComboBox<String> cmbProduct;

    private javax.swing.JPanel pnlMiddle;

    private javax.swing.JPanel pnlDate;

    private javax.swing.JLabel lblDateCaption;

    private javax.swing.JTextField txtDate;

    private javax.swing.JPanel pnlFields;

    private javax.swing.JPanel pnlPredictBtn;

    private javax.swing.JButton btnPredict;

    private javax.swing.JPanel pnlResultWrap;

    private javax.swing.JPanel cardResult;

    private javax.swing.JLabel lblResultTitle;

    private javax.swing.JPanel pnlResultBody;

    private javax.swing.JPanel pnlResultTop;

    private javax.swing.JLabel lblResultModel;

    private javax.swing.JLabel lblResultValue;

    private javax.swing.JLabel lblResultUnit;

    private javax.swing.JPanel pnlBadge;

    private javax.swing.JLabel lblResultBadge;

    private javax.swing.JLabel lblResultNote;

    private javax.swing.JLabel lblResultBoth;

    private javax.swing.JPanel pnlBatchWrap;

    private javax.swing.JPanel pnlBatchPad;

    private javax.swing.JPanel cardBatch;

    private javax.swing.JPanel pnlBatchHead;

    private javax.swing.JPanel pnlBatchTitles;

    private javax.swing.JLabel lblBatchTitle;

    private javax.swing.JLabel lblBatchHint;

    private javax.swing.JPanel pnlBatchCtl;

    private javax.swing.JComboBox<String> cmbHorizon;

    private javax.swing.JButton btnRun;

    private javax.swing.JPanel pnlBatchOut;

    private javax.swing.JLabel lblBatchSummary;

    private javax.swing.JPanel pnlBatchBody;

    private javax.swing.JPanel pnlBatchTable;

    private javax.swing.JPanel pnlBatchChart;

    private javax.swing.JScrollPane scrEval;

    private javax.swing.JPanel pnlEvalHost;

    private javax.swing.JScrollPane scrData;

    private javax.swing.JPanel pnlDataHost;

    // End of variables declaration//GEN-END:variables
}
