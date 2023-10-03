package test;


import Dazzle.cell.PanelAction;
import Dazzle.cell.TableActionCellEditor;
import Dazzle.cell.TableActionCellRender;
import Dazzle.cell.TableActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class Main extends javax.swing.JFrame {
    
   
 
    public Main() {
       
        initComponents();
        
         

   
        PrintStream printStream = new PrintStream(new TextAreaOutputStream(textArea));
        System.setOut(printStream);
        
        
         Date currentDate = new Date();
        datechooser.setDate(currentDate);
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Select row: "+ row);
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                 DefaultTableModel model = (DefaultTableModel)table.getModel();
                   
                int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
       
    } else {
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();


        String titleText = model1.getValueAt(selectedRow, 0).toString().trim();
        String amountText = model1.getValueAt(selectedRow, 1).toString();
        String dateText = model1.getValueAt(selectedRow, 2).toString();

        try {
            double amountValue = Double.parseDouble(amountText);
            totalAmount -= amountValue;
        } catch (NumberFormatException e) {
          
        }
        System.out.println("("+"Newly Deleted Data: "+"Title: "+titleText+", Amount: "+amountText+", Date: "+dateText+")");
        list.delete(titleText);
        list.delete(amountText);
        list.delete(dateText);
      
        Total.setText(Double.toString(totalAmount));
          model.removeRow(row);   

      
    }
          
            }

            @Override
            public void onView(int row) {
              System.out.println("Status: "+ row);
               DefaultTableModel model = (DefaultTableModel) table.getModel();

    
    if (row >= 0 && row < model.getRowCount()) {
        
        Object currentValue = model.getValueAt(row, 3);

      
        if ("Paid".equals(currentValue)) {
            model.setValueAt("Unpaid", row, 3);
        } else {
            model.setValueAt("Paid", row, 3);
        }
        table.repaint();
    }         
            }
        };
        table.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));  
    }
    public Object getModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     public static class Node {
        Record data;
        Node next;

        public Node(Record data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class LinkedList {
        Node head;

        public void add(Record data) {
            Node node = new Node(data);

            if (head == null) {
                head = node;
            } else {
                Node n = head;
                while (n.next != null) {
                    n = n.next;
                }
                n.next = node;
            }
        }

        public void delete(String searchData) {
            if (head == null ) {
                System.out.println("Your Table is empty!");
               return;
            }
            if (head.data.getTitle().equals(searchData)) {
                head = head.next;
                return;
            }
            Node node = head;

            while (node.next != null && !node.next.data.getTitle().equals(searchData)) {
                node = node.next;
            }
            if (node.next != null) {
                node.next = node.next.next;
            }
        }

        public Node search(String searchData) {
            Node node = head;
            while (node != null) {
                if (node.data.getTitle().equals(searchData)) {
                   
                    return node;
                }
                node = node.next;
            }
           
            return null;
        }

        public void display() {
            Node node = head;
            while (node != null) {
                System.out.println(node.data.toString());
                node = node.next;
            }
        }
    }

    public static class Record {
        String title;
        String amount;
        String date;
        String status;

        public Record(String title, String amount, String date, String status) {
            this.title = title;
            this.amount = amount;
            this.date = date;
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Amount: " + amount + ", Date: " + date + ", Status: " + status;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        title = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        status = new javax.swing.JTextField();
        datechooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Expense Tracker");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(68, 80, 105));

        table.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Amount", "Date", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(40);
        table.setSelectionBackground(new java.awt.Color(91, 154, 139));
        jScrollPane1.setViewportView(table);

        title.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        title.setText("Title");
        title.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                titleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                titleFocusLost(evt);
            }
        });
        title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                titleMouseEntered(evt);
            }
        });

        amount.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        amount.setText("0.0");
        amount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                amountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                amountFocusLost(evt);
            }
        });
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountKeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Total.setEditable(false);
        Total.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total");

        status.setEditable(false);
        status.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        status.setText("Ongoing");

        datechooser.setDateFormatString("MMMM dd, yyyy");
        datechooser.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Expense Tracker");

        search.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 0));
        jLabel3.setText("Logs:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(amount, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(datechooser, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(4, 4, 4)))
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datechooser, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private LinkedList list = new LinkedList();
    private double totalAmount;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SimpleDateFormat sdate = new SimpleDateFormat("MMMM dd,yyyy");
        String date3 = sdate.format(datechooser.getDate());
        
        Date currentDate = new Date();
        datechooser.setDate(currentDate);
        if(title.equals("") ||amount.equals("")||date3.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill out all the details!");
        }
        else{
              String date2 = sdate.format(datechooser.getDate());
            String titleText = title.getText();
            String amountText = amount.getText();
            
             String statusText =status.getText();
        
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();
        
         try {
            double amountValue = Double.parseDouble(amountText);
           totalAmount += amountValue; 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount format!");
        }
         
         
         Total.setText(Double.toString(totalAmount));
        // Add individual elements to the linked list
       Record record = new Record(titleText, amountText, date2, statusText);
       list.add(record);
        String data[] = {titleText, amountText,date2,statusText};
        model1.addRow(data);
        System.out.println("("+"Newly Added Data: "+"Title: "+titleText+", Amount: "+amountText+", Date: "+date2+", Status: "+statusText +")");
        
            System.out.println("====================================================================================");
                         System.out.print("                                 ");       System.out.println("Data OverView");
                                                          list.display();
            System.out.println("====================================================================================");
            
          
        
        title.setText("Title");
        amount.setText("0.0");
        
        
        JOptionPane.showMessageDialog(this, "Data Add Successfully!");
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyPressed
       char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            amount.setEditable(false);
        }else{
            amount.setEditable(true);
        }
    }//GEN-LAST:event_amountKeyPressed

    private void titleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleMouseClicked
     
    }//GEN-LAST:event_titleMouseClicked

    private void titleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleFocusGained
        if (title.getText().equals("Title")) {
            title.setText(null);
            title.requestFocus();
            
        }
    }//GEN-LAST:event_titleFocusGained

    private void titleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleMouseEntered
          if (title.equals("Title")) {
            title.setText("");
        }
    }//GEN-LAST:event_titleMouseEntered

    private void titleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleFocusLost
        if (title.getText().equals("")) {
            title.setText("Title");
           
            
        }
    }//GEN-LAST:event_titleFocusLost

    private void amountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_amountFocusGained
        if (amount.getText().equals("0.0")) {
            amount.setText(null);
            amount.requestFocus();
        }
    }//GEN-LAST:event_amountFocusGained

    private void amountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_amountFocusLost
        if (amount.getText().equals("")) {
            amount.setText("0.0");
        }
    }//GEN-LAST:event_amountFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
      TableRowSorter<DefaultTableModel>tablemodel2= new TableRowSorter<>(tablemodel);
      table.setRowSorter(tablemodel2);
      tablemodel2.setRowFilter(RowFilter.regexFilter(search.getText().trim()));
     
        DefaultTableModel model = (DefaultTableModel)table.getModel();
             String searchData = search.getText().trim();

    
    Node foundNode = list.search(searchData);
   
    
    if (foundNode != null) {
         String titleToFilter = foundNode.data.getTitle().trim();

        System.out.println("Found: "+searchData);
        search.setText("");
    } else {
       
       
        System.out.println("Not Found: " + searchData);
         search.setText("");
     
    }
      
            


      
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
//        if (search.getText().equals("Search")) {
//            search.setText("");
//            search.requestFocus();
//        }
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
//        if (search.getText().equals("")) {
//            search.setText("Search");
//        }
    }//GEN-LAST:event_searchFocusLost
 
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Total;
    private javax.swing.JTextField amount;
    private com.toedter.calendar.JDateChooser datechooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField search;
    private javax.swing.JTextField status;
    private javax.swing.JTable table;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField title;
    // End of variables declaration//GEN-END:variables

    private static class TextAreaOutputStream extends java.io.OutputStream {
         private JTextArea textArea;
        public TextAreaOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength()); 
        }
    }
}
