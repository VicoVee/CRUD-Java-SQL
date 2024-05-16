import java.sql.Connection; //An object that holds the Connection to DB
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; //Catch Errors
import java.sql.Statement; //create statements with ease
import java.io.BufferedReader;
import java.io.IOException;

//Creating an Insert Statement in Java
public class Create_DB {
    public void addUsers(Connection con, BufferedReader br) {
        String COMMA_DELIMITER = ","; // Identify the columns in the CSV
        try {
            String line;
            br.readLine(); // Skips the first row, which are the column names

            // Creates a primary key for the row. I decided to not keep auto increment
            // so I will manually add a primary key for the data by checking the current total + 1,
            // then incrementing by 1 when inserting into the statement
            int count = countAllUsers(con) + 1;

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
                String createUser = "INSERT INTO Users VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertUser = con.prepareStatement(createUser);

                // Create SQL statement
                insertUser.setInt(1, count++);
                insertUser.setString(2, username);
                insertUser.setString(3, password);
                insertUser.setString(4, fullname);
                insertUser.setString(5, email);

                // Execute SQL statement
                insertUser.executeUpdate();
            }
            System.out.println("There are " + countAllUsers(con) + " users in the database");
        } catch (IOException ioe) {
            System.out.println("Oop something went wrong and I have no clue (file input error)");
            ioe.printStackTrace();
            System.exit(1);
        } catch (SQLException sqlE) {
            System.out.println("Oh no, something wrong with the SQL");
            sqlE.printStackTrace();
        } 
    }

    // Select Statements in SQL
    // A method to print out all Users first by fullname, then by username
    public void viewAllUsers(Connection con) throws SQLException {
        String viewUsers = "SELECT fullname, username FROM Users";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(viewUsers);

        while (result.next()){
            System.out.println(result.getString(1) + " has been added as user: " + result.getString(2)); 
        }

    }

    //Returns the count of all users in the database
    public int countAllUsers(Connection con) throws SQLException {
        String viewUsers = "SELECT COUNT(fullname) FROM Users";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(viewUsers);

        result.next();
        int count = result.getInt(1);
        return count;
    }

    // Delete Statements in SQL
    // A method that deletes all users in the database, used to clean up test DB
    public void deleteAllUsers(Connection con) throws SQLException {
        String delete = "DELETE FROM Users" ;
        PreparedStatement statement = con.prepareStatement(delete);
        statement.executeUpdate();
        System.out.println("All users have been deleted");
    }
}