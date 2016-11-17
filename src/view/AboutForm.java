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
public class AboutForm extends javax.swing.JFrame {

    /**
     * Creates new form AboutForm
     */
    public AboutForm() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaText = new javax.swing.JTextArea();

        setTitle("About FindSmells");
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(255, 255, 255));

        jTextAreaText.setEditable(false);
        jTextAreaText.setColumns(20);
        jTextAreaText.setRows(5);
        jTextAreaText.setText("FindSmells 1.0 is a tool for detection of bad smells on XML files with\ncomputed software metrics. The tool aims to provide flexible, large-\nscale detection since strategies may  be  composed  the user and\nrun  without  the  source   code   of   the   system  under  analysis.\nFindSmells allows management of detections strategies through  a \ndata base, and provide the export of detection results.  Therefore,\nthe  tool  may  support  the  development  team   in   assessing  of \nsoftware quality.\n\nFindSmell was developed by:\n- Bruno L. Sousa (UFMG)\n- Priscila P. Souza (UFMG)\n- Eduardo Fernandes (UFMG)\n- Kecia A. M. Ferreira (CEFET-MG)\n- Mariza A. S. Bigonha (UFMG)\n\nThe FindSmell source code can be find in the link:\n\nhttps://github.com/BrunoLSousa/FindSmells");
        jScrollPane1.setViewportView(jTextAreaText);

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaText;
    // End of variables declaration//GEN-END:variables
}