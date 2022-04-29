
package TableModel;

/**
 *
 * @author iAmira
 */


// declaration of the class invoice items 
public class InvoiceItems 
{
   private  String itemName;     // define a new object item name as a string 
   private  double itemPrice;    // define a new object item price as double 
   private  int    itemCount;    // define a new object item count as integer 
   private SalesInvoiceHeader header;      //  define a new object of sales invoice header called header (read header)

  
  
   
   // method to get file as csv (comma seoerated value )
   public String getFILESASCSV() 
   {      
       
       return "" + getHeader().getInvoiceNumber() + "," + getItemName() + "," + getItemPrice() + "," + getItemCount();
   }
   

   //insert the constractor  of the class invoice items 
    public InvoiceItems(String itemName, double itemPrice, int itemCount, SalesInvoiceHeader header) {
        this.header = header;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
        this.itemCount = itemCount;
      
    }
  

    
     // method to get items total = item price * item count 
   public double getItemsTotal()
   {
   return itemPrice * itemCount ;
    
    }
    
    
    
    //insert getter and setter of the class invoice items 
    public SalesInvoiceHeader getHeader() 
    {
        return header;
    }

    public void setHeader(SalesInvoiceHeader header)
    {
        this.header = header;
    }
    public String getItemName() 
    {
        return itemName;
    }

    public double getItemPrice() 
    {
        return itemPrice;
    }

    public int getItemCount() 
    {
        return itemCount;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) 
    {
        this.itemPrice = itemPrice;
    }

    public void setItemCount(int itemCount)
    {
        this.itemCount = itemCount;
    }

  
    // implement the metho stringb to string of item name , item price , item count 
    @Override
    public String toString()
    {
        return "Sales Items : \n\n " + "Item Name = " + itemName + " \n Item Count = " + itemCount + " \n Item Price = " + itemPrice + "";
    }
   
    


}

