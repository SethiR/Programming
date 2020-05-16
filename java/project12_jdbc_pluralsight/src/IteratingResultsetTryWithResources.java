import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IteratingResultsetTryWithResources {
    public static void main(String[] args) throws SQLException {

        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("Select * from city")
        ) {

            while (rs.next()){
                System.out.println(rs.getString(1) + rs.getString(2));
            }

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
    }
}
