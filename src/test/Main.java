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
    public double totalAmount =0.0;
    public Main() {
       
        initComponents();

        PrintStream printStream = new PrintStream(new TextAreaOutputStream(textArea));
        System.setOut(printStream);

         Date currentDate = new Date();
        datechooser.setDate(currentDate);
        TableActionEvent event = new TableActionEvent() {
            
//            @Override
//            public void onEdit(int row) {
//             System.out.println("Select row: " + row);
////            SimpleDateFormat sdate = new SimpleDateFormat("MMMM dd, yyyy");
////            String date3 = sdate.format(datechooser.getDate());
////
////    
////    String date2 = sdate.format(datechooser.getDate());
////    String titleText = title.getText().trim();
////    String amountText = amount.getText().trim();
////    String statusText = status.getText().trim();
////
////    DefaultTableModel model1 = (DefaultTableModel) table.getModel();
////    String amountT = (String)table.getValueAt(row, 1);
////    
////             
////     
////
////    if (titleText.equals("") || amountText.equals("") || date3.isEmpty()) {
////        // Handle the case when any of the fields are empty
////    } else {
////        int rowIndex = table.getSelectedRow();
////        
////        if (rowIndex != -1) {
////            String oldTitle = (String) table.getValueAt(rowIndex, 0);
////
////            
////            Node nodeToUpdate = list.search(oldTitle);
////
////            if (nodeToUpdate != null) {
////               
////                Record record = nodeToUpdate.data;
////
////             
////                record.setTitle(titleText);
////              
////                record.setDate(date2);
////                record.setStatus(statusText);
////
////             
////                table.setValueAt(titleText, rowIndex, 0);
////                table.setValueAt(amountText, rowIndex, 1);
////                table.setValueAt(date2, rowIndex, 2);
////                table.setValueAt(statusText, rowIndex, 3);
////                    
////          
////                    double oldAmountValue = Double.parseDouble(amountT);
////                    double newAmountValue = Double.parseDouble(amountText);
////  
////                        totalAmount=totalAmount+newAmountValue-oldAmountValue;
////       
////   
////                Total.setText(Double.toString(totalAmount));
////
////                System.out.println("Updated Data: Title: " + titleText + ", Amount: " + amountText + ", Date: " + date2 + ", Status: " + statusText);
////            } else {
////                System.out.println("Data not found for updating: " + oldTitle);
////            }
////        }
////
////        title.setText("Title");
////        amount.setText("0.0");
////    }
//    
//            }

            @Override
      public void onDelete(int row) {
         if (table.isEditing()) {
              table.getCellEditor().stopCellEditing();}
         
           DefaultTableModel model = (DefaultTableModel)table.getModel();
                int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
           
    } else {
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();

        String titleText = model1.getValueAt(selectedRow, 0).toString().trim();
        String amountText = model1.getValueAt(selectedRow, 1).toString();
        String dateText = model1.getValueAt(selectedRow, 2).toString();
       
        System.out.println("("+"Newly Deleted Data: "+"Title: "+titleText+", Amount: "+amountText+", Date: "+dateText+")");
        list.delete(titleText);
        list.delete(amountText);
        list.delete(dateText);
         
        
        if (row >= 0 && row < model.getRowCount()) {
         
        Object currentValue = model.getValueAt(row, 3);

      
        if ("Paid".equals(currentValue)) {
            model.setValueAt("Unpaid", row, 3);
             double newAmountvalue = Double.parseDouble(amountText);
            totalAmount+=newAmountvalue;
            
            Total.setText(Double.toString(totalAmount));
        } else {
  
            Total.setText(Double.toString(totalAmount));
        }
     
        if (totalAmount <0) {
            totalAmount = 0.0;
             Total.setText(Double.toString(totalAmount));
        }else{
            
        }
        //if paid just delete the data else totalamount - newam
           double newam = Double.parseDouble(amountText);
            totalAmount-=newam;
       Total.setText(Double.toString(totalAmount));
            }
          model.removeRow(row);  
            }      
            }

            @Override
     public void onView(int row) {
            System.out.println("Status: "+ row);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
              
            String amountText = (String)table.getValueAt(row, 1);   
              if (row >= 0 && row < model.getRowCount()) {
                Object currentValue = model.getValueAt(row, 3);

        if ("Paid".equals(currentValue)) {
            model.setValueAt("Unpaid", row, 3);
            System.out.println("Status Change to (Unpaid)");
            double newAmountvalue = Double.parseDouble(amountText);
            totalAmount+=newAmountvalue;
            Total.setText(Double.toString(totalAmount));
        } else {
            model.setValueAt("Paid", row, 3);
            System.out.println("Status Change to (Paid)");
            double newAmountvalue = Double.parseDouble(amountText);
            totalAmount-=newAmountvalue;
            Total.setText(Double.toString(totalAmount));
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
       public void update(String oldTitle, Record newData) {
    Node node = head;

    while (node != null) {
        if (node.data.getTitle().equals(oldTitle)) {
            node.data = newData;
            return;
        }
        node = node.next;
    }

    System.out.println("Data not found for updating: " + oldTitle);
}
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
        public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

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
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        Total = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        title = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        amount = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        datechooser = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        status = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Expense Tracker");

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        table.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Amount", "Date", "Status", "Update Status/Delete"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(43);
        table.setSelectionBackground(new java.awt.Color(91, 154, 139));
        table.setShowVerticalLines(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(4).setMinWidth(120);
            table.getColumnModel().getColumn(4).setMaxWidth(120);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 121, 208));
        jLabel1.setText("Total:");

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
        textArea.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        Total.setFont(new java.awt.Font("Courier New", 3, 18)); // NOI18N
        Total.setForeground(new java.awt.Color(0, 121, 208));
        Total.setText("0.0");

        jPanel2.setBackground(new java.awt.Color(255, 204, 102));

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));

        title.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 153, 0));

        amount.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(amount)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(amount, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 153, 0));

        datechooser.setDateFormatString("MMMM dd, yyyy");
        datechooser.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datechooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(datechooser, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 153, 0));

        status.setEditable(false);
        status.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        status.setText("Ongoing");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jPanel7.setBackground(new java.awt.Color(255, 153, 0));

        add.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(112, 112, 112))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(325, 325, 325))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 0));
        jLabel3.setText("Logs:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 121, 208));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/icons8_sun_35px.png"))); // NOI18N
        jLabel4.setText("Expense Tracker");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 889, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(38, 38, 38))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private LinkedList list = new LinkedList();
    

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        SimpleDateFormat sdate = new SimpleDateFormat("MMMM dd, yyyy");
        String date3 = sdate.format(datechooser.getDate());
       
        if(title.equals("") ||amount.equals("")||date3.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill out all the details!");
        }
        else{
            String date2 = sdate.format(datechooser.getDate());
            String titleText = title.getText().trim();
            String amountText = amount.getText().trim();
            String statusText =status.getText().trim();
        
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();
           double amountValue = Double.parseDouble(amountText);
           totalAmount += amountValue; 

         Total.setText(Double.toString(totalAmount));
       
            Record record = new Record(titleText, amountText, date2, statusText);
            list.add(record);
            String data[] = {titleText, amountText,date2,statusText};
            model1.addRow(data);
            System.out.println("("+"Newly Added Data: "+"Title: "+titleText+", Amount: "+amountText+", Date: "+date2+", Status: "+statusText +")");
        
            System.out.println("================================================================================================================");
                         System.out.print("                                 ");       System.out.println("Data OverView");
                                                          list.display();
            System.out.println("================================================================================================================");
            
           title.setText("Title");
           amount.setText("0.0");
 
        JOptionPane.showMessageDialog(this, "Data Add Successfully!");
        }    
    }//GEN-LAST:event_addActionPerformed

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

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
//       DefaultTableModel model = (DefaultTableModel) table.getModel();
//       int i = table.getSelectedRow();
//        try {
//            
//            title.setText(model.getValueAt(i, 0).toString());
//            amount.setText(model.getValueAt(i, 1).toString());
//            Date date_pass = new SimpleDateFormat("MMMM dd, yyyy").parse((String)model.getValueAt(i, 2));
//            status.setText(model.getValueAt(i, 3).toString());
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_tableMouseClicked
 
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
    private javax.swing.JLabel Total;
    private javax.swing.JButton add;
    private javax.swing.JTextField amount;
    private com.toedter.calendar.JDateChooser datechooser;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
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
