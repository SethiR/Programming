import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String SQLiteURL = "jdbc:sqlite:C:/Users/182362434/dbA.db";
    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/world";
    private static final String MYSQLuser = "admin";
    private static final String MYSQLpassword = "admin";

    public static Connection getConnection(DBType dbType) throws SQLException {
        switch (dbType){
            case SQLITEDB:
                return DriverManager.getConnection(SQLiteURL);
            case MYSQLDB:
                return DriverManager.getConnection(MYSQLURL, MYSQLuser, MYSQLpassword);
            default:
                return null;
        }
    }

    public static void showErrorMessage(SQLException e){
        System.err.println("Error : " + e.getMessage());
        System.err.println("Error code : " + e.getErrorCode());
    }

}
