
package Display;

import FramePackage.SalesInvoiceFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 *
 * @author iAmira
 */


// declaration of the class header display extend J dialog 
public class HeaderDisplay  extends JDialog {
    
    
    // declaration of text field , labels and buttons in the class header display 
    private final JTextField CustomerNameField;
    private final JTextField InvoiceDateField;
    private final JLabel CustomerNameLabel;
    private final JLabel InvoiceDateLabel;
    private final JButton OkButton;
    private final JButton CancelButton;

    
   // insert getter and setter of customer name field and invoice date field  
    public JTextField getCustomerNameField() {
        return CustomerNameField;
    }

    public JTextField getInvoiceDateField() {
        return InvoiceDateField;
    }
    
    
    
    //
    public HeaderDisplay(SalesInvoiceFrame frame) {
       
        CustomerNameLabel = new JLabel("  Customer Name  ");     //create a label of customer name 
        CustomerNameField = new JTextField(15);              //create a text field of customer name with width 15
        
        InvoiceDateLabel = new JLabel("   Invoice Date  ");     //create a label of invoice date
        InvoiceDateField = new JTextField(15);             //create a text field of invoice date with width 15
        
        OkButton = new JButton("OK");                   //create a  button OK of invoice frame
        CancelButton = new JButton("Cancel");          //create a  button cancel of invoice frame
        
        OkButton.setActionCommand("InvoiceOK");       // set action command of invoice ok 
        CancelButton.setActionCommand("InvoiceCancel");    // set action command of invoice cancel 
        
        OkButton.addActionListener(frame.getListener());   // add action listener of the frame ok button 
        CancelButton.addActionListener(frame.getListener());   // add action listener of the frame cancel button 
       
        
        
        setLayout(new GridLayout(3, 2));        //make a grid ( 3 row * 2 columns)
       
        add(InvoiceDateLabel);                //  add first row for invoice Date label 
        add(InvoiceDateField);               // add invoice date text field 
      
        add(CustomerNameLabel);             // add second row for customer name label 
        add(CustomerNameField);            // add customer name text field 
        
       
        add(OkButton);                  // third row for ok the frame 
        add(CancelButton);             // to cancel the frame   
      
        pack();      // calling a pack method to set the size of the frame 
        
    }

   
}
