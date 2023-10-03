/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Dazzle.cell;
  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.util.Collections.list;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class PanelAction extends javax.swing.JPanel {
 
   
    public PanelAction() {
       
        initComponents();
    }
public void initEvent(TableActionEvent event, int row){
    cmdEdit.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
         event.onEdit(row);
        }
        
        
    });
       cmdDelete.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
         event.onDelete(row);
        }
        
        
    });
    cmdView.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
         event.onView(row);
        }
        
        
    });
 
    
    

}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actionButton1 = new Dazzle.cell.ActionButton();
        cmdEdit = new Dazzle.cell.ActionButton();
        cmdView = new Dazzle.cell.ActionButton();
        cmdDelete = new Dazzle.cell.ActionButton();

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dazzle/cell/icons8_pen_20px_1.png"))); // NOI18N
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dazzle/cell/icons8_eye_20px_1.png"))); // NOI18N

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dazzle/cell/icons8_delete_folder_20px_1.png"))); // NOI18N
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cmdView, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(cmdView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdEditActionPerformed
   
    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
  
    }//GEN-LAST:event_cmdDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dazzle.cell.ActionButton actionButton1;
    private Dazzle.cell.ActionButton cmdDelete;
    private Dazzle.cell.ActionButton cmdEdit;
    private Dazzle.cell.ActionButton cmdView;
    // End of variables declaration//GEN-END:variables
}
