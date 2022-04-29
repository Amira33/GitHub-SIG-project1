
package TableModel;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author iAmira
 */


//declaration of the class sales invoice header 
public class SalesInvoiceHeader {
   
    private ArrayList<InvoiceItems> Items;   // define a new  object called items of array list of the class invoice items 
    private String customerName;            // define a new object customer name as a string 
    private int invoiceNumber;              // define a new objec invoice number as a string 
    private Date invoiceDate;              // define a new object invoice date as date format (day-month-year)
    
    
    
    
    public String getFILESASCSV() {      // get files as comma-separated values
      
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); //define date format type (day-month-year)
      
        return "" + getInvoiceNumber() + "," + df.format(getInvoiceDate()) + "," + getCustomerName();
   }
    

    //insert the constractor of customer name , invoice date , invoice number 
    public SalesInvoiceHeader(int invoiceNumber, Date invoiceDate, String customerName) 
    {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        
    }
  
   // insert the getter and setter 
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    
    
    // implement the method string to string 
     @Override
    public String toString() 
    {
        System.out.println("\n");
      
        System.out.println("                  Showing the Sales Invoices and Sales Items                     ");
       
        System.out.println("\n");
      
        String value =  "Sales Invoices : \n\n " + "Invoice Number =  " + invoiceNumber + " \n Customer Name =  " +  customerName  + " \n Invoice Date =  " + invoiceDate + "";
     
        
       for (InvoiceItems item :getItems())
       {
                value += "\n \n" + item;    // (\n to insert new line)
         
       }
  return value;
    
    
    }
    
    
    //getter and setter of array list of the class invoice items 
    public ArrayList<InvoiceItems> getItems() {
        
        if (Items ==null)
            Items = new ArrayList<>();
        return Items;
        
    }
    
    
    public void setItems(ArrayList<InvoiceItems> Items) {
        this.Items = Items;
    }
     
     
     
  
  // getter of invoice total  
    public double getInvoiceTotal()
    {
    double total = 0.0 ;   // intial new object total as double equal zero
    for (InvoiceItems Item : getItems()) 
    {
        total += Item.getItemsTotal();
    }
    return total;
}

    /**
     *
     * @param Item
     */
    public void addInvItems(InvoiceItems Item){
    getItems().add(Item);

     }
    
    
}

