
package Control;

import TableModel.InvoiceHeaderTable;
import TableModel.InvoiceItems;
import TableModel.SalesInvoiceHeader;
import FramePackage.SalesInvoiceFrame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

///**
// *
// * @author iAmira
// */


//declaration of the class menu control and implement action listener 
public class MenuControl implements ActionListener
{
   
       private SalesInvoiceFrame frame ;   // define a new object of sales invoice frame 
       private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");    // date format as (day - month - year )
    

// make a constractor  of the class menu control 
    public MenuControl(SalesInvoiceFrame frame) 
    {
        this.frame = frame;
    }

   
 
// make a method of menu run screen to show the result in netbeans run window each time used it     
 private void MenuRunScreen()
   {


       for (SalesInvoiceHeader header : frame.getSalesInvoicesMenu())   // make a for loop 
        {
              System.out.println(header);    // print out header 
         }
         System.out.println(" \n ----------------------------------------------------------------------------------------------\n ");
    }       
     

    
 // implement action performed
    public void actionPerformed(ActionEvent e) 
    { 

       switch (e.getActionCommand()) 
       {
       
        
        case "SaveFile" :  
            {                 
           
            SaveCSVFile();               //method of  savefile from table as CSV file
            break;
            }   
            
        case "LoadFile" :               
           {
               try {
                
             LoadCSVFile() ;      // method of  Load CSV file to table
           
            break;
              
               } 
               catch (IOException ex) 
               {
                   Logger.getLogger(MenuControl.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ParseException ex) {
                   Logger.getLogger(MenuControl.class.getName()).log(Level.SEVERE, null, ex);
               }
           }

       }
 
    }  
           
    
       
           
 
    
    //to make a metod of save file 
    private void SaveCSVFile() 
    {
        
      String Salesinvoices = "";   // define a new string object called invoices 
      
      String items = "";    // define a new string object called items 
      
      for (SalesInvoiceHeader header :frame.getSalesInvoicesMenu())   // for loop of class sales invoice header 
      {
          Salesinvoices += header.getFILESASCSV();   // to get a csv file for invoice 
          Salesinvoices += "\n";         // to insert new line 
         
          for (InvoiceItems item : header.getItems()) //for loop of the class invoice items 
          {
              items += item.getFILESASCSV();   // get a file as csv file  for item 
              items += "\n";     // to insert new line 
          }
      }
       
      JOptionPane.showMessageDialog(frame, " Save Invoices as CSV file   ", "Invoices Files", JOptionPane.INFORMATION_MESSAGE); //show a msg TO Save Invoices FILE
      
      JFileChooser fileChooser = new JFileChooser();  // to choose a file . file saving location as a default directory 
       
      int result = fileChooser.showSaveDialog(frame);   // define anw object called result to show the save dialog 
          
      if (result == JFileChooser.APPROVE_OPTION) 
      {
               File headerFile = fileChooser.getSelectedFile();  
               try {
                   FileWriter hFW;      // to write text char files (streams of char )
                    
                    hFW = new FileWriter(headerFile);
                    hFW.write(Salesinvoices);    // hfw  high file writer 
                    hFW.flush();      // used to flush , clear the writer te stream of any element 
                    hFW.close();
          
            System.out.println(" \n Bravo YOu Saved Invoices CSV File");             //print in the programe  run screen you saved the invoice CSV file 
                    
        JOptionPane.showMessageDialog(frame, " Save Items as CSV file ", "Items Files ", JOptionPane.INFORMATION_MESSAGE); // show a msg TO Save Items FILE            
           
            result = fileChooser.showSaveDialog(frame);   
             if (result == JFileChooser.APPROVE_OPTION)
             { 
                 
                 File itemsFile = fileChooser.getSelectedFile();    
                 FileWriter lFW = new FileWriter(itemsFile);
                    lFW.write(items);
                    lFW.flush();
                    lFW.close();
              } 
            
   
            System.out.println("\n ----------------------------------------------------------------------------------------------");
             System.out.println(" \n Bravo YOu Saved Items CSV file");               //print in run screen you saved the  item CSV file 
             
            }
               catch (HeadlessException | IOException | NumberFormatException  ex) 
               {
               // JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),  "Error", JOptionPane.ERROR_MESSAGE); // Handling
                   
                   JOptionPane.showMessageDialog(frame, "Wrong Save", "Error", JOptionPane.ERROR_MESSAGE);  //error msg for wrong save 
            ex.printStackTrace();
            
            System.out.println("\n \n  wrong Save  ");
                   
                   
               }
     }
    
            JOptionPane.showMessageDialog(frame, "Bravo Files are saved in your Computer ", " Success ", JOptionPane.INFORMATION_MESSAGE);
            
            
     System.out.println(" \n------------------------------------------------------------------------------------------------------");
     System.out.println(" \n All Files (Invoices and Items are saved in your computer successfully ");
            
    }    // end of the method save 
    
   
  
    
//*************************************************************************************************************************************//
    
   
    
    
    // to make a metod of load file 
    
    private void LoadCSVFile() throws FileNotFoundException, IOException, ParseException
    {
          JOptionPane.showMessageDialog(frame, "     Insert the Invoices CSV file    ", " Invoices File ", JOptionPane.INFORMATION_MESSAGE); //msg  TO CHOOSE HEADER FILE
         
          JFileChooser openFile = new JFileChooser();   // to choose a file , file saving location as a default directory 
       
          int result = openFile.showOpenDialog(frame);   // define a new object called result to open a dialog frame  
        if (result == JFileChooser.APPROVE_OPTION)      // if condition of j file chooser 
        {
            File headerFile = openFile.getSelectedFile();     // get the selected invoice file 
             try {
                FileReader headerFr = new FileReader(headerFile);          // READ invoise CSV FILE
                BufferedReader headerBr = new BufferedReader(headerFr);
               
                String headerItem = null;   // define a string header item 

                while ((headerItem = headerBr.readLine()) != null)     // while header item not equal null
                {
                    String[] headerSegments = headerItem.split(",");    // to split invoices OF HEADER items using (,)
                    String invoiceNumberStr = headerSegments[0];       // invoiceNumber string as a location 0 of header segment 
                    String invoiceDateStr = headerSegments[1];        // invoiceDate string as a location 1 of header segment 
                    String customerName = headerSegments[2];         // customerName already string and has a location 2 of header segment 
                    
                    int invoiceNumber = Integer.parseInt(invoiceNumberStr);  // TO turn invoice number from string to int
                    Date invoiceDate = df.parse(invoiceDateStr);            // using dateformat turn date from string into Date type (day - month- year)
                    
                    SalesInvoiceHeader inv = new SalesInvoiceHeader(invoiceNumber, invoiceDate, customerName);
                    
                    frame.getSalesInvoicesMenu().add(inv);   // get the sales invoices menu and add the invoice number , invoice date , invoice name 
                 
                   
                }
                   
                headerBr.close();    // close header br 
                headerFr.close();   // close header fr 
                
                System.out.println(" \n Bravo You already insert CSV Invoice File  ");   // print out the msg in netbeans run window 
                
                
                JOptionPane.showMessageDialog(frame , "    Insert the items CSV File     ", "Items File", JOptionPane.INFORMATION_MESSAGE);  // msg to choose item file 
                
                result = openFile.showOpenDialog(frame);   // to choose a file , file saving location as a default directory
               
                if (result == JFileChooser.APPROVE_OPTION)  
                {
                    File itemsFile = openFile.getSelectedFile();     // get the selected items file 
                    BufferedReader itemsBr;
                    try (FileReader itemsFr = new FileReader(itemsFile)) 
                    {
                        itemsBr = new BufferedReader(itemsFr);
                        
                        String itemsItem;   // define a new object  items item as string 
                        itemsItem = null;   // initial of  new object items item 
                        
                        while ((itemsItem = itemsBr.readLine()) != null)    // while items item not equal null
                        {
                            String[] itemParts = itemsItem.split(",");     // to split items  parts of items table using (,)
                            String invoiceNumberStr = itemParts[0];       //string invoice num
                            String itemName = itemParts[1];              // string item name
                            String itemPriceStr = itemParts[2];         // string item price
                            String itemCountStr = itemParts[3];        // string item count
                            
                            int invoiceNumber = Integer.parseInt(invoiceNumberStr);   // turn sales num from string to integer
                            double itemPrice = Double.parseDouble(itemPriceStr);     // turn item price from string to double
                            int itemCount = Integer.parseInt(itemCountStr);         // turn item count from string to integer
                            
                            SalesInvoiceHeader header = SearchNumber(invoiceNumber);
                            InvoiceItems invItem = new InvoiceItems(itemName, itemPrice, itemCount, header);
                            header.getItems().add(invItem);
                        }
                    }
                    itemsBr.close();   // close items br 
                    
                    frame.setHeaderTable(new InvoiceHeaderTable(frame.getSalesInvoicesMenu()));
                    frame.getSalesInvoiceTable().setModel(frame.getHeaderTable());
                    frame.getSalesInvoiceTable().validate();
                }
                
               System.out.println(" \n Bravo You already insert CSV Items File  ");
               
               System.out.println(" \n ---------------------------------------------------------------------------------------------\n ");
               
             } 
            
             catch (ParseException ex)  //parse exception handling 
             {
            JOptionPane.showMessageDialog(frame, "Wrong File format", "Error", JOptionPane.ERROR_MESSAGE);  //error msg for wrong file format 
            ex.printStackTrace();
            
            System.out.println("\n \n you are insert wrong file format  ");
            
        
                
            } 

        }
    
        
      MenuRunScreen() ;              //to show the detailed load files in the netbeans programe  run screen 
 }        // end of the load  method 
  
   
    
    // create a method of search num  to  find invoice by number 
    
     private SalesInvoiceHeader SearchNumber(int InvoiceNumber) 
     {
        SalesInvoiceHeader header = null;    //define a header 
        for (SalesInvoiceHeader invoice : frame.getSalesInvoicesMenu())   // for loop 
        {
            if (InvoiceNumber == invoice.getInvoiceNumber()) 
            {
                header = invoice;     // define header equal invoice 
                break;
            }
        }
        return header;
        
    }
     

    
   
 
           
      
 }    // end of the class menu control 

           
           
