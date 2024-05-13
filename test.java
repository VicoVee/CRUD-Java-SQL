import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws FileNotFoundException{
        FileReader file = new FileReader("us-500.csv");
        BufferedReader br = new BufferedReader(file);

        Create_DB test = new Create_DB();
        test.addUsers(br);
    }
}
