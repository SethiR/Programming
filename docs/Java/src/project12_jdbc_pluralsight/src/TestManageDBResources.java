import java.sql.*;

public class TestManageDBResources {
    public static void main(String[] args) throws SQLException {

        Connection con = null;
        try{
            con = DBUtil.getConnection(DBType.MYSQLDB);
            System.out.println("Connection Successful");
        }
        catch (SQLException e){
            DBUtil.showErrorMessage(e);
        }
        finally {
            if (con!=null) {
                con.close();
            }
        }
    }
}
