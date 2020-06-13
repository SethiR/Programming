import java.sql.*;

public class ResultsetScrollableDemo {
    public static void main(String[] args) {

        try(
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);  // lets say we had oracle databasesa
                // creating a scrollable and read only resultset
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY) ;
                ResultSet rs = ((Statement) st).executeQuery("Select * from Customer");

                ){

            rs.beforeFirst();  // move the cursor to before first record;
            rs.absolute(1); // goto first row
            System.out.println(rs.getString(1) + " : " + rs.getString(2));

        }catch (SQLException e){
            DBUtil.showErrorMessage(e);
        }

    }
}
