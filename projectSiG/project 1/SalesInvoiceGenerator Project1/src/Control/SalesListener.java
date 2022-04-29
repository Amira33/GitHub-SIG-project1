
package Control;

import TableModel.InvoiceItems;
import TableModel.InvoiceItemsTable;
import TableModel.SalesInvoiceHeader;
import FramePackage.SalesInvoiceFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author iAmira
 */


//declaration of the class sales listener and implement action listener and list selection listener
public class SalesListener  implements ActionListener, ListSelectionListener
{

   // declaration of the frame and date format 
       private SalesInvoiceFrame frame ;        // a new object of the sales invoice frame 
       private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");   // date format (day - month - year )
   
   
   
    // make a constractor of the class sales listener 
    public SalesListener (SalesInvoiceFrame frame) 
    {
        this. frame = frame  ;
    
    }
    
    
    // create a method of invoices run screen  to show the result in neatbeans run window each time used it 
    
     private void InvoicesRunScreen() 
     {
        
         for (SalesInvoiceHeader header : frame.getSalesInvoicesMenu())    // for loop of the class sales invoice header
         {
              System.out.println(header);     // print out header 
         }
         System.out.println("\n --------------------------------------------------------------------------------------------- \n");  // print out a line as a break after each methods
    }

  

   
    
 @Override
    
    public void actionPerformed(ActionEvent e)   //implement ActionListener
    
    {
       switch (e.getActionCommand()) 
       {
       
      
         case "InvoiceCancel" :
         {
             InvoiceCancelFrame();      // for cancel the invoice frame of create new invoice
          
         break;
         }
        
         case "InvoiceOK" : 
         {
             InvoiceOKFrame();      // for ok the invoice frame of create new invoice
                   
        break;
         }
    
         
         
         case "ItemCancel" :
         {
             ItemCancelFrame();          // for cancel the item frame of create new item
         break;
         }
         case "ItemOK" :
         {
             ItemOKFrame();               // for ok the item  frame of create new item
         break;
         }
         
    }    // end of the switch 
}       //  end of method action performed


   

  // implement the value changed 
   
       @Override
    public void valueChanged(ListSelectionEvent e)
    {
       
     
        ChoosingRow();   // used this method if the value of the table changed
      
    }

       
    // method of choosing row 
    private void ChoosingRow()
    {
        
     int Rowchosen = frame.getSalesInvoiceTable().getSelectedRow();   //define a new object selected row index 
        if (Rowchosen >= 0)    // make if condition if selected row index large or equal zero 
        {
            SalesInvoiceHeader row =frame.getHeaderTable().getSalesinvoicesMenu().get(Rowchosen);  // select Row from Table
           
            frame.getCustomerNameTextfield().setText(row.getCustomerName());    // set a text of customer name text field 
            frame.getInvoiceDateTextfield().setText(df.format(row.getInvoiceDate()));   // set a text of invoice date text field 
            frame.getInvoiceNumberLabel().setText("" + row.getInvoiceNumber());        // set a text of invoice number label 
            frame.getInvoiceTotalLabel().setText("" + row.getInvoiceTotal());        // set a text of invoice total label
           
            ArrayList<InvoiceItems> items = row.getItems();   // make the array list of the class invoice item 
            
            frame.setItemsTable(new InvoiceItemsTable(items));    // set the new items in items table 
            frame.getInvoiceItemTable().setModel(frame.getItemsTable());
            frame.getItemsTable().fireTableDataChanged();     // Table drow itself and change the data of the table 
            
          //  System.out.println(" you are selected from menu ");
    }

    
}


    // to create a method of invoice cancel frame 
    private void InvoiceCancelFrame() {
        frame.getHeaderDisplay().setVisible(false);   // set visible of header display as false 
        frame.getHeaderDisplay().dispose();          // to exit the window of the frame 
        frame.setHeaderDisplay(null);               // cancle dialog frame
       
        
        System.out.println(" \n-------------------------------------------------------------------------------------------");
        System.out.println(" \n You are cancel the Invoice frame ");
        System.out.println(" \n-------------------------------------------------------------------------------------------");
        
    }  

   
    // to  create a method of invoice ok frame 
    private void InvoiceOKFrame() {
       
        String invoiceDateStr =frame.getHeaderDisplay().getInvoiceDateField().getText();  // take a text of invoice date field 
        String customerName= frame.getHeaderDisplay().getCustomerNameField().getText();   // take a text of customer name field 
       
        frame.getHeaderDisplay().setVisible(false);
        frame.getHeaderDisplay().dispose();    // to exit the window of invoice frame
        frame.setHeaderDisplay(null);
       
        try 
        {
            Date invoiceDate;     // define a new object of invoice date and it;s type is date 
            invoiceDate = df.parse(invoiceDateStr);   // parse the invoice date str 
           
            int invoiceNumber = getTheNextInvoiceNum();   // define a invoice num as integer and get a method of next invoice number
            SalesInvoiceHeader sales = new SalesInvoiceHeader(invoiceNumber, invoiceDate, customerName);   // define a new object sales to take ( invoice number , invoice date , customer name )
           
            frame.getSalesInvoicesMenu().add(sales);    // frame to get sales invoices menu and add the object (sales)
            frame.getHeaderTable().fireTableDataChanged();        //draw table if Data changed                    
        
        
        } //end of the try 
       
        
        catch (ParseException ex)  //parse exception handling  for wrong date format
        {
            JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);  //error msg for wrong date format 
            ex.printStackTrace();
            
            System.out.println("\n you are insert wrong Date format  ");
            
        }   // end of the catch 
        
        InvoicesRunScreen();  // call a method of invoices run screen to show the detailes in neatbeans run window 
    
   }   //end of the method invoice ok frame 
     
  
    
    // crate a method of get next  invoice number 
    private int getTheNextInvoiceNum()
    {
        int high = 0;   // initial a new object called high 
        for (SalesInvoiceHeader header : frame.getSalesInvoicesMenu())   // for loop to count sales invoice header 
        {
            if (header.getInvoiceNumber() > high) 
            {
                high = header.getInvoiceNumber();
            }
        }
        return high + 1;   //return  high number plus 1 
    }

 
    
    //create a method of item ok frame 
    private void ItemOKFrame() {
        String itemName     = frame.getItemDisplay().getItemNameField().getText();    // take a text of item name field 
        String itemCountStr = frame.getItemDisplay().getItemCountField().getText();  // take a text of item count field 
        String itemPriceStr = frame.getItemDisplay().getItemPriceField().getText();  // take a text of item price field 
        
        frame.getItemDisplay().setVisible(false);
        frame.getItemDisplay().dispose();    // to exit the window of the item frame 
        frame.setItemDisplay(null);
        
       
        try
        {
        int itemCount = Integer.parseInt(itemCountStr);    //define a new object called item count taking a parse from item count str 
        double itemPrice = Double.parseDouble(itemPriceStr);   // define item price taking double parse from item price str
        int headerIndex = frame.getSalesInvoiceTable().getSelectedRow();    // define a header index to get from sales invoice table get from selected row
        SalesInvoiceHeader invoice = frame.getHeaderTable().getSalesinvoicesMenu().get(headerIndex);
        InvoiceItems invoiceitem = new InvoiceItems(itemName, itemPrice, itemCount, invoice);   // define a new object called invoice item
        invoice.addInvItems(invoiceitem);
        frame.getItemsTable().fireTableDataChanged();   // get item table and change the data of table with the new
        frame.getHeaderTable().fireTableDataChanged();  // get header table and change the data of table with the new 
        frame.getInvoiceTotalLabel().setText(""+invoice.getInvoiceTotal());
        
        }
         catch (NumberFormatException ex) {
                ex.printStackTrace();    
               
                  System.out.println(" \n  It's an Error  Number Format ");
                JOptionPane.showMessageDialog(frame, "Number Format Error" , "Error", JOptionPane.ERROR_MESSAGE); // error msg of wrong  number format
                System.out.println(" \n It's an Error  Number Format ");
         }
        
        
        InvoicesRunScreen();   // called a method of invoices run screen to show the details in netbeans run screen window 
    }

    
    //method of item cancel frame 
    private void ItemCancelFrame() {
        
        frame.getItemDisplay().setVisible(false);
        frame.getItemDisplay().dispose();     // to exit the window of item frame 
        frame.setItemDisplay(null);
        
          System.out.println(" \n-------------------------------------------------------------------------------------------");
          System.out.println(" \n You are cancel the Item frame ");
          System.out.println(" \n-------------------------------------------------------------------------------------------");
    }
    
  
}
