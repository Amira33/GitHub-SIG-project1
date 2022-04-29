
package Control;


import Display.ItemDisplay;
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



//declaration of the class items button control extend j frame and implement action listener and list listener 
public class ItemsButtonControl   extends javax.swing.JFrame implements ActionListener , ListSelectionListener 
{

   private SalesInvoiceFrame frame ;   // define a new object of sales invoice frame 
       private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");    // date format as (day - month - year )

   
 
    //  make a constractor of the class items button control 
    public ItemsButtonControl(SalesInvoiceFrame frame) 
    {
         this.frame = frame;
    }
   
    
    // make a method of items button run screen to show the result in netbeans run  window each time used it 
    private void ItemsButtonRunScreen() 
    {
        
         for (SalesInvoiceHeader header : frame.getSalesInvoicesMenu()) //make a for loop of the class sales invoice header 
         {
              System.out.println(header);   // to print out the header 
         }
         System.out.println(" \n ------------------------------------------------------------------------------------\n");
    }

   
    @Override
   public void actionPerformed(ActionEvent e)   //implement Actionperformed
   {
       switch (e.getActionCommand()) {
           
           
           case "Saveitem" :              // to save new item in the selected invoice
           {
             SaveChoosenItem();      // make a method of save choosen item 
             break;
            
           }
        
           case "Cancelitem" :          // to cancel or delet the selected item 
           {
            CancelChoosenItem();      // make a method of cancel choosen item 
            break;
            
         }
           
       }
    }

   
   // method of save new item 
   private void SaveChoosenItem()
   {
            
            frame.setItemDisplay(new ItemDisplay(frame));   // set a new item display 
            frame.getItemDisplay().setVisible(true);       // set item display visible as true 
           
            System.out.println(" \n You are Saved New Item  for Selected Invoice  \n  ");
             
            //  ItemsButtonRunScreen();
    }

   
   
   // make a method of cancel choosen item 
   private void CancelChoosenItem() 
   {
      int itemSelected = frame.getInvoiceItemTable().getSelectedRow();  // to get the selected row of invoice item table 
        InvoiceItems item = frame.getItemsTable().getItemsList().get(itemSelected);  // to get the items list of items table 
       
        
        frame.getItemsTable().getItemsList().remove(itemSelected);
        frame.getItemsTable().fireTableDataChanged();     // to change the data of the table anditems table  drow itself
        frame.getHeaderTable().fireTableDataChanged();   // header table drow it self 
        
        frame.getInvoiceTotalLabel().setText(""+item.getHeader().getInvoiceTotal());
    
        
         
         System.out.println(" \n You are Deleted a Selected  Item \n");
       
           ItemsButtonRunScreen();   // method to show the deleted items in netbeans window run screen 
           
            
    }

   
    // implement of the value changed 
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        
            ChoosingRow(); 
    }

    
    // make a method of choosing row 
    private void ChoosingRow() 
    {
        
    int Rowchosen = frame.getSalesInvoiceTable().getSelectedRow();     //define a new object of selected row index 
        if (Rowchosen >= 0)  // make if condition selected row index large than or equal zero 
        {
            SalesInvoiceHeader row =frame.getHeaderTable().getSalesinvoicesMenu().get(Rowchosen);  // select Row from Table
            
            frame.getCustomerNameTextfield().setText(row.getCustomerName());      //set the text of customer name text field 
            frame.getInvoiceDateTextfield().setText(df.format(row.getInvoiceDate()));  // set the text of invoice date text field 
            frame.getInvoiceNumberLabel().setText("" + row.getInvoiceNumber());      // set the text of invoice number label 
            frame.getInvoiceTotalLabel().setText("" + row.getInvoiceTotal());       // set the text of invoice total label 
           
            ArrayList<InvoiceItems> items = row.getItems();     // make the array list of the class invoice items 
            
            frame.setItemsTable(new InvoiceItemsTable(items));     // set the new items in items table 
            frame.getInvoiceItemTable().setModel(frame.getItemsTable());
            frame.getItemsTable().fireTableDataChanged();     // Table drow itself and change the data 
            
          //  System.out.println(" you are selected from menu ");
    }


}

 
}