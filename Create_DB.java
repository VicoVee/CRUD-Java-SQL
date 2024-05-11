import java.sql.Connection; //Connect to database?
import java.sql.DriverManager;
import java.sql.SQLException; //Catch Errors
import java.sql.Statement; //create SELECT statements with ease

public class Create_DB {

    static String dbURL = "jdbc:mysql://localhost:3306/sampledb"; //Get Connection Address
    static String username = "root"; //Get the user
    static String password = "916192991"; //Get the password

    public static void main(String[] args) {
        try {
            //Create a Connection object. This will allow us to connect to the DB
            Connection conn = DriverManager.getConnection(dbURL, username, password);

            //For now, we just want to check if the connection is working.
            //If the connection works, then just print out Connected
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}