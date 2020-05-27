import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class TestConnectionSQLite
{
    public static void main(String[] args) throws Exception
    {
//        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/182362434/dbA.db");

        String query = "insert into customer (firstName, lastName, email, dob) values (?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(query);

        String firstName = getUserInput("Enter your first name : ");
        String lastName = getUserInput("Enter your last name : ");
        String email = getUserInput("Enter your email : ");
        String dob = getUserInput("Enter your dob in yyyy-mm-dd format : ");

        st.setString(1, firstName);
        st.setString(2, lastName);
        st.setString(3, email);
        st.setString(4, dob);

        int rowsAffected = st.executeUpdate();
        System.out.println("Number of row(s) affected : " + rowsAffected);

        st.close();
        con.close();
    }

    private static String getUserInput(String message)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        return scan.nextLine().strip();
    }
}