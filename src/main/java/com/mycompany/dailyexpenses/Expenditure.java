package com.mycompany.dailyexpenses;

import com.mycompany.dailyexpenses.constants.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import javax.swing.JOptionPane;

public class Expenditure extends javax.swing.JFrame {

    private String username;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Connection conn = null;
    private final String INSERT_EXPENDITURE_SQL = "INSERT INTO expendituretbl"
            + "  (username, category, amount, date_of_expense) VALUES "
            + " (?,?,?,?);";
    private final String DISPLAY_CATEGORY_SQL = "SELECT category FROM categorytbl WHERE username = ? ORDER BY category ASC";
    private final String DISPLAY_BUDGET_SQL = "SELECT budget FROM userlogintbl WHERE username = ?";

    private final String SUM_EXPENDITURE_SQL = "SELECT SUM(amount) AS SUM_AMOUNT FROM expendituretbl WHERE username = ?";

    /**
     * Creates new form Expenditure
     */
    public Expenditure() {
        initComponents();
    }

    public Expenditure(String username) {
        initComponents();
        this.username = username;
        listData();
    }

    private void listData() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
            ps = conn.prepareStatement(DISPLAY_CATEGORY_SQL);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                String str = rs.getString("category");
                jComboCategory.addItem(str);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jComboCategory = new javax.swing.JComboBox<>();
        jTxtAmount = new javax.swing.JTextField();
        jBtnCategory = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jBtnHistory = new javax.swing.JButton();
        jBtnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Expenditure");

        jComboCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Item" }));
        jComboCategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboCategoryItemStateChanged(evt);
            }
        });
        jComboCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCategoryActionPerformed(evt);
            }
        });

        jTxtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtAmountActionPerformed(evt);
            }
        });

        jBtnCategory.setText("New Category");
        jBtnCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCategoryActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Amount");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Category");

        jBtnHistory.setText("View History");
        jBtnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHistoryActionPerformed(evt);
            }
        });

        jBtnAdd.setText("Add");
        jBtnAdd.setEnabled(false);
        jBtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jBtnHistory))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jBtnAdd)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBtnCategory))
                                .addComponent(jTxtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAdd)
                    .addComponent(jBtnCategory))
                .addGap(18, 18, 18)
                .addComponent(jBtnHistory)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboCategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboCategoryItemStateChanged
        if (jComboCategory.getSelectedIndex() > 0) {
            jBtnAdd.setEnabled(true);
        } else {
            jBtnAdd.setEnabled(false);
        }
    }//GEN-LAST:event_jComboCategoryItemStateChanged

    private void jComboCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCategoryActionPerformed

    }//GEN-LAST:event_jComboCategoryActionPerformed

    private void jTxtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtAmountActionPerformed

    }//GEN-LAST:event_jTxtAmountActionPerformed

    private void jBtnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCategoryActionPerformed
        openNewCategory();
    }//GEN-LAST:event_jBtnCategoryActionPerformed

    private void jBtnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHistoryActionPerformed
        openViewHistory();
    }//GEN-LAST:event_jBtnHistoryActionPerformed

    private void jBtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddActionPerformed
        String category = jComboCategory.getSelectedItem().toString();
        double total = 0;
        double amount = 0;
        double budget = 0;
        try {
            amount = Double.parseDouble(jTxtAmount.getText().trim());
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
                ps = conn.prepareStatement(DISPLAY_BUDGET_SQL);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {
                    budget = rs.getDouble("budget");
                }
                ps = conn.prepareStatement(SUM_EXPENDITURE_SQL);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getDouble("SUM_AMOUNT");
                }
                if (amount + total <= budget) {
                    Timestamp from = Timestamp.from(Instant.now());
                    ps = conn.prepareStatement(INSERT_EXPENDITURE_SQL);
                    ps.setString(1, username);
                    ps.setString(2, category);
                    ps.setDouble(3, amount);
                    ps.setTimestamp(4, from);
                    ps.executeUpdate();
                    ps.close();
                    conn.close();
                    JOptionPane.showMessageDialog(jBtnAdd, "Data Saved");
                    jTxtAmount.setText("");
                    jBtnAdd.setEnabled(false);
                    jComboCategory.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(jBtnAdd, "You have pass your budget limit");
                }
                ps.close();
                rs.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(jBtnAdd, "Enter a valid amount");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jBtnAddActionPerformed

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
            java.util.logging.Logger.getLogger(Expenditure.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Expenditure.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Expenditure.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Expenditure.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Expenditure().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdd;
    private javax.swing.JButton jBtnCategory;
    private javax.swing.JButton jBtnHistory;
    private javax.swing.JComboBox<String> jComboCategory;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTxtAmount;
    // End of variables declaration//GEN-END:variables

    private void openViewHistory() {
        setVisible(false);
        new ViewHistory(username).setVisible(true);
    }

    private void openNewCategory() {
        setVisible(false);
        new NewCategory(username).setVisible(true);
    }
}