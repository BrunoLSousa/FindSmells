/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author bruno
 */
public class FIlterSystemForm extends javax.swing.JFrame {

    /**
     * Creates new form FIlterSystemForm
     */
    public FIlterSystemForm() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanelMain = new javax.swing.JPanel();
        jPanelFilterSystem = new javax.swing.JPanel();
        jLabelProject = new javax.swing.JLabel();
        jComboBoxProject = new javax.swing.JComboBox();
        jLabelDetectionStrategy = new javax.swing.JLabel();
        jComboBoxDetectionStrategy = new javax.swing.JComboBox();
        jButtonFilter = new javax.swing.JButton();
        jPanelRegisterProject = new javax.swing.JPanel();
        jLabelNameProject = new javax.swing.JLabel();
        jTextFieldNameProject = new javax.swing.JTextField();
        jPanelOptions = new javax.swing.JPanel();
        jRadioButtonOptionMultipleProjects = new javax.swing.JRadioButton();
        jRadioButtonOptionSingleProject = new javax.swing.JRadioButton();
        jButtonSelectXML = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSelectedProjects = new javax.swing.JTextArea();
        jButtonDone = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuTool = new javax.swing.JMenu();
        jMenuItemCreateDetectionStrategy = new javax.swing.JMenuItem();
        jMenuItemUpdateThreshold = new javax.swing.JMenuItem();
        jMenuItemRemoveDetectionStrategy = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelFilterSystem.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtering of system"));

        jLabelProject.setText("Choose a Project:");

        jComboBoxProject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelDetectionStrategy.setText("Choose a Detection Strategy:");

        jComboBoxDetectionStrategy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonFilter.setText("Filter");

        javax.swing.GroupLayout jPanelFilterSystemLayout = new javax.swing.GroupLayout(jPanelFilterSystem);
        jPanelFilterSystem.setLayout(jPanelFilterSystemLayout);
        jPanelFilterSystemLayout.setHorizontalGroup(
            jPanelFilterSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilterSystemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFilterSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonFilter)
                    .addGroup(jPanelFilterSystemLayout.createSequentialGroup()
                        .addGroup(jPanelFilterSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDetectionStrategy)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFilterSystemLayout.createSequentialGroup()
                                .addComponent(jLabelProject)
                                .addGap(37, 37, 37)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFilterSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxDetectionStrategy, 0, 235, Short.MAX_VALUE)
                            .addComponent(jComboBoxProject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFilterSystemLayout.setVerticalGroup(
            jPanelFilterSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilterSystemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFilterSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDetectionStrategy)
                    .addComponent(jComboBoxDetectionStrategy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFilterSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProject)
                    .addComponent(jComboBoxProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jButtonFilter)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanelRegisterProject.setBorder(javax.swing.BorderFactory.createTitledBorder("Register System"));
        jPanelRegisterProject.setMinimumSize(new java.awt.Dimension(597, 376));

        jLabelNameProject.setText("Project:");

        jTextFieldNameProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameProjectActionPerformed(evt);
            }
        });

        jPanelOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        jRadioButtonOptionMultipleProjects.setText("Register multiple projects");
        jRadioButtonOptionMultipleProjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOptionMultipleProjectsActionPerformed(evt);
            }
        });

        jRadioButtonOptionSingleProject.setText("Register a single project");
        jRadioButtonOptionSingleProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOptionSingleProjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOptionsLayout = new javax.swing.GroupLayout(jPanelOptions);
        jPanelOptions.setLayout(jPanelOptionsLayout);
        jPanelOptionsLayout.setHorizontalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonOptionMultipleProjects)
                    .addComponent(jRadioButtonOptionSingleProject))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOptionsLayout.setVerticalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonOptionMultipleProjects)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonOptionSingleProject)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jButtonSelectXML.setText("Select Files XML");

        jTextAreaSelectedProjects.setEditable(false);
        jTextAreaSelectedProjects.setColumns(20);
        jTextAreaSelectedProjects.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSelectedProjects);

        jButtonDone.setText("Done");

        javax.swing.GroupLayout jPanelRegisterProjectLayout = new javax.swing.GroupLayout(jPanelRegisterProject);
        jPanelRegisterProject.setLayout(jPanelRegisterProjectLayout);
        jPanelRegisterProjectLayout.setHorizontalGroup(
            jPanelRegisterProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegisterProjectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegisterProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegisterProjectLayout.createSequentialGroup()
                        .addGroup(jPanelRegisterProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelRegisterProjectLayout.createSequentialGroup()
                                .addComponent(jLabelNameProject)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNameProject, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(80, Short.MAX_VALUE))
                    .addGroup(jPanelRegisterProjectLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelRegisterProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSelectXML, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegisterProjectLayout.createSequentialGroup()
                                .addComponent(jButtonDone)
                                .addGap(40, 40, 40))))))
        );
        jPanelRegisterProjectLayout.setVerticalGroup(
            jPanelRegisterProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegisterProjectLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelRegisterProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNameProject)
                    .addComponent(jTextFieldNameProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelRegisterProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelRegisterProjectLayout.createSequentialGroup()
                        .addComponent(jButtonSelectXML)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDone))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFilterSystem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelRegisterProject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFilterSystem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelRegisterProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelRegisterProject.getAccessibleContext().setAccessibleName("Register Project");

        jMenu1.setText("File");

        jMenuItemExit.setText("Exit");
        jMenu1.add(jMenuItemExit);

        jMenuBar.add(jMenu1);

        jMenuTool.setText("Tool");

        jMenuItemCreateDetectionStrategy.setText("Create Detection Strategy");
        jMenuItemCreateDetectionStrategy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreateDetectionStrategyActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemCreateDetectionStrategy);

        jMenuItemUpdateThreshold.setText("Update Threshold");
        jMenuItemUpdateThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUpdateThresholdActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemUpdateThreshold);

        jMenuItemRemoveDetectionStrategy.setText("Remove Detection Strategy");
        jMenuTool.add(jMenuItemRemoveDetectionStrategy);

        jMenuBar.add(jMenuTool);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCreateDetectionStrategyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreateDetectionStrategyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCreateDetectionStrategyActionPerformed

    private void jMenuItemUpdateThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUpdateThresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemUpdateThresholdActionPerformed

    private void jTextFieldNameProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameProjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNameProjectActionPerformed

    private void jRadioButtonOptionMultipleProjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOptionMultipleProjectsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonOptionMultipleProjectsActionPerformed

    private void jRadioButtonOptionSingleProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOptionSingleProjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonOptionSingleProjectActionPerformed

//    private void createJTableWithResults(){
//        this.jTableFilteredArtifacts = new JTable();
//        jTableFilteredArtifacts.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null}
//            },
//            new String [] {
//                "Title 1", "Title 2", "Title 3", "Title 4"
//            }
//        ) {
//            boolean[] canEdit = new boolean [] {
//                false, false, false, false
//            };
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit [columnIndex];
//            }
//        });
//        jTableFilteredArtifacts.getTableHeader().setReorderingAllowed(false);
//        jScrollPane1.setViewportView(jTableFilteredArtifacts);
//    }
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FIlterSystemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FIlterSystemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FIlterSystemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FIlterSystemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FIlterSystemForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonDone;
    private javax.swing.JButton jButtonFilter;
    private javax.swing.JButton jButtonSelectXML;
    private javax.swing.JComboBox jComboBoxDetectionStrategy;
    private javax.swing.JComboBox jComboBoxProject;
    private javax.swing.JLabel jLabelDetectionStrategy;
    private javax.swing.JLabel jLabelNameProject;
    private javax.swing.JLabel jLabelProject;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemCreateDetectionStrategy;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemRemoveDetectionStrategy;
    private javax.swing.JMenuItem jMenuItemUpdateThreshold;
    private javax.swing.JMenu jMenuTool;
    private javax.swing.JPanel jPanelFilterSystem;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelRegisterProject;
    private javax.swing.JRadioButton jRadioButtonOptionMultipleProjects;
    private javax.swing.JRadioButton jRadioButtonOptionSingleProject;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaSelectedProjects;
    private javax.swing.JTextField jTextFieldNameProject;
    // End of variables declaration//GEN-END:variables
//    private javax.swing.JTable jTableFilteredArtifacts;
}
