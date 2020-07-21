package edu.umgc.cmis242;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

/**
 * <h3>Programming Project 4</h3>
 * <p>The fourth programming project involves writing a program to manage a real estate database</p>
 * @author      Jimmy Maher
 * @version     %I%, %G%
 * @since       1.0
 */

public class Project4 extends JFrame
{
    /* Declare class consents */
    private static final String FRAME_TITLE = "Real Estate Database";
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;
    private static final Font TEXT_FONT = new Font("Times New Roman", Font.BOLD, 15);
    private static final int TEXT_WIDTH = 5;
    private static final String[ ] DATABASE_ACTION = {"Insert", "Delete", "Find"};
    private static final String[ ] STATUS = {"FOR_SALE", "UNDER_CONTRACT", "SOLD"};

    /* Declare Class Fields */
    protected String propertyAddress;
    protected Status statusOfProperty;
    protected int numberOfBedrooms;
    protected int squareFootage;
    protected int price;
    protected int transactionNumber;

    /* Create an object of the components */
    protected JLabel transactionNumberLabel;
    protected JLabel addressLabel;
    protected JLabel bedroomsLabel;
    protected JLabel squareFootageLabel;
    protected JLabel priceLabel;

    protected JTextField transactionNumberField;
    protected JTextField addressField;
    protected JTextField bedroomsField;
    protected JTextField squareFootageField;
    protected JTextField priceField;

    protected JButton processButton;
    protected JButton changeStatusButton;

    protected JComboBox<String> databaseActionCombo;
    protected JComboBox<String> changeStatusCombo;

    protected JPanel controlPanel;

    protected Property record;

    protected TreeMap<Integer, Property> databaseRecords;

    /**
     * <p>Class constructor will instantiate all components, and set properties for components if any
     * </p>
     */
    public Project4( )
    {
        transactionNumberLabel = new JLabel("Transaction No: ", JLabel.CENTER);
        transactionNumberLabel.setFont(TEXT_FONT);

        addressLabel = new JLabel("Address: ", JLabel.CENTER);
        addressLabel.setFont(TEXT_FONT);

        bedroomsLabel = new JLabel("Bedrooms: ", JLabel.CENTER);
        bedroomsLabel.setFont(TEXT_FONT);

        squareFootageLabel = new JLabel("Square Footage: ", JLabel.CENTER);
        squareFootageLabel.setFont(TEXT_FONT);

        priceLabel = new JLabel("Price: ", JLabel.CENTER);
        priceLabel.setFont(TEXT_FONT);

        transactionNumberField = new JTextField(TEXT_WIDTH);
        transactionNumberField.setEnabled(true);
        transactionNumberField.setFont(TEXT_FONT);
        transactionNumberField.setText(" ");

        addressField = new JTextField(TEXT_WIDTH);
        addressField.setEnabled(true);
        addressField.setFont(TEXT_FONT);
        addressField.setText(" ");

        bedroomsField = new JTextField(TEXT_WIDTH);
        bedroomsField.setEnabled(true);
        bedroomsField.setFont(TEXT_FONT);
        bedroomsField.setText(" ");

        squareFootageField = new JTextField(TEXT_WIDTH);
        squareFootageField.setEnabled(true);
        squareFootageField.setFont(TEXT_FONT);
        squareFootageField.setText(" ");

        priceField = new JTextField(TEXT_WIDTH);
        priceField.setEnabled(true);
        priceField.setFont(TEXT_FONT);
        priceField.setText(" ");

        databaseActionCombo = new JComboBox< >(DATABASE_ACTION);
        databaseActionCombo.setEditable(false);
        databaseActionCombo.setFont(TEXT_FONT);

        changeStatusCombo = new JComboBox< >(STATUS);
        changeStatusCombo.setEditable(false);
        changeStatusCombo.setFont(TEXT_FONT);

        processButton = new JButton("Process");
        processButton.setFont(TEXT_FONT);
        processButton.setOpaque(true);

        changeStatusButton = new JButton("Change Status");
        changeStatusButton.setFont(TEXT_FONT);
        changeStatusButton.setOpaque(true);

        controlPanel = new JPanel(new GridLayout(7, 2));

        /* Add Swing components to JPanel */
        controlPanel.add(transactionNumberLabel);
        controlPanel.add(transactionNumberField);
        controlPanel.add(addressLabel);
        controlPanel.add(addressField);
        controlPanel.add(bedroomsLabel);
        controlPanel.add(bedroomsField);
        controlPanel.add(squareFootageLabel);
        controlPanel.add(squareFootageField);
        controlPanel.add(priceLabel);
        controlPanel.add(priceField);
        controlPanel.add(processButton);
        controlPanel.add(databaseActionCombo);
        controlPanel.add(changeStatusButton);
        controlPanel.add(changeStatusCombo);

        /* Add JPanel to JFrame */
        setContentPane(controlPanel);

        ActionListener buttonListener1 = new processButtonListener( );
        ActionListener buttonListener2 = new changeStatusButtonListener( );

        changeStatusButton.addActionListener(buttonListener2);
        processButton.addActionListener(buttonListener1);

        /* Helper Method */
        createMap( );
    }// end of Project4 constructor

    /**
     * <p>
     *     This method will instantiate record object and then store all property
     *     record
     * </p>
     */
    public void setPropertyRecords( )
    {
        propertyAddress = addressField.getText( );

        record = new Property(propertyAddress, numberOfBedrooms, squareFootage, price);

    }// end of setPropertyRecords method

    /**
     * <p>
     *     This method will instantiate TreeMap object then create an empty TreeMap. Upon successful of creating the
     *     TreeMap, a popup will confirm a TreeMap is created.
     * </p>
     */
    public void createMap( )
    {
        databaseRecords = new TreeMap< >( );

        JOptionPane.showMessageDialog(null, "Database was created successful!", "Info",
                JOptionPane.INFORMATION_MESSAGE);

    }// end of createMap method

    /**
     *<p>
     *  This method will insert property records into database using transactionNumber as the key and record object as
     *  the value. If the insert request is successful then a then a window should pop up confirming success. Else
     *  If the user attempts to insert a key that is already in the database an error message should be displayed
     *  using a JOptionPane message dialog box.
     *</p>
     */
    public void insertRecord( )
    {
        if(transactionNumber == 0)
        {
            JOptionPane.showMessageDialog(null, "Transaction Number not entered! Please enter a" +
                                           " transaction number", "Waring!", JOptionPane.WARNING_MESSAGE);
        }
        else if (verifyTreeMapKey(transactionNumber) || verifyTreeMapValue(record))
        {
            JOptionPane.showMessageDialog(null, "Transaction Number or Record already exist!" +
                                           " Please check data!", "Waring!", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            databaseRecords.put(transactionNumber, record);

            JOptionPane.showMessageDialog(null, "Record successfully inserted into database",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }// end of insertIntoMap method

    /**
     *<p>
     *  This method will remove property record in database using transactionNumber as the key and
     *  record object the value. If the delete request is successful then a window should pop up confirming success.
     *  Else if the delete request is unsuccessful then a window should pop up warning that it could not
     *  delete the property record due that the key does not exist.
     *</p>
     */
    public void deleteRecord( )
    {
        if(verifyTreeMapKey(transactionNumber))
        {
            databaseRecords.remove(transactionNumber);

            JOptionPane.showMessageDialog(null, "Record successfully removed from database",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Record does not exist in database",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }// end of deleteTreeMapKey method

    /**
     * <p>
     *    This method will find the property record in database using transaction number as the key and record as the
     *    values. If the find request is successful then a window should pop up containing all the information in
     *    the associated Property object. Else if the find request is unsuccessful then a window should pop up warning
     *    that it could not find the property record.
     * </p>
     */
    public void findRecord( )
    {
        if(verifyTreeMapKey(transactionNumber) && verifyTreeMapValue(record))
        {
            databaseRecords.get(transactionNumber);

            JOptionPane.showMessageDialog(null, record.toString( ));
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Record does not exist in database",
                                          "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }// end of findRecord

    /**
     * <p>
     *     This method will test to see if the record is already in the database
     * </p>
     * @param record TreeMap value
     * @return True or false on test condition.
     */
    public boolean verifyTreeMapValue(Property record)
    {
        return (databaseRecords.containsValue(record));

    }// end of verifyTreeMapKey method

    /**
     * <p>
     *     This method will test to see if the key is already in database
     * </p>
     * @param transactionNumber TreeMap key
     * @return True or false on test condition.
     */
    public boolean verifyTreeMapKey(int transactionNumber)
    {
        return databaseRecords.containsKey(transactionNumber);

    }// end of verifyTreeMapKey method

    /**
     * <p>
     *     This method will verify that user enters correctly integer values where in any of the fields that require that
     *     data type. If the user enters incorrectly, an error message should be displayed in a JOptionPane window.
     * </p>
     */
    public void verifyInput( )
    {
            try
            {
                transactionNumber = Integer.parseInt(transactionNumberField.getText( ));
                numberOfBedrooms = Integer.parseInt(bedroomsField.getText( ));
                squareFootage = Integer.parseInt(squareFootageField.getText( ));
                price = Integer.parseInt(priceField.getText( ));
            }
            catch(NumberFormatException error)
            {
                JOptionPane.showMessageDialog(null, error.getMessage( ));
            }
    }// end of verifyInput method

    /**
     * <p>
     *     This method clears any of the data in JTextFields
     * </p>
     */
    public void clearTextFields( )
    {
        transactionNumberField.setText("");
        bedroomsField.setText(" ");
        squareFootageField.setText("");
        priceField.setText("");
        addressField.setText("");
    }//end of clearTextFields

    /**
     * <p>
     *     This method will create window using JFrame
     * </p>
     */
    public static void createAndShowGUI()
    {
        JFrame window = new Project4( );

        // Set the title of the frame
        window.setTitle(FRAME_TITLE);

        // Set the frame's size
        window.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // Display the JFrame to user
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }// end createAndShowGUI method

    /**
     * Schedule a job for the event-dispatching thread, and creating and showing this application's GUI.
     * @param args no args
     */
    public static void main(String[ ] args)
    {
        javax.swing.SwingUtilities.invokeLater(Project4::createAndShowGUI);

    }// end of main method


    /**
     * <p>
     *    Clicking the Process button first causes the selected choice of the three database actions in the combo box
     *    to its right to be executed; Insert, Delete, or Find.
     *
     * </p>
     */
    class processButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int index = databaseActionCombo.getSelectedIndex();

           switch(index)
           {
               case 0:
                   verifyInput( );

                   setPropertyRecords( );

                   insertRecord( );

                   clearTextFields( );

               break;

               case 1:
                   deleteRecord( );
                   clearTextFields( );
               break;

               case 2:
                   findRecord( );
                   clearTextFields( );
               break;

               default:
                   throw new IllegalStateException("Unexpected value: " + index);
           }// end of switch
        }// end of actionPerformed method
    }// end of processButtonListener inner class

    /**
     * <p>
     *     Clicking the Change Status button should cause the status of property association with the designated
     *     transaction number to be changed to status selected in the combo box to its right.
     * </p>
     */
    class changeStatusButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(transactionNumber == 0)
            {
                JOptionPane.showMessageDialog(null, "Record does not exist in database",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                // Fetch the record using the transaction number
                databaseRecords.get(transactionNumber);
            }
            
            statusOfProperty = Status.valueOf((String) changeStatusCombo.getSelectedItem( ));

            record.changeState(statusOfProperty);

            findRecord( );
        }
    }
}// end of Project4
