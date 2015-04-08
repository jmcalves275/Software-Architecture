
import artifact.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/******************************************************************************
* File:NewJFrame.java
* Course: 17655
* Project: Assignment 2
* Copyright: Copyright (c) 2009 Carnegie Mellon University
* Versions:
*	1.0 November 2009 - Initial rewrite of original assignment 2 (ajl).
*
* This class defines a GUI application that allows EEP order takers to enter
* phone orders into the database. 
*
******************************************************************************/

/**
 *
 * @author lattanze
 */
public class NewJFrame extends javax.swing.JFrame {

    String versionID = "v2.10.10";

    LoginInterfaceService bis = new LoginInterfaceService();
    LoginInterface client = bis.getLoginInterfacePort();
		
    OrderInterfaceService ois = new OrderInterfaceService();
    OrderInterface order = ois.getOrderInterfacePort();
    
    InventoryInterfaceService iis = new InventoryInterfaceService();
    InventoryInterface inventory = iis.getInventoryInterfacePort();

    /** Creates new form NewJFrame */
    public NewJFrame() {
        initComponents();
        jLabel1.setText("Order Management Application " + versionID);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        loginButton = new javax.swing.JToggleButton();
        logoutButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Order Management Application");

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Trees");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Seeds");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Shrubs");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Press Button For Inventory Display");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel6.setText("First Name");

        jLabel7.setText("Last Name");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel8.setText("Phone #");

        jLabel5.setText("Items Selected:");

        jButton4.setText("Add to Order");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel9.setText("Order Total Cost:");

        jTextField6.setText("$0");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton5.setText("Submit Order");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel10.setText("Product ID : Product Description : Price : Items in Stock");

        jLabel2.setText("Messages:");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel3.setText("Customer Information");

        jLabel12.setText("Address");

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jLabel13.setText("Username: ");

        jLabel14.setText("Password:");

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addGap(32, 32, 32)
                                                .addComponent(jButton2))
                                            .addComponent(jLabel4))
                                        .addGap(24, 24, 24)
                                        .addComponent(jButton3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(161, 161, 161)
                                        .addComponent(jButton5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2)))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField6)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(jLabel1)))
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutButton)
                    .addComponent(loginButton))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12))
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jButton5)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // jButton1 is responsible for querying the inventory database and
        // getting the tree inventory. Once retieved, the tree inventory is
        // displayed in jTextArea1. From here the user can select an inventory
        // item by triple clicking the item.

        // BE001 : Box Elder : $36.00 : 400 units in stock

        if(!isLoggedIn){
            jTextArea3.append( "Not logged in\n");
            return;
        }
        
        // BE001 : Box Elder : $36.00 : 400 units in stock
        ArrayList<Inventory> trees = (ArrayList<Inventory>) inventory.getInventory("TREES",username.getText(),token).getListaInventario();
        

        for(Inventory i : trees)
        	jTextArea1.append(i.getCode() + " : " + i.getDescription() + " : $" + i.getPrice() + " : " + i.getQuantity() + " units in stock\n");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // This button gets the selected line of text from the
        // inventory list window jTextArea1. The line of text is parsed and
        // the relevant information is placed in the order area (jTextArea2).

        if(!isLoggedIn){
            jTextArea3.append( "Not logged in\n");
            return;
        }
        
        int beginIndex;                     // Parsing index
        int endIndex;                       // Parsing index
        Float fCost;                        // Item cost
        String productDescription = null;   // Product description
        String productID = null;            // Product ID pnemonic
        String sCost,sTotalCost;            // String order and total cost values

        String inventorySelection = null;

        // this is the selected line of text
        inventorySelection =  jTextArea1.getSelectedText();

        // make sure its not blank
        if (inventorySelection.length() > 0 )
        {
            // get the product ID
            beginIndex = 0;
            endIndex = inventorySelection.indexOf(" : ",beginIndex);
            productID = inventorySelection.substring(beginIndex,endIndex);

            // get the product description
            beginIndex = endIndex + 3; //skip over " : "
            endIndex = inventorySelection.indexOf(" : ",beginIndex);
            productDescription = inventorySelection.substring(beginIndex,endIndex);

            // get the string cost value
            beginIndex = endIndex + 4; //skip over " : $"
            endIndex = inventorySelection.indexOf(" : ",beginIndex);
            sCost = inventorySelection.substring(beginIndex,endIndex);

            // write the string to the order area

            jTextArea2.append( productID + " : " + productDescription + " : $"
                    + sCost + "\n");

            // convert the string cost to a float, add it to what is in the
            // cost field (jTextField6), and update the cost field with the
            // new value

            sTotalCost = jTextField6.getText();
            beginIndex = 0;
            beginIndex = sTotalCost.indexOf("$",beginIndex)+1;
            sTotalCost = sTotalCost.substring(beginIndex, sTotalCost.length());
            fCost = Float.parseFloat(sTotalCost) + Float.parseFloat(sCost);
            jTextField6.setText( "$" + fCost.toString());
            
        } // Blank string check
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // This is the submit order button. This handler will check to make sure
        // that the customer information is provided, then create an entry in
        // the orderinfo::orders table. It will also create another table where
        // the list of items is stored. This table is also in the orderinfo
        // database as well.
        
        if(!isLoggedIn){
            jTextArea3.append( "Not logged in\n");
            return;
        }
        
        int beginIndex;                 // String parsing index
        Boolean connectError = false;   // Error flag
        String customerAddress;         // Buyers mailing address
        int endIndex;                   // String paring index
        String firstName = null;        // Customer's first name
        Connection DBConn = null;       // MySQL connection handle
        float fCost;                    // Total order cost
        String description;             // Tree, seed, or shrub description
        Boolean executeError = false;   // Error flag
        String errString = null;        // String for displaying errors
        int executeUpdateVal;           // Return value from execute indicating effected rows
        String lastName = null;         // Customer's last name
        String msgString = null;        // String for displaying non-error messages
        String orderTableName = null;   // This is the name of the table that lists the items
        String sTotalCost = null;       // String representing total order cost
        String sPerUnitCost = null;     // String representation of per unit cost
        String orderItem = null;        // Order line item from jTextArea2
        String phoneNumber = null;      // Customer phone number
        Float perUnitCost;              // Cost per tree, seed, or shrub unit
        String productID = null;        // Product id of tree, seed, or shrub
        Statement s = null;             // SQL statement pointer
        String SQLstatement = null;     // String for building SQL queries

        // Check to make sure there is a first name, last name, address and phone
        if ((jTextField3.getText().length()>0) && (jTextField4.getText().length()>0) && (jTextField5.getText().length()>0) && (jTextArea4.getText().length()>0))
        {
            
       

            Calendar rightNow = Calendar.getInstance();

            int TheHour = rightNow.get(rightNow.HOUR_OF_DAY);
            int TheMinute = rightNow.get(rightNow.MINUTE);
            int TheSecond = rightNow.get(rightNow.SECOND);
            int TheDay = rightNow.get(rightNow.DAY_OF_WEEK);
            int TheMonth = rightNow.get(rightNow.MONTH);
            int TheYear = rightNow.get(rightNow.YEAR);
            orderTableName = "order" + String.valueOf(rightNow.getTimeInMillis());

            String dateTimeStamp = TheMonth + "/" + TheDay + "/" + TheYear + " "
                    + TheHour + ":" + TheMinute  + ":" + TheSecond;

            // Get the order data
            firstName = jTextField3.getText();
            lastName = jTextField4.getText();
            phoneNumber = jTextField5.getText();
            customerAddress = jTextArea4.getText();
            sTotalCost = jTextField6.getText();
            beginIndex = 0;
            beginIndex = sTotalCost.indexOf("$",beginIndex)+1;
            sTotalCost = sTotalCost.substring(beginIndex, sTotalCost.length());
            fCost = Float.parseFloat(sTotalCost);
                

                    SQLstatement = ( "INSERT INTO orders (order_date, " + "first_name, " +
                        "last_name, address, phone, total_cost, shipped, " +
                        "ordertable) VALUES ( '" + dateTimeStamp + "', " +
                        "'" + firstName + "', " + "'" + lastName + "', " +
                        "'" + customerAddress + "', " + "'" + phoneNumber + "', " +
                        fCost + ", " + false + ", '" + orderTableName +"' );");



            ArrayList<Product> listaProdutos = new ArrayList<>();


            // Now we create a table that contains the itemized list
            // of stuff that is associated with the order

            String[] items = jTextArea2.getText().split("\\n");

            for (int i = 0; i < items.length; i++ )
            {
                orderItem = items[i];
                jTextArea3.append("\nitem #:" + i + ": " + items[i]);

                // Check just to make sure that a blank line was not stuck in
                // there... just in case.
                
                if (orderItem.length() > 0 )
                {
                    // Parse out the product id
                    beginIndex = 0;
                    endIndex = orderItem.indexOf(" : ",beginIndex);
                    productID = orderItem.substring(beginIndex,endIndex);

                    // Parse out the description text
                    beginIndex = endIndex + 3; //skip over " : "
                    endIndex = orderItem.indexOf(" : ",beginIndex);
                    description = orderItem.substring(beginIndex,endIndex);

                    // Parse out the item cost
                    beginIndex = endIndex + 4; //skip over " : $"
                    //endIndex = orderItem.indexOf(" : ",orderItem.length());
                    //sPerUnitCost = orderItem.substring(beginIndex,endIndex);
                    sPerUnitCost = orderItem.substring(beginIndex,orderItem.length());
                    perUnitCost = Float.parseFloat(sPerUnitCost);
                    
                    Product p = new Product();
                    p.setDescription(description);
                    p.setProductId(productID);
                    p.setItemPrice(perUnitCost);
                    p.setItemId(i + 1);
                    
                    
                    listaProdutos.add(p);

                } // line length check

            } //for each line of text in order table
            
            Order o = order.newOrder(loggedUser, token, firstName, lastName, customerAddress, phoneNumber, listaProdutos);
                    
            if(o.isSuccess()){
                msgString =  "\nORDER SUBMITTED FOR: " + firstName + " " + lastName;
                jTextArea3.setText(msgString);

                // Clean up the display

                jTextArea1.setText("");
                jTextArea2.setText("");
                jTextArea4.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("$0");
            }
            else{
                jTextArea3.setText("\nFailed to create order\n");
            }
                
        } else {

            errString =  "\nMissing customer information!!!\n";
            jTextArea3.append(errString);
            connectError = true;

        }// customer data check

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // jButton2 is responsible for querying the inventory database and
        // getting the seed inventory. Once retieved, the seed inventory is
        // displayed in jTextArea1. From here the user can select an inventory
        // item by triple clicking the item.

        if(!isLoggedIn){
            jTextArea3.append( "Not logged in\n");
            return;
        }
        
        // BE001 : Box Elder : $36.00 : 400 units in stock
        ArrayList<Inventory> seeds = (ArrayList<Inventory>) inventory.getInventory("SEEDS",username.getText(),token).getListaInventario();

        for(Inventory i : seeds)
        	jTextArea1.append(i.getCode() + " : " + i.getDescription() + " : $" + i.getPrice() + " : " + i.getQuantity() + " units in stock\n");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // jButton3 is responsible for querying the inventory database and
        // getting the shrub inventory. Once retieved, the shrub inventory is
        // displayed in jTextArea1. From here the user can select an inventory
        // item by triple clicking the item.

        if(!isLoggedIn){
            jTextArea3.append( "Not logged in\n");
            return;
        }
        
        // BE001 : Box Elder : $36.00 : 400 units in stock
        ArrayList<Inventory> shrubs = (ArrayList<Inventory>) inventory.getInventory("SHRUBS",username.getText(),token).getListaInventario();

        for(Inventory i : shrubs)
        	jTextArea1.append(i.getCode() + " : " + i.getDescription() + " : $" + i.getPrice() + " : " + i.getQuantity() + " units in stock\n");


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        
    }//GEN-LAST:event_passwordActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        if(isLoggedIn){
            jTextArea3.append( "Already logged in\n");
            return;
        }

        token = client.login(username.getText(), password.getText(), true).getToken();
        
        if(token.length() == 32){
            isLoggedIn = true;
            loggedUser = username.getText();
            jTextArea3.append("Logged in\n");
        }
        else{
            isLoggedIn = false;
            loggedUser = null;
            jTextArea3.append("Failed to login\n");
            
        }

    }//GEN-LAST:event_loginButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        
        if(!isLoggedIn){
            jTextArea3.append( "Not logged in\n");
            return;
        }
        
        if(client.logout(username.getText(), password.getText(), false).isSuccess()){
            isLoggedIn = false;
            loggedUser = null;
            jTextArea3.append("Logged out\n");
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    private Boolean isLoggedIn = false;
    private String token;
    private String loggedUser;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JToggleButton loginButton;
    private javax.swing.JToggleButton logoutButton;
    private javax.swing.JTextField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

}
