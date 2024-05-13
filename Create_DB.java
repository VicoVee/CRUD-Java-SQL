import java.sql.Connection; //An object that holds the Connection to DB
import java.sql.DriverManager; //Connects Java to MySQL
import java.sql.PreparedStatement;
import java.sql.SQLException; //Catch Errors
import java.sql.Statement; //create statements with ease
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

//Creating an Insert Statement in Java
public class Create_DB {
    private final String COMMA_DELIMITER = ","; // Identify the columns in the CSV

    public void addUsers(Connection con, BufferedReader br) {
        // Holds all the parameters for the SQL statement
        // List<String> userInfo = new ArrayList<>();

        try {
            String line;
            br.readLine(); // Skips the column names

            // While the line is NOT NULL, read given columns to add to parameters
            while ((line = br.readLine()) != null) {
                // Split lines to appropiate columns and save each column as a String array
                String[] temp = line.split(COMMA_DELIMITER);

                // Removing the double quotes (") from each column O(1) and reinserting into
                // String array
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = temp[i].replaceAll("\"", "");
                }

                /**
                 * ~User Creation~
                 * Username: Firstname + First Initial
                 * Password: First 3 char of last name + Zip Code
                 * Fullname: First + Last Name
                 * Email: The email
                 */
                String username = temp[0] + temp[1].substring(0, 2);
                String password = temp[1].substring(0, 2) + temp[7];
                String fullname = temp[0] + " " + temp[1];
                String email = temp[10];

                // Create SQL statement
                String createUser = "INSERT INTO Users (username, password, fullname, email) VALUES (?, ?, ?, ?)";
                PreparedStatement insertUser = con.prepareStatement(createUser);

                // Create SQL statement
                insertUser.setString(1, username);
                insertUser.setString(2, password);
                insertUser.setString(3, fullname);
                insertUser.setString(4, email);

                // Execute SQL statement
                insertUser.executeUpdate();
            }
        } catch (IOException ioe) {
            System.out.println("Oop something went wrong and I have no clue (file input error)");
            ioe.printStackTrace();
            System.exit(1);
        } catch (SQLException sqlE) {
            System.out.println("Oh no, something wrong with the SQL");
            sqlE.printStackTrace();
        }
    }

    // Next create a method to print out all Users
    // Select Statements in SQL next
    // System.out.println(String.format("%s %s %s %s", username, password, fullname,
    // email));
    public void viewAllUsers(Connection con) throws SQLException {
        Statement viewUsers = con.createStatement();

    }

    // public static void main(String[] args) {
    // //Create a Connection object. This will allow us to connect to the DB
    // //After everything under the try is completed, the connection will
    // automatically close
    // try (Connection conn = DriverManager.getConnection(dbURL, username,
    // password)) {
    // //First, write the insert statement in SQL
    // //Then convert to String and place in a Java String Object
    // //Note: Need to define the parameters (username, pass, etc) because the
    // primary key
    // //is already auto_incremented? Will check on this later
    // String userBob = "INSERT INTO Users (username, password, fullname, email)
    // VALUES(?, ?, ?, ?)";

    // //Insert each parameter manually using prepareStatement
    // PreparedStatement insertBob = conn.prepareStatement(userBob);
    // //Since each parameter is a varchar/string, use the setString method
    // insertBob.setString(1, "BobLovesMe");
    // insertBob.setString(2, "password134");
    // insertBob.setString(3, "Bob Larry");
    // insertBob.setString(4, "lovemenots@yahoo.com");

    // //executeUpdate() runs the SQL command and sends to database
    // //Returns the number of rows returned or 0 if nothing is returned
    // int rowInserted = insertBob.executeUpdate();
    // if(rowInserted > 0){
    // System.out.println("A user has been inserted");
    // }

    // //Testing something else
    // String userMarie = "INSERT INTO Users (username, password, fullname, email)
    // VALUES (?, ?, ?, ?)";

    // PreparedStatement insertMarie = conn.prepareStatement(userMarie);

    // for(int i = 0; i < arr.length; i++){
    // insertMarie.setString(i+1, arr[i]);
    // }
    // rowInserted = insertMarie.executeUpdate();
    // if(rowInserted > 0){
    // System.out.println("Marie has been added as a user!");
    // }
    // else {
    // System.out.println("Insertion Failed");
    // }
    // } catch (SQLException ex) {
    // ex.printStackTrace();
    // }
    // }
}