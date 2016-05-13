package travelplanner;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class main {
    private JTable table;
    private JPanel contentPane;
    private JFrame frame;
    private JTextField txtCardNr;
    private JTextField txtOrigin;
    private JTextField txtDestination;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField passwordConfirmField;
    private JTextField txtPrice;
    private JTextField txtDate;
    private JTextField txtTime;
    private JTextField txtDate_2;
    private JTextField txtTime_2;
	private JTextField txtDepartureTime;
	private JTextField txtArrivalTime;
	private JTextField txtPricePerSeat;
    private SystemController sc = new SystemController();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    main window = new main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public main() {
    	frame = new JFrame();
        
        initialize(frame);
    }

    private void initialize(JFrame frame) {
    	frame.setBounds(100, 100, 723, 540);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        login_register(frame);

        txtOrigin = new JTextField();
        txtOrigin.setBounds(94, 107, 144, 20);
        frame.getContentPane().add(txtOrigin);
        txtOrigin.setColumns(10);

        txtDestination = new JTextField();
        txtDestination.setColumns(10);
        txtDestination.setBounds(376, 107, 144, 20);
        frame.getContentPane().add(txtDestination);
        
        JDateChooser dateOrigin = new JDateChooser();
        dateOrigin.setDateFormatString("yyyy-mm-dd");
        dateOrigin.setBounds(94, 159, 95, 20);
        frame.getContentPane().add(dateOrigin);

        JLabel lblOrigin = new JLabel("Origin");
        lblOrigin.setBounds(94, 82, 46, 14);
        frame.getContentPane().add(lblOrigin);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(376, 82, 138, 14);
        frame.getContentPane().add(lblDestination);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SystemController sc = new SystemController();
                String origin = txtOrigin.getText();
                String destination = txtDestination.getText();
                
                
                String date = dateOrigin.getDate().toString();
                String[][] res = sc.getFlights(origin, destination, date);
                
                System.out.println(Arrays.deepToString(res));
            }
        });
        btnSearch.setBounds(253, 304, 89, 23);
        frame.getContentPane().add(btnSearch);

        JCheckBox chckbxReturnFlight = new JCheckBox("Return Flight");
        chckbxReturnFlight.setBounds(376, 304, 97, 23);
        frame.getContentPane().add(chckbxReturnFlight);

        JDateChooser dateReturn = new JDateChooser();
        dateReturn.setEnabled(false);
        dateReturn.setBounds(376, 159, 95, 20);
        frame.getContentPane().add(dateReturn);

        JButton btnAdminTest = new JButton("Admin test");
        btnAdminTest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.getContentPane().removeAll();
            	
            	frame.getContentPane().revalidate();
            	frame.getContentPane().repaint();
            }
        });
        btnAdminTest.setBounds(10, 10, 111, 23);
        frame.getContentPane().add(btnAdminTest);
    }
    
    public void login_register(JFrame frame){
    	 JButton btnLogin = new JButton("Login");
         btnLogin.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	frame.getContentPane().removeAll();
             	login(frame);
             	frame.getContentPane().revalidate();
             	frame.getContentPane().repaint();
             }
         });
         btnLogin.setBounds(391, 11, 89, 21);
         frame.getContentPane().add(btnLogin);

         JButton btnRegister = new JButton("Register");
         btnRegister.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	frame.getContentPane().removeAll();
             	register(frame);
             	frame.getContentPane().revalidate();
             	frame.getContentPane().repaint();
             }
         });
         btnRegister.setBounds(489, 11, 89, 21);
         frame.getContentPane().add(btnRegister);
    }
    
    public void user_logout(JFrame frame){
    	
    	JLabel lblUser = new JLabel("Welcome User: xxx");
        lblUser.setBounds(345, 11, 150, 21);
        frame.getContentPane().add(lblUser);

        JButton btnRegister = new JButton("Logout");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	frame.getContentPane().removeAll();
            	initialize(frame);
            	frame.getContentPane().revalidate();
            	frame.getContentPane().repaint();
            }
        });
        btnRegister.setBounds(489, 11, 89, 21);
        frame.getContentPane().add(btnRegister);
    }
    
    public void login(JFrame frame){
    	contentPane = new JPanel();
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(contentPane);

        usernameField = new JTextField();
        usernameField.setBounds(127, 107, 196, 20);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(127, 177, 196, 20);
        frame.getContentPane().add(passwordField);
        passwordField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(127, 150, 100, 14);
        frame.getContentPane().add(lblPassword);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(127, 74, 78, 14);
        frame.getContentPane().add(lblUsername);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean res = sc.login(usernameField.getText(), passwordField.getText());
                    
                    if (res) {
                        frame.getContentPane().removeAll();
                        initialize(frame);
                        frame.getContentPane().revalidate();
                        frame.getContentPane().repaint();
                    } else {
                        
                        JOptionPane.showMessageDialog(frame, "Wrong username/password",
                                "Couldn't sign in", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnLogin.setBounds(182, 250, 89, 23);
        frame.getContentPane().add(btnLogin);
    }
    
    public void register(JFrame frame){
    	contentPane = new JPanel();
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(contentPane);
        
        nameField = new JTextField();
        nameField.setBounds(173, 71, 156, 20);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        lastNameField = new JTextField();
        lastNameField.setColumns(10);
        lastNameField.setBounds(173, 121, 156, 20);
        frame.getContentPane().add(lastNameField);

        emailField = new JTextField();
        emailField.setColumns(10);
        emailField.setBounds(173, 175, 156, 20);
        frame.getContentPane().add(emailField);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(173, 229, 156, 20);
        frame.getContentPane().add(passwordField);

        passwordConfirmField = new JPasswordField();
        passwordConfirmField.setColumns(10);
        passwordConfirmField.setBounds(173, 281, 156, 20);
        frame.getContentPane().add(passwordConfirmField);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(173, 48, 46, 14);
        frame.getContentPane().add(lblName);

        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(173, 102, 70, 14);
        frame.getContentPane().add(lblLastName);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(173, 150, 46, 14);
        frame.getContentPane().add(lblEmail);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(173, 206, 70, 14);
        frame.getContentPane().add(lblPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setBounds(173, 260, 116, 14);
        frame.getContentPane().add(lblConfirmPassword);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getText().equals(passwordConfirmField.getText())) {
                    
                    try {
                        sc.register(emailField.getText(), passwordField.getText(), nameField.getText(), lastNameField.getText());
                        frame.getContentPane().removeAll();
                        frame.getContentPane().repaint();
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("passwords doesn't match");
                }
            }
        });
        btnRegister.setBounds(208, 334, 89, 23);
        frame.getContentPane().add(btnRegister);
    }
    
    public void search(JFrame frame){
        contentPane = new JPanel();
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(66, 82, 550, 321);
        frame.getContentPane().add(scrollPane);

        DefaultTableModel model = new DefaultTableModel();

        table = new JTable(model);

        model.addColumn("Origin");
        model.addColumn("Destination");
        model.addColumn("Date");

        for(int i = 0; i < 10; i++){
            model.insertRow(i,new Object[] {"test","test","test"});
        }

        scrollPane.setViewportView(table);

        txtOrigin = new JTextField();
        txtOrigin.setBounds(66, 49, 161, 20);
        frame.getContentPane().add(txtOrigin);
        txtOrigin.setColumns(10);

        txtDestination = new JTextField();
        txtDestination.setColumns(10);
        txtDestination.setBounds(237, 49, 164, 20);
        frame.getContentPane().add(txtDestination);

        JDateChooser dateOrigin = new JDateChooser();
        dateOrigin.setBounds(411, 49, 95, 20);
        frame.getContentPane().add(dateOrigin);

        JLabel lblOrigin = new JLabel("Origin");
        lblOrigin.setBounds(66, 24, 46, 14);
        frame.getContentPane().add(lblOrigin);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(237, 24, 100, 14);
        frame.getContentPane().add(lblDestination);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemController sc = new SystemController();
                String origin = lblOrigin.getText();
                String destination = lblDestination.getText();
                String date = dateOrigin.getDateFormatString();
                System.out.println(date);
                //sc.getFlights(origin, destination, date);
            }
        });
        btnSearch.setBounds(527, 48, 89, 23);
        frame.getContentPane().add(btnSearch);

        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                book(frame);
                frame.getContentPane().repaint();
            }
        });
        btnBook.setBounds(280, 424, 89, 23);
        frame.getContentPane().add(btnBook);
        DefaultTableModel model_search = new DefaultTableModel();

        table = new JTable(model_search);

        model_search.addColumn("Origin");
        model_search.addColumn("Destination");
        model_search.addColumn("Date");

        for(int i = 0; i < 10; i++){
                model_search.insertRow(i,new Object[] {"test","test","test"});
        }

        scrollPane.setViewportView(table);

        txtOrigin = new JTextField();
        txtOrigin.setBounds(66, 49, 161, 20);
        frame.getContentPane().add(txtOrigin);
        txtOrigin.setColumns(10);

        txtDestination = new JTextField();
        txtDestination.setColumns(10);
        txtDestination.setBounds(237, 49, 164, 20);
        frame.getContentPane().add(txtDestination);

        JDateChooser dateOrigin_search = new JDateChooser();
        dateOrigin_search.setBounds(411, 49, 95, 20);
        frame.getContentPane().add(dateOrigin_search);

        JLabel lblOrigin_search = new JLabel("Origin");
        lblOrigin_search.setBounds(66, 24, 46, 14);
        frame.getContentPane().add(lblOrigin_search);

        JLabel lblDestination_search = new JLabel("Destination");
        lblDestination_search.setBounds(237, 24, 100, 14);
        frame.getContentPane().add(lblDestination_search);

        JButton btnBook_search = new JButton("Book");
        btnBook_search.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();

        book(frame);
        frame.getContentPane().repaint();
                }
        });
        btnBook_search.setBounds(280, 424, 89, 23);
        frame.getContentPane().add(btnBook_search);
    }
    
    public void book(JFrame frame){
    	contentPane = new JPanel();
    	user_logout(frame);
        
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(contentPane);

        txtOrigin = new JTextField();
        txtOrigin.setBounds(81, 87, 176, 20);
        frame.getContentPane().add(txtOrigin);
        txtOrigin.setColumns(10);

        JTextField txtDestination = new JTextField();
        txtDestination.setColumns(10);
        txtDestination.setBounds(81, 144, 176, 20);
        frame.getContentPane().add(txtDestination);

        txtPrice = new JTextField();
        txtPrice.setColumns(10);
        txtPrice.setBounds(81, 261, 58, 20);
        frame.getContentPane().add(txtPrice);

        txtDate = new JTextField();
        txtDate.setBounds(399, 87, 86, 20);
        frame.getContentPane().add(txtDate);
        txtDate.setColumns(10);

        txtTime = new JTextField();
        txtTime.setColumns(10);
        txtTime.setBounds(559, 87, 86, 20);
        frame.getContentPane().add(txtTime);

        txtDate_2 = new JTextField();
        txtDate_2.setColumns(10);
        txtDate_2.setBounds(399, 144, 86, 20);
        frame.getContentPane().add(txtDate_2);

        txtTime_2 = new JTextField();
        txtTime_2.setColumns(10);
        txtTime_2.setBounds(559, 144, 86, 20);
        frame.getContentPane().add(txtTime_2);

        JLabel lblOrigin = new JLabel("Origin");
        lblOrigin.setBounds(81, 62, 46, 14);
        frame.getContentPane().add(lblOrigin);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(81, 118, 136, 14);
        frame.getContentPane().add(lblDestination);

        JLabel lblEnterPassengers = new JLabel("Enter Nr Of Passengers");
        lblEnterPassengers.setBounds(81, 179, 136, 14);
        frame.getContentPane().add(lblEnterPassengers);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(81, 235, 46, 14);
        frame.getContentPane().add(lblPrice);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(399, 62, 46, 14);
        frame.getContentPane().add(lblDate);

        JLabel lblDate_2 = new JLabel("Date");
        lblDate_2.setBounds(399, 118, 46, 14);
        frame.getContentPane().add(lblDate_2);

        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(559, 62, 46, 14);
        frame.getContentPane().add(lblTime);

        JLabel lblTime_2 = new JLabel("Time");
        lblTime_2.setBounds(559, 119, 46, 14);
        frame.getContentPane().add(lblTime_2);

        JSpinner spPassengers = new JSpinner();
        spPassengers.setEnabled(true);


        spPassengers.setBounds(81, 204, 29, 20);
        frame.getContentPane().add(spPassengers);

        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {     
                    frame.getContentPane().removeAll();
            pay(frame);
            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
             }
         });
        btnBook.setBounds(278, 362, 89, 23);
        frame.getContentPane().add(btnBook);

    }
    
    public void pay(JFrame frame){
    	contentPane = new JPanel();
    	user_logout(frame);
		
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(contentPane);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(77, 126, 46, 14);
        frame.getContentPane().add(lblPrice);

        JLabel lblDisplayPrice = new JLabel("Displaying Price");
        lblDisplayPrice.setBounds(161, 126, 162, 14);
        frame.getContentPane().add(lblDisplayPrice);

        JLabel lblCardNr = new JLabel("Card Nr:");
        lblCardNr.setBounds(77, 172, 46, 14);
        frame.getContentPane().add(lblCardNr);

        txtCardNr = new JTextField();
        txtCardNr.setBounds(161, 169, 342, 20);
        frame.getContentPane().add(txtCardNr);
        txtCardNr.setColumns(10);

        JLabel lblReceipt = new JLabel("Receipt sent to:");
        lblReceipt.setBounds(77, 222, 150, 14);
        frame.getContentPane().add(lblReceipt);

        JLabel lblUserEmail = new JLabel("User Email");
        lblUserEmail.setBounds(161, 270, 226, 14);
        frame.getContentPane().add(lblUserEmail);

        JButton btnPay = new JButton("Pay");
        btnPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); 
                initialize(frame);
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
            }
        });
        btnPay.setBounds(77, 328, 89, 23);
        frame.getContentPane().add(btnPay);
    }
    
    public void adminMain(JFrame frame){
    	contentPane = new JPanel();
    	user_logout(frame);
		
    	frame.getContentPane().setLayout(null);
        frame.getContentPane().add(contentPane);

        txtOrigin = new JTextField();
        txtOrigin.setColumns(10);
        txtOrigin.setBounds(35, 60, 161, 20);
        frame.getContentPane().add(txtOrigin);

        JLabel lblOrigin = new JLabel("Origin");
        lblOrigin.setBounds(35, 35, 46, 14);
        frame.getContentPane().add(lblOrigin);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(206, 35, 100, 14);
        frame.getContentPane().add(lblDestination);

        txtDestination = new JTextField();
        txtDestination.setColumns(10);
        txtDestination.setBounds(206, 60, 164, 20);
        frame.getContentPane().add(txtDestination);

        JDateChooser dateOrigin = new JDateChooser();
        dateOrigin.setBounds(380, 60, 95, 20);
        frame.getContentPane().add(dateOrigin);

        JButton button = new JButton("Search");
        button.setBounds(496, 59, 89, 23);
        frame.getContentPane().add(button);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(35, 96, 550, 321);
        frame.getContentPane().add(scrollPane);

        DefaultTableModel model = new DefaultTableModel();

        table = new JTable(model);

        model.addColumn("Origin");
        model.addColumn("Destination");
        model.addColumn("Date");

        for(int i = 0; i < 10; i++){
            model.insertRow(i,new Object[] {"test","test","test"});
        }

        scrollPane.setViewportView(table);

        JButton btnAddFlight = new JButton("Add Flight");
        btnAddFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); 
                adminEditAdd(frame);
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
            }
        });
        btnAddFlight.setBounds(606, 93, 89, 23);
        frame.getContentPane().add(btnAddFlight);

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); 
                adminEditAdd(frame);
                frame.getContentPane().validate();
                frame.getContentPane().repaint();		
            }
        });
        btnEdit.setBounds(606, 142, 89, 23);
        frame.getContentPane().add(btnEdit);

        JButton btnRemove = new JButton("Remove");
        btnRemove.setBounds(606, 190, 89, 23);
        frame.getContentPane().add(btnRemove);
    }
    
    public void adminEditAdd(JFrame frame){
    	contentPane = new JPanel();
    	user_logout(frame);
		
    	frame.getContentPane().setLayout(null);
        frame.getContentPane().add(contentPane);

        txtOrigin = new JTextField();
        txtOrigin.setBounds(119, 83, 178, 20);
        frame.getContentPane().add(txtOrigin);
        txtOrigin.setColumns(10);

        txtDestination = new JTextField();
        txtDestination.setColumns(10);
        txtDestination.setBounds(409, 83, 178, 20);
        frame.getContentPane().add(txtDestination);

        txtDepartureTime = new JTextField();
        txtDepartureTime.setColumns(10);
        txtDepartureTime.setBounds(119, 191, 178, 20);
        frame.getContentPane().add(txtDepartureTime);

        txtArrivalTime = new JTextField();
        txtArrivalTime.setColumns(10);
        txtArrivalTime.setBounds(409, 191, 178, 20);
        frame.getContentPane().add(txtArrivalTime);

        txtPricePerSeat = new JTextField();
        txtPricePerSeat.setColumns(10);
        txtPricePerSeat.setBounds(409, 245, 178, 20);
        frame.getContentPane().add(txtPricePerSeat);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); 
                adminMain(frame);
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
            }
        });
        btnSubmit.setBounds(304, 336, 89, 23);
        frame.getContentPane().add(btnSubmit);

        JDateChooser dateDeparture = new JDateChooser();
        dateDeparture.setBounds(119, 135, 178, 20);
        frame.getContentPane().add(dateDeparture);

        JDateChooser dateArrival = new JDateChooser();
        dateArrival.setBounds(409, 135, 178, 20);
        frame.getContentPane().add(dateArrival);

        JSpinner spNrOfSeats = new JSpinner();
        spNrOfSeats.setBounds(119, 245, 89, 20);
        frame.getContentPane().add(spNrOfSeats);

        JLabel lblOrigin = new JLabel("Origin");
        lblOrigin.setBounds(119, 61, 46, 14);
        frame.getContentPane().add(lblOrigin);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(408, 61, 89, 14);
        frame.getContentPane().add(lblDestination);

        JLabel lblDepartureDate = new JLabel("Departure date");
        lblDepartureDate.setBounds(119, 114, 89, 14);
        frame.getContentPane().add(lblDepartureDate);

        JLabel lblArrivalDate = new JLabel("Arrival Date");
        lblArrivalDate.setBounds(409, 110, 151, 14);
        frame.getContentPane().add(lblArrivalDate);

        JLabel lblDepartureTime = new JLabel("Departure Time");
        lblDepartureTime.setBounds(119, 166, 124, 14);
        frame.getContentPane().add(lblDepartureTime);

        JLabel lblArrivalTime = new JLabel("Arrival Time");
        lblArrivalTime.setBounds(409, 166, 124, 14);
        frame.getContentPane().add(lblArrivalTime);

        JLabel lblSeats = new JLabel("Seats");
        lblSeats.setBounds(119, 222, 46, 14);
        frame.getContentPane().add(lblSeats);

        JLabel lblPricePerSeat = new JLabel("Price per seat");
        lblPricePerSeat.setBounds(409, 220, 96, 14);
        frame.getContentPane().add(lblPricePerSeat);
    }
}
