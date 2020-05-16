/*
DDL --> Data Definition Language (Create table, change database etc...)

DML --> Data Manipulation Language (INSERT, UPDATE and DELETE statements)
Use --> st.executeUpdate(query)

DQL --> Data Query Language (SELECT, SHOW and HELP statements)
Use --> st.executeQuery(query)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertingValuesStringQuery
{
    public static void main(String[] args) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","admin","admin");

        Statement st = con.createStatement();

        String query = "insert into customer (firstName, lastName, email, dob) values ('Sheldon', 'Cooper', 'Sheldon.Cooper@gmail.com', '1973-01-01')";

        int rowsAffected = st.executeUpdate(query);
        System.out.println("Number of row(s) affected : " + rowsAffected);

        st.close();
        con.close();

    }
}
