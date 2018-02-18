/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busticket;

import DAO.Factory;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import logic.Review;
import logic.Route;

/**
 *
 * @author Admin
 */
public class AddReview extends javax.swing.JFrame {

    /**
     * Creates new form AddReview
     */
    ArrayList<Integer> ID = new ArrayList<Integer>();
    int gradeOfUser = 0;
    int indeX;
    public AddReview() {
        this.setLocation(500, 250);
        try {
            //ID.clear();
            initComponents();
            routes.removeAllItems();
            Collection allRoute = Factory.getInstance().getRouteDAO().getAllRoute();
            Iterator it = allRoute.iterator();
            while(it.hasNext())
            {
                Route rout = (Route) it.next();
                ID.add(rout.getIdRoute());
                routes.addItem(rout.getStart()+ " - " + rout.getEnd()+" "+rout.getDateStart());
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddReview.class.getName()).log(Level.SEVERE, null, ex);
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

        routes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        documentUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        addReview = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        reviewFor = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setTitle("AddReview");
        setResizable(false);

        routes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        routes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                routesItemStateChanged(evt);
            }
        });

        jLabel1.setText("AllRoutes:");

        jLabel2.setText("Document:");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Review:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel4.setText("Grade:");

        addReview.setText("AddReview");
        addReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReviewActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("Reviews for:");

        reviewFor.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        jButton1.setText("BackToBusTickets");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reviewFor, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 98, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(documentUser))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(jScrollPane1)
                    .addComponent(routes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addReview, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reviewFor, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(routes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(documentUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addReview, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void routesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_routesItemStateChanged
        List<String> userDoc = new ArrayList<>();
        List<Integer> userGrade = new ArrayList<>();
        List<String> userReview = new ArrayList<>();
        jPanel1.removeAll();
        userDoc.clear();
        userGrade.clear();
        userReview.clear();
        if (routes.getItemCount() >= 0)  {
            if (evt.getStateChange()==ItemEvent.SELECTED) 
            {
                int f = 0;
                try {
                    
                    indeX = routes.getSelectedIndex();
                    reviewFor.setText(routes.getItemAt(indeX));
                    Collection allReview = Factory.getInstance().getReviewDAO().getReviewFor(ID.get(indeX));
                    Iterator it = allReview.iterator();
                    while(it.hasNext())
                    {
                        Review rew = (Review) it.next();
                        //System.out.println(rew.getReview()+" "+rew.getDocumentUsers()+" "+rew.getGrade() /*+ ID.get(index)*/);
                        
                        userDoc.add(rew.getDocumentUsers());
                        userGrade.add(rew.getGrade());
                        userReview.add(rew.getReview());
                        
                        
                        
                    }
                   System.out.println(f = userDoc.size());
                } catch (SQLException ex) {
                    Logger.getLogger(AddReview.class.getName()).log(Level.SEVERE, null, ex);
                }
                jPanel1.setLayout(new GridLayout(3,1));
                for (int i = 0; i < f; i++) {
                    //System.out.println(userDoc.get(i)+"   "+userGrade.get(i)+"   "+userReview.get(i));
                    JLabel users[] = new JLabel[f];
                    users[i] = new JLabel("User: "+userDoc.get(i));
                    System.out.println("User: "+userDoc.get(i));
                    users[i].setFont(new Font(users[i].getName(), Font.PLAIN, 14));
                    jPanel1.add(users[i]);               
                    
                }
                for (int i = 0; i < f; i++) {
                    JLabel grade[] = new JLabel[f];
                    grade[i] =  new JLabel("Grade: "+userGrade.get(i));
                    System.out.println("Grade: "+userGrade.get(i));
                    grade[i].setFont(new Font(grade[i].getName(), Font.PLAIN, 14));
                    jPanel1.add(grade[i]);   
                }
                for (int i = 0; i < f; i++) {
                    JLabel reviewText[] = new JLabel[f];
                    reviewText[i] = new JLabel("Review\n: "+userReview.get(i));
                    System.out.println("Review\n: "+userReview.get(i));
                    reviewText[i].setFont(new Font(reviewText[i].getName(), Font.PLAIN, 16));
                    jPanel1.add(reviewText[i]);
                    
                    
                }
            }
       }
                        
    }//GEN-LAST:event_routesItemStateChanged

    private void addReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReviewActionPerformed
        try {
            String userDocs = null;
            userDocs = documentUser.getText();
            String userReview = jTextArea1.getText();
            Collection findSetReview = Factory.getInstance().getReviewDAO().getInfoByDoc(userDocs, ID.get(indeX));
            if(userDocs.equals("")){
                JOptionPane.showMessageDialog(null, "The document can not be empty ", "Error ", JOptionPane.OK_OPTION);
            }
            else
            {
                if(!findSetReview.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "You have already left a review ", "Error ", JOptionPane.OK_OPTION);
                        //System.out.println("Yes");
                    }
                    else
                    {
                       Route route = Factory.getInstance().getRouteDAO().getRouteByID(ID.get(indeX));
                       Review rew = new Review();
                       rew.setDocumentUsers(userDocs);
                       rew.setGrade(gradeOfUser);
                       rew.setReview(userReview);
                       rew.setRoute(route);
                       Factory.getInstance().getReviewDAO().addReview(rew);
                    }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddReview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addReviewActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (routes.getItemCount() >= 0)  {
            if (evt.getStateChange()==ItemEvent.SELECTED) 
            {
                int index = jComboBox1.getSelectedIndex();
                gradeOfUser = Integer.parseInt(jComboBox1.getItemAt(index));
            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            this.setVisible(false);
            BusTicket bt = new BusTicket();
            bt.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AddReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddReview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addReview;
    private javax.swing.JTextField documentUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel reviewFor;
    private javax.swing.JComboBox<String> routes;
    // End of variables declaration//GEN-END:variables
}