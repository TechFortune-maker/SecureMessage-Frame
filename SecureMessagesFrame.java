
package za.ac.tut.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.encryption.MessageEncryptor;
import za.ac.tut.message.Message;

/**
 *
 * @author Osego
 */
public class SecureMessagesFrame extends JFrame{
    //panels
    private JPanel headingPn1;
    private JPanel plainMessagePn1;
    private JPanel encryptMessagePn1;
    private JPanel plainMessagePn1AndencyptMessagePn1Combined;
    private JPanel mainPn1;
  
    //label
    private JLabel headingLb1;
    
    //text area 
    private JTextArea plainTextArea;
    private JTextArea encryptTextArea;
    
    //scrollpane 
    private JScrollPane plainScrollPane;
    private JScrollPane encryptScrollPane;
    
    //Create a menu bar
    private  JMenuBar menuBar;
    
    //Create a menu 
    private JMenu fileMenu;
    
    //Create menu items 
    private JMenuItem openFileItem;
    private JMenuItem encryptMenuItem;
    private JMenuItem saveEncryptMessageItem;
    private JMenuItem clearMenuItem;
    private JMenuItem exitMenuItem;

    public SecureMessagesFrame() {
        //create a gui
        
        setTitle("Secure Messages");
        setSize(650,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setResizable(false);
        
        menuBar = new JMenuBar();
        headingPn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        plainMessagePn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        
        plainMessagePn1.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Plain Message"));
        encryptMessagePn1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        encryptMessagePn1.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Encrypted Message"));
        plainMessagePn1AndencyptMessagePn1Combined = new JPanel(new GridLayout(1,2,1,1));
        mainPn1 = new JPanel(new BorderLayout());
        
        headingLb1 = new JLabel("Message Encryptor");
        headingLb1.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC,30));
        headingLb1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        headingLb1.setForeground(Color.BLUE);
   
         
        //create a file menu 
        fileMenu = new JMenu("File");
        
        //create menu items 
        
        openFileItem = new JMenuItem("Open file...");
        openFileItem.addActionListener(new OpenFileBtnActionListener());
        
        encryptMenuItem = new JMenuItem("Encrypt message");
        encryptMenuItem.addActionListener(new EncryptMessageBtnListener());
        
        saveEncryptMessageItem = new JMenuItem("Save encrypted message...");
        saveEncryptMessageItem.addActionListener(new SaveBtnListener());
        
        clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new ClearBtnActionListener());
        
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitBtnActionListener());
        
        //add items to the file menu
        fileMenu.add(openFileItem);
        fileMenu.add(encryptMenuItem);
        fileMenu.add(saveEncryptMessageItem);
        fileMenu.addSeparator();
        fileMenu.add(clearMenuItem);
        fileMenu.add(exitMenuItem);
        
        //create a text area 
        plainTextArea = new JTextArea(15,25);
        plainTextArea.setEditable(false);
        
        plainScrollPane = new JScrollPane(plainTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                          JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        encryptTextArea = new JTextArea(15,25);
        encryptTextArea.setEditable(false);

        encryptScrollPane = new JScrollPane(encryptTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //add the component to the panel
        headingPn1.add(headingLb1);
        
        plainMessagePn1.add(plainScrollPane);
        encryptMessagePn1.add(encryptScrollPane);

        plainMessagePn1AndencyptMessagePn1Combined.add(plainMessagePn1);
        plainMessagePn1AndencyptMessagePn1Combined.add(encryptMessagePn1);
        
        mainPn1.add(headingPn1, BorderLayout.NORTH);
        mainPn1.add(plainMessagePn1AndencyptMessagePn1Combined);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
        add(mainPn1);
        pack();
        
  
        setVisible(true);
        
    }private class OpenFileBtnActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //declare local variables
            
            BufferedReader br;
            String data, record ="";
            JFileChooser fc;
            int val;
            File file;
            
            //create a file chooser
            fc = new JFileChooser();
            
            //open a file dialog
            val = fc.showOpenDialog(SecureMessagesFrame.this);
            
            if(val ==  JFileChooser.APPROVE_OPTION){
                try {
                    //get the selected file 
                    file = fc.getSelectedFile();

                    //open a reading stream
                    br = new BufferedReader(new FileReader(file));
                    
                    //read the file until it is the end of the file 
                    while(( data= br.readLine())!= null){
                         
                         //concatenate data 
                         record +=  data + "\n";
                    }
                    //close the reading stream
                    br.close();
                    
                    //append the data on the text area
                    plainTextArea.setText(record);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
       
        }

    }private class ClearBtnActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //clear fields
            plainTextArea.setText("");
            encryptTextArea.setText("");
            
        }

    }private class ExitBtnActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
  
    }
    private class EncryptMessageBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //read the content of the plain text
            String textArea;
            textArea = plainTextArea.getText();
            
            
            //encrypt the message using the WeDoSecureApps
            Message message  = new Message(textArea);
            
            MessageEncryptor msg = new MessageEncryptor(message.getMessage());

            encryptTextArea.setText(msg.encrypt());
            
        }

    }
    private class SaveBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //read the content of the encrypted message 
            String textArea = encryptTextArea.getText();
            
            MessageEncryptor message = new MessageEncryptor(textArea);
            
            //Declare local variables
            JFileChooser fc;
            int val;
            File file;
            BufferedWriter bw;
            
            //Create a file chooser 
            fc = new JFileChooser();
            
            //show a save dialog 
            val = fc.showSaveDialog(SecureMessagesFrame.this);
            
            if(val == JFileChooser.APPROVE_OPTION){
  
                try {
                    //get the selected file
                    file = fc.getSelectedFile();
                    
                    //open the writing stream
                    bw = new BufferedWriter(new FileWriter(file,true));
                    
                    //write data to the file 
                    bw.write(message.encrypt());
                    
                    //move the cursor to the newLine after writing data
                    bw.newLine();
                    
                    //close the stream
                    bw.close();
                    
                    JOptionPane.showMessageDialog(SecureMessagesFrame.this, "Encrypted Message Saved!!");
  
                } catch (IOException ex) {
                    Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        }
    }
}
