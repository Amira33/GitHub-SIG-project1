

package TableModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author iAmira
 */


//declaration of the class invoice header table and extend abstract table model
public class InvoiceHeaderTable extends AbstractTableModel {


    private final  List<SalesInvoiceHeader> salesinvoicesMenu;   // define a new object of the class sales invoice header 
    private final  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");  // DateFormatas (Day, Month, Year)
    
   
    //insert the constaractor of invoices list of the class invoice header table 
    public InvoiceHeaderTable(List<SalesInvoiceHeader> invoicesList) {
        this.salesinvoicesMenu = invoicesList; 
    }

    
    // insert the getter of sales invoices menu as list of sales invoice header 
    public List<SalesInvoiceHeader> getSalesinvoicesMenu() {
        return salesinvoicesMenu;
    }

   
    //implement the row count 
    @Override
    public int getRowCount() {
       
        return salesinvoicesMenu.size();       //Size of row invoice list of  invoice header table
    }

    
    //implement of column count 
    @Override
    public int getColumnCount() {
        return 4;                      // number of coulumns of invoice header table( invoice NO,invoice DATE,Customer Name, invoice TOTAL)
    }

 
    
    @Override
    public String getColumnName(int columnIndex) {      // index name of columns of invoice header table
          switch (columnIndex) 
          {
              case 0 : return "Invoice Number";     // for invoice number
              case 1 : return "Customer Name";     // for customer name 
              case 2 : return "Invoice Date";     // for invoice date 
              case 3 : return "Invoice Total";   // for invoice total 
              default : return "";              // else return the default ""
          }
    }

    
    
    // implement the celleitable ( row index , column index 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;             //not allwoed the user to modify the  table  
    }
    
    
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
          switch (columnIndex) {
              case 0 : return Integer.class;     //integer for invoice number
              case 1 : return String.class;     //string for customer name
              case 2 : return String.class;    //string for invoice date
              case 3 : return Double.class;   //double for invoice total
              default : return Object.class;  // object for default ""
          }
    }

  
    //implwment the value of row index and column index 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {    // to draw the data of table 
        SalesInvoiceHeader row;
        row = salesinvoicesMenu.get(rowIndex);
        
          switch (columnIndex) {
              case 0 : return row.getInvoiceNumber();    //get the invoice number and return it 
              case 1 : return row.getCustomerName();     // get customer name and return it 
              case 2 : return df.format(row.getInvoiceDate());   // get the invoice date as format and return it 
              case 3 : return row.getInvoiceTotal();            // get the invoice total and return it 
              default : return "";
          }
        
    }


}

    

