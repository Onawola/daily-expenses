package com.mycompany.dailyexpenses;

import com.mycompany.dailyexpenses.constants.Constants;
import com.mycompany.dailyexpenses.models.Expense;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ViewHistory extends javax.swing.JFrame {

    private String username;
    private final String DISPLAY_TODAY_SQL = "SELECT username, category, amount, date_of_expense FROM expendituretbl WHERE date_of_expense = (current_date) AND username = ?";
    private final String DISPLAY_YESTERDAY_SQL = "SELECT username, category, amount, date_of_expense FROM expendituretbl WHERE date_of_expense <= now() - INTERVAL '1 DAY' AND username = ?";
    private final String DISPLAY_LASTWEEK_SQL = "SELECT username, category, amount, date_of_expense FROM expendituretbl WHERE date_of_expense <= now() - INTERVAL '7 DAY' AND username = ?";
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private DefaultTableModel expenditureModel = new DefaultTableModel();

    /**
     * Creates new form ViewHistory
     */
    public ViewHistory() {
        initComponents();
    }

    public ViewHistory(String username) {
        initComponents();
        this.username = username;
        addTable();
        listData(DISPLAY_TODAY_SQL);
    }

    private void listData(String time) {
        List<Expense> expenses = getAllExpenses(time);
        for (Expense expense : expenses) {
            expenditureModel.addRow(new Object[]{expense.getUsernameE(), expense.getCategory(), expense.getAmount(), expense.getDate()});
        }
    }

    public List<Expense> getAllExpenses(String time) {
        List<Expense> expenses = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
            ps = conn.prepareStatement(time);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                String usernameE = rs.getString("username");
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                Date dateOfExpense = rs.getTimestamp("date_of_expense");
                Expense expense = new Expense(usernameE, category, amount, dateOfExpense);
                expenses.add(expense);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expenses;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jComboBoxTime = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("View History");

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(historyTable);

        jComboBoxTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Today", "Yesterday", "Last Week" }));
        jComboBoxTime.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTimeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jComboBoxTime, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        openExpenditure();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxTimeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTimeItemStateChanged
        String value = jComboBoxTime.getSelectedItem().toString();
        switch (value) {
            case "Today":
                todayList();
                break;
            case "Yesterday":
                yesterdayList();
                break;
            case "Last Week":
                lastWeekList();
                break;

        }
    }//GEN-LAST:event_jComboBoxTimeItemStateChanged

    /*
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
            java.util.logging.Logger.getLogger(ViewHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable historyTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void openExpenditure() {
        setVisible(false);
        new Expenditure(username).setVisible(true);
    }

    private void todayList() {
        if (expenditureModel.getRowCount() > 0) {
            for (int i = expenditureModel.getRowCount() - 1; i > -1; i--) {
                expenditureModel.removeRow(i);
            }
        }

        listData(DISPLAY_TODAY_SQL);
    }

    private void yesterdayList() {
        if (expenditureModel.getRowCount() > 0) {
            for (int i = expenditureModel.getRowCount() - 1; i > -1; i--) {
                expenditureModel.removeRow(i);
            }
        }
        listData(DISPLAY_YESTERDAY_SQL);
    }

    private void lastWeekList() {
        if (expenditureModel.getRowCount() > 0) {
            for (int i = expenditureModel.getRowCount() - 1; i > -1; i--) {
                expenditureModel.removeRow(i);
            }
        }
        listData(DISPLAY_LASTWEEK_SQL);

    }

    private void addTable() {
        expenditureModel.addColumn("Username");
        expenditureModel.addColumn("Category");
        expenditureModel.addColumn("Amount");
        expenditureModel.addColumn("Date of Expense");
        historyTable.setModel(expenditureModel);
    }
}
