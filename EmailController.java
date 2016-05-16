package travelplanner;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
 
 
public class EmailController {
   
    private String user;
    private EmailMock mock;
    //private String dbPath = "/pa1415_group.e2_travelplanner.db";
   
    public EmailController(String user){
        this.user=user;
        mock = new EmailMock();
       
    }
   
    //TASK: Sends the reciept to the email adress of the current logged in user after a successful booking
    public boolean sendRecipt(){
       
        //Get current users email ( ? = private varaible user in the class)
        //Select email From Users where username = ?
        Connection connection = null;
        String email=null;
       
        try {
           
            connection = DriverManager.getConnection("jdbc:sqlite:" + SystemController.dbPath);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
           
            ResultSet rs = statement.executeQuery("SELECT email FROM users WHERE email = '" + this.user +"'");
           
            while(rs.next()) {
                // read the result set
               
                //Save users email in variable (String email)
                //String email = Value from database;
                email=rs.getString("email");
            }
        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
       
       
       
       
       
        String recipt=this.makeRecipt();
       
        boolean ret=mock.sendEmail(email, recipt);
       
        return true;
       
       
       
    }
   
    //TASK: Collects the info about the transcation out of data that is collected from the database
    private String getTranscation(){
       
        //Get current user transaction (? = private varaible user in the class)
        //Select transaction From Transactions where username = ? ORDER BY created desc Limit 1;
        Connection connection = null;
        int transaction=0;
        int price = 0;
        Date date = new Date();
       
        try {
           
            connection = DriverManager.getConnection("jdbc:sqlite:" + SystemController.dbPath);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
           
            ResultSet rs = statement.executeQuery("SELECT * FROM transactions WHERE email = '" + this.user +"'"
                                                  + "ORDER BY transaction_id desc LIMIT 1");
           
            while(rs.next()) {
                //Save data from database in the variable (int transaction)
                //int transaction = Value from database
                transaction=rs.getInt("transaction_id");
                price=rs.getInt("price");
                date=rs.getDate("date");
               
            }
        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
       
        String allInfo="Transaction id: "+ transaction +" Price: "+ price + " Date: "+ date;
        System.out.println(allInfo);
        return allInfo;
    }
    //TASK: Creates the reciept out of data from the getTranscation function
    private String makeRecipt(){
       
        String allInfo = this.getTranscation();
       
        //Get current user info (? = private varaible user in the class)
        // Select * From Users where username = ?
        Connection connection = null;
        String name=null;
        String lastName=null;
        String email=null;
       
        try {
           
            connection = DriverManager.getConnection("jdbc:sqlite:" + SystemController.dbPath);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
           
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE email = '" + this.user +"'");
           
            while(rs.next()) {
                // read the result set
               
                //Save data from database in the variables:
                //(String userName,  String name,  String lastName, String email);
                name=rs.getString("first_name");
                lastName=rs.getString("last_name");
                email=rs.getString("email");
               
            }
        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
       
       
       
        String reciept="Recipt from TravelPlanner \n"
        + "Hello " + name + " " +lastName +"! "+ allInfo + " on your account "
        +" with the register email: " + email ;
       
        System.out.println(reciept);
        return reciept;
       
    }
    public boolean verify(String verifyHash)
    {
       
        String message = "Use this hash to verufy your account: " + verifyHash;
       
        return mock.sendEmail(this.user, message);
       
    }
   
}