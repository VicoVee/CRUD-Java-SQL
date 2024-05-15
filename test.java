import java.io.BufferedReader; //Reads through file (per line)
import java.io.FileNotFoundException;
import java.io.FileReader; //Takes a file and prep for buffered reader
import java.io.IOException; //Catches Error
import java.sql.Connection; //Create connection to MySQL/Database
import java.sql.DriverManager; //Connects SQL connection and Java
import java.sql.SQLException; //Catches SQL Errors

public class test {
    static String dbURL = "jdbc:mysql://localhost:3306/sampledb"; // Get Connection Address
    static String username = "root"; // Get the user
    static String password = "916192991"; // Get the password

    public static void main(String[] args) throws FileNotFoundException {
        FileReader file = new FileReader("us-500.csv");
        BufferedReader br = new BufferedReader(file);

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            Create_DB test = new Create_DB();
           //test.addUsers(con, br);
           //test.viewAllUsers(con);
           test.countAllUsers(con);

        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }
}
