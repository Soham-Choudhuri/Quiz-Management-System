/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author soham
 */
import Project.ConnectionProvider;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class quizExamStudent extends javax.swing.JFrame {

    public String questionID = "1";
    public String answer;
    public int min = 0;
    public int sec = 0;
    public int marks = 0;

    public void answerCheck() {
        String studentAnswer = "";
        if (jRadioButton1.isSelected()) {
            studentAnswer = jRadioButton1.getText();
        } else if (jRadioButton2.isSelected()) {
            studentAnswer = jRadioButton2.getText();
        } else if (jRadioButton3.isSelected()) {
            studentAnswer = jRadioButton3.getText();
        } else {
            studentAnswer = jRadioButton4.getText();
        }

        if (studentAnswer.equals(answer)) {
            marks = marks + 1;
            String showmarks = String.valueOf(marks);
            jLabelShowMarks.setText(showmarks);
        }

        // clear input of radio buttons
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            ResultSet rsCount = st.executeQuery("SELECT COUNT(*) FROM question");
            rsCount.next();
            int totalQuestions = rsCount.getInt(1);

            int showQuestionID = Integer.parseInt(questionID) + 1;
            questionID = String.valueOf(showQuestionID);

            if (showQuestionID > totalQuestions) {
                jButtonNext.setVisible(false);
            } else {
                question();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void question() {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            ResultSet rsQuestion = st.executeQuery("select *from question where id='" + questionID + "'");
            if (rsQuestion.next()) {
                jLabelShowQuestionID.setText(rsQuestion.getString(1));
                jLabelQuestionName.setText(rsQuestion.getString(2));
                jRadioButton1.setText(rsQuestion.getString(3));
                jRadioButton2.setText(rsQuestion.getString(4));
                jRadioButton3.setText(rsQuestion.getString(5));
                jRadioButton4.setText(rsQuestion.getString(6));
                answer = rsQuestion.getString(7);
            }

            ResultSet rsCount = st.executeQuery("SELECT COUNT(*) FROM question");
            rsCount.next();
            int totalQuestions = rsCount.getInt(1);
            if (totalQuestions == 1) {
                jButtonNext.setVisible(false);
            } else {
                if (Integer.parseInt(questionID) == totalQuestions) {
                    jButtonNext.setVisible(false);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Submit() {
        String phoneString = jLabel_ShowPhone.getText();
        answerCheck();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("update student set marks='" + marks + "' where phone='" + phoneString + "'");
            new quizSubmission(phoneString).setVisible(true);
            setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Creates new form quizExamStudent
     */
    public quizExamStudent() {
        initComponents();
    }

    Timer time;

    public quizExamStudent(String getPhone) {
        initComponents();

        // date
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        jLabelShowDate.setText(dFormat.format(date));
        jLabel_ShowPhone.setText(getPhone);

        // first question & student details
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from student where phone='" + getPhone + "'");
            while (rs.next()) {
                jLabelUser.setText(rs.getString(2) + " " + rs.getString(3));
                jLabelShowRollNo.setText(rs.getString(1));
            }

            ResultSet rsQuestion = st.executeQuery("select *from question where id='" + questionID + "'");
            while (rsQuestion.next()) {
                jLabelShowQuestionID.setText(rsQuestion.getString(1));
                jLabelQuestionName.setText(rsQuestion.getString(2));
                jRadioButton1.setText(rsQuestion.getString(3));
                jRadioButton2.setText(rsQuestion.getString(4));
                jRadioButton3.setText(rsQuestion.getString(5));
                jRadioButton4.setText(rsQuestion.getString(6));
                answer = rsQuestion.getString(7);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        // timer
        setLocationRelativeTo(this);
        time = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jLabelTAM.setText(String.valueOf(min));
                jLabelTAS.setText(String.valueOf(sec));

                if (sec == 60) {
                    sec = 0;
                    min = min + 1;
                    if (min == 10) {
                        time.stop();
                        answerCheck();
                        Submit();
                    }
                }
                sec++;
            }
        });
        time.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabelUser = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelShowDate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelShowRollNo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabelTAM = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabelQuestionName = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jButtonSubmit = new javax.swing.JButton();
        jLabelShowQuestionID = new javax.swing.JLabel();
        jLabelTAS = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelShowMarks = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel_ShowPhone = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(926, 546));
        setMinimumSize(new java.awt.Dimension(926, 546));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(926, 546));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUser.setBackground(new java.awt.Color(0, 0, 0));
        jLabelUser.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabelUser.setText("User");
        getContentPane().add(jLabelUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Date:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        jLabelShowDate.setBackground(new java.awt.Color(0, 0, 0));
        jLabelShowDate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelShowDate.setText("current_date");
        getContentPane().add(jLabelShowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, -1, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Time Allotted:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, -1, -1));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("total_time");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 30, -1, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Roll:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 54, -1, -1));

        jLabelShowRollNo.setBackground(new java.awt.Color(0, 0, 0));
        jLabelShowRollNo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelShowRollNo.setText("user_roll");
        getContentPane().add(jLabelShowRollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 54, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 82, 926, 10));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Time Elasped:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, -1, -1));

        jLabelTAM.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTAM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelTAM.setText("00");
        getContentPane().add(jLabelTAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, -1, -1));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setText("Question No:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 98, -1, -1));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("00");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 98, -1, -1));

        jLabelQuestionName.setBackground(new java.awt.Color(0, 0, 0));
        jLabelQuestionName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelQuestionName.setText("Question Sample?");
        getContentPane().add(jLabelQuestionName, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 209, -1, -1));

        jRadioButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton1.setText("Answer Sample 1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 249, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton2.setText("Answer Sample 2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 289, -1, -1));

        jRadioButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton3.setText("Answer Sample 3");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 329, -1, -1));

        jRadioButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton4.setText("Answer Sample 4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 369, -1, -1));

        jButtonPrev.setBackground(new java.awt.Color(153, 204, 255));
        jButtonPrev.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonPrev.setText("Previous");
        jButtonPrev.setAlignmentY(0.0F);
        getContentPane().add(jButtonPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 434, -1, -1));

        jButtonNext.setBackground(new java.awt.Color(153, 204, 255));
        jButtonNext.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonNext.setText("Next");
        jButtonNext.setAlignmentY(0.0F);
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 434, 109, -1));

        jButtonSubmit.setBackground(new java.awt.Color(255, 51, 51));
        jButtonSubmit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSubmit.setText("Finish & See Result");
        jButtonSubmit.setAlignmentY(0.0F);
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 481, -1, -1));

        jLabelShowQuestionID.setBackground(new java.awt.Color(0, 0, 0));
        jLabelShowQuestionID.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelShowQuestionID.setText("00");
        getContentPane().add(jLabelShowQuestionID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 98, -1, -1));

        jLabelTAS.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTAS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelTAS.setText("00");
        getContentPane().add(jLabelTAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, -1, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Marks Obtained:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 126, -1, -1));

        jLabelShowMarks.setBackground(new java.awt.Color(0, 0, 0));
        jLabelShowMarks.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelShowMarks.setText("00");
        getContentPane().add(jLabelShowMarks, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 126, -1, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("/");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 98, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Phone: ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        jLabel_ShowPhone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel_ShowPhone.setText("user_phone");
        getContentPane().add(jLabel_ShowPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonNextActionPerformed
        question();
        answerCheck();
    }// GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonSubmitActionPerformed
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to submit?", "Select",
                JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            answerCheck();
            Submit();
        }
    }// GEN-LAST:event_jButtonSubmitActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }// GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton2ActionPerformed
        if (jRadioButton2.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }// GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton3ActionPerformed
        if (jRadioButton3.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }// GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton4ActionPerformed
        if (jRadioButton4.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
        }
    }// GEN-LAST:event_jRadioButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(quizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(quizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(quizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(quizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new quizExamStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelQuestionName;
    private javax.swing.JLabel jLabelShowDate;
    private javax.swing.JLabel jLabelShowMarks;
    private javax.swing.JLabel jLabelShowQuestionID;
    private javax.swing.JLabel jLabelShowRollNo;
    private javax.swing.JLabel jLabelTAM;
    private javax.swing.JLabel jLabelTAS;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabel_ShowPhone;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
