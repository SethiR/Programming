import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestStaticSQLStatement {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection(DBType.MYSQLDB);
            st = conn.createStatement();
            rs = st.executeQuery("Select * from city");
            rs.last();
            System.out.println("Number of rows : " + rs.getRow());

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
        finally {
            if ( rs != null )
                rs.close();
            if ( st != null )
                st.close();
            if ( conn != null )
                conn.close();
        }


    }
}
