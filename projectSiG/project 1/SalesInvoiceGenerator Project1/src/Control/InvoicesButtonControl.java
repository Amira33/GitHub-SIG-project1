
package Control;


import Display.HeaderDisplay;
import TableModel.InvoiceItems;
import TableModel.InvoiceItemsTable;
import TableModel.SalesInvoiceHeader;
import FramePackage.SalesInvoiceFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


// *
// * @author iAmira
// */


// declaration of the class invoices button control extend j frame and implement action listener and list listener 
public class InvoicesButtonControl  extends javax.swing.JFrame implements ActionListener , ListSelectionListener
{
    
    
       private SalesInvoiceFrame frame ;   // define a new object of sales invoice frame 
       private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");    // date format as (day - month - year)
       
       
       
// make a constractor of the class invoices button control 
    public InvoicesButtonControl(SalesInvoiceFrame frame) {
        this.frame = frame  ;
    }
        

    // make a method of invoices button run screen to show the result in netbeans each time used it 
 private void InvoicesButtonRunScreen() 
    {
        
         for (SalesInvoiceHeader header : frame.getSalesInvoicesMenu())   // make a for loop of the class sales invoice header 
         {
              System.out.println(header);   // print out header 
         }
         System.out.println("\n ------------------------------------------------------------------------------------- \n ");  // to print out the line after each metod 
    }

 
 
 // implement action performed 
     @Override
     public void actionPerformed(ActionEvent e) 
     {  

       switch (e.getActionCommand()) 
       {
         case "CreatenewInvoice " :  // to create new invoice with num and date and name 
         {      
           
         CreateNewInvoice();
        break;
        }
            
        case "DeleteInvoice" : // to delete choosen invoice from invoice table
        {         
            DeleteChoosenInvoice();
            break;
        }


       }
     }
  


     // to make a method of deleted choosen invoice 
    private void DeleteChoosenInvoice() 
    {
        

      int Choseninvoice = frame.getSalesInvoiceTable().getSelectedRow();   // to get the selected row of sales invoice table 
     
      SalesInvoiceHeader header = frame.getHeaderTable().getSalesinvoicesMenu().get(Choseninvoice);
      
      frame.getHeaderTable().getSalesinvoicesMenu().remove(Choseninvoice);
      frame.getHeaderTable().fireTableDataChanged();    // to change the data of header table and drow itself with the new 
        
      frame.setItemsTable (new InvoiceItemsTable(new ArrayList<>()));
      frame.getInvoiceItemTable().setModel(frame.getItemsTable());
      frame.getItemsTable().fireTableDataChanged();   // to change the data of items table 
      
      
      frame.getCustomerNameTextfield().setText("");   // set a text of customer name  text field 
      frame.getInvoiceDateTextfield().setText("");   //  set a text of invoice date text field 
      frame.getInvoiceNumberLabel().setText("");    // to set a text of invoice number label 
      frame.getInvoiceTotalLabel().setText("");    // to set a text of invoice total label 
      

   System.out.println(" \n  You are Deleted a Selected Invoice   \n");
    
            
            InvoicesButtonRunScreen();
        
    }

   
    // to make a method of create new invoice 
    private void CreateNewInvoice() 
    {
           
        
       
           frame.setHeaderDisplay(new  HeaderDisplay(frame));    // set it from the class header display 
           frame.getHeaderDisplay().setVisible(true);       // set header display visible as true 
        
        System.out.println(" \n You are Created a New Invoice   \n");
    
    
    }

    
    
   // implement the method of value changed  
    @Override
    public void valueChanged(ListSelectionEvent e)    // used if the value of the table changed 
    {
          ChoosingRow();      // call a method of sales invoice table row selected 
    }

   
    
    // implement a method of choosing row 
    private void ChoosingRow() 
    {
        
    int Rowchosen = frame.getSalesInvoiceTable().getSelectedRow();   // define a new object of selected row index 
       
       if (Rowchosen >= 0) // make if condition 
        {
            SalesInvoiceHeader row =frame.getHeaderTable().getSalesinvoicesMenu().get(Rowchosen);  // select Row from Table
          
            
            frame.getCustomerNameTextfield().setText(row.getCustomerName());  // set the text of customer name text field 
            frame.getInvoiceDateTextfield().setText(df.format(row.getInvoiceDate()));  //set the text of invoice date text field  
            frame.getInvoiceNumberLabel().setText("" + row.getInvoiceNumber());      // set the text of invoice number label 
            frame.getInvoiceTotalLabel().setText("" + row.getInvoiceTotal());      // set the text of invoice total label 
           
            ArrayList<InvoiceItems> items = row.getItems();   // make the array list of the class invoice items 
            
            frame.setItemsTable(new InvoiceItemsTable(items));   // set the new items in items table 
            frame.getInvoiceItemTable().setModel(frame.getItemsTable());
            frame.getItemsTable().fireTableDataChanged();     // Table drow itself and change the data 
            
          //  System.out.println(" you are selected from menu ");
    }


}
   
}

