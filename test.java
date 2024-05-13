import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    static String dbURL = "jdbc:mysql://localhost:3306/sampledb"; // Get Connection Address
    static String username = "root"; // Get the user
    static String password = "916192991"; // Get the password
    // static String[] arr = {"MarioBros", "nintendo64", "Mario Luigi",
    // "nintendo@gmail.com"};

    public static void main(String[] args) throws FileNotFoundException {
        FileReader file = new FileReader("us-500.csv");
        BufferedReader br = new BufferedReader(file);

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            Create_DB test = new Create_DB();
            test.addUsers(con, br);

        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }
}
