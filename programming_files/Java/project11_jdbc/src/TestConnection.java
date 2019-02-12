import java.sql.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","admin","admin");
        Statement st = con.createStatement();

    }
}
