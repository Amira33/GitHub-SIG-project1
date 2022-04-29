
package TableModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author iAmira
 */
    

//declaration of the class invoice items table extend abstract table model 
public class InvoiceItemsTable extends AbstractTableModel 
{

    private final List<InvoiceItems> itemsList;    // define a new object called items list as a list of class invoice items 
    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");    // define a date format as (day - month- year )
    
    
    
    //insert the constractor of items list of the class invoice items table 
    public InvoiceItemsTable(List<InvoiceItems> itemsList) {
        this.itemsList = itemsList;
    }
    
    
   // insert getter of items list 
    public List<InvoiceItems> getItemsList() {
        return itemsList;
    }
    
    
    
    //implement get row count 
    @Override
    public int getRowCount() {
       return itemsList.size();      //Size of row itmes list of  invoice items table
    }

    //implement a column count 
    @Override
    public int getColumnCount() {
        return 4;                   // number of coulumns of invoice items table ( item Name,item Price, item Count ,item TOTAL)
    }

   
    
    // implement a get of column name
     @Override
    public String getColumnName(int columnIndex) {      // index name of columns of invoice items table
          switch (columnIndex) {
             
              case 0 : return "Item Name";          // case 0 for item name 
              case 1 : return "Item Price";        // case 1 for item price 
              case 2 : return "Item Count";       //  case 2 for item count 
              case 3 : return "Item Total";      // case 3 for item total 
              default : return "";              // else return ""
          }
    }

    
    // implement the column class
    @Override
    public Class<?> getColumnClass(int columnIndex) {
          switch (columnIndex) {
              
              case 0 : return String.class;     //for item name
              case 1 : return Double.class;    //for item price
              case 2 : return Integer.class;   //for item count
              case 3 : return Double.class;    //for item total
              default : return Object.class;
          }
    }

    
    //implement the celleditable (row index , column index )
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;             //not allwoed the user to modify the  table  
    }

    
    
    //implement a value of row index , column index 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {    // to draw the data of table 
        InvoiceItems row;
        row = itemsList.get(rowIndex);
          switch (columnIndex) {
              
              case 0 : return row.getItemName();      //get item name and return it 
              case 1 : return row.getItemPrice();    // get item price and return it 
              case 2 : return row.getItemCount();   // get item count and return it 
              case 3 : return row.getItemsTotal();    // get items total and return it 
              default : return "";                  // else return ""
          }
    }
}
