import java.sql.*;

public class TestConnection
{
    public static void main(String[] args) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","admin","admin");

//        String query = "select * from country where Code = \"IND\"";
        String query = "select * from country";
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(query);
//        rs.next();

        while (rs.next())
        {
            String name = rs.getString("Name");
            System.out.println(name);
        }

        st.close();
        con.close();
    }
}
