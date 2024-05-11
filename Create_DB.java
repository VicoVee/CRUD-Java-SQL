import java.sql.Connection; //An object that holds the Connection to DB
import java.sql.DriverManager; //Connects Java to MySQL
import java.sql.SQLException; //Catch Errors
import java.sql.Statement; //create statements with ease


//Creating an Insert Statement in Java
public class Create_DB {
    static String dbURL = "jdbc:mysql://localhost:3306/sampledb"; //Get Connection Address
    static String username = "root"; //Get the user
    static String password = "916192991"; //Get the password

    public static void main(String[] args) {
        //Create a Connection object. This will allow us to connect to the DB
        //After everything under the try is completed, the connection will automatically close
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            //First, write the insert statement in SQL
            //Then convert to String and place in a Java String Object
            //Note: Need to define the parameters (username, pass, etc) because the primary key
            //is already auto_incremented
            String userBob = "INSERT INTO Users (username, password, fullname, email) VALUES(?, ?, ?, ?)";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}