import java.sql.*;

public class TestSQLiteConnection {
    public static void main(String[] args) throws SQLException {

        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:sqlite:C:/Users/182362434/dbA.db");
            System.out.println("Connection Successful");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            con.close();
        }

    }
}
