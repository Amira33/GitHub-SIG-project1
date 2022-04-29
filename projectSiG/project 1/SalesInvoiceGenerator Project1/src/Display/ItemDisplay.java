

package Display;


import FramePackage.SalesInvoiceFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author iAmira
 */


// declaration of the class item display extend J dialog 
public class ItemDisplay extends JDialog {
  
    
    
    // declaration of the text field , labels and buttons of the class item display 
    private final JTextField itemNameField;
    private final JTextField itemCountField;
    private final JLabel itemPriceLabel;
    private final JTextField itemPriceField;
    private final JLabel itemNameLabel;
    private final JLabel itemCountLabel;
    private final JButton OkButton;
    private final JButton CancelButton;
    
   
    
    
    public ItemDisplay(SalesInvoiceFrame frame) {
        
        itemNameField = new JTextField(15);               //create a text field of item name with width 15
        itemNameLabel = new JLabel(" Item Name ");       // make a label of item name 
        
        itemCountField = new JTextField(15);            //create a text field of item count with width 15
        itemCountLabel = new JLabel(" Item Count ");   // make a label of item count 
        
        itemPriceField = new JTextField(15);          //create a text field of item price with width 15
        itemPriceLabel = new JLabel(" Item Price ");  // make a label of item price 
  
       
        OkButton = new JButton("OK");               //create a button Ok of item frame
        CancelButton = new JButton("Cancel");      //create a button cancel of item frame
        
        OkButton.setActionCommand("ItemOK");       // set action command of item ok 
        CancelButton.setActionCommand("ItemCancel");   // set action command of item cancel 
        
        OkButton.addActionListener(frame.getListener());   // add action listener of the frame of ok button 
        CancelButton.addActionListener(frame.getListener());   // add action listener of the frame of cancel  button 
        
     
        
        setLayout(new GridLayout(4, 2));             // 4 row * 2 columns
        
        add(itemNameLabel);                         // add first row for item Name label 
        add(itemNameField);                         // add item name text field 
        
        add(itemCountLabel);                       //add second row for item count
        add(itemCountField);                       // add item count text field 
       
        add(itemPriceLabel);                     // add third row for item price
        add(itemPriceField);                     // add item price text field 
       
        add(OkButton);                          // for ok button frame 
        add(CancelButton);                     // for cancel button frame
        
        pack();                          // calling a pack method to set the size of the frame 
    }

   
    //make getter of item price field , item name field , item count field 
    public JTextField getItemPriceField() {
        return itemPriceField;
    }
    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

    
}

