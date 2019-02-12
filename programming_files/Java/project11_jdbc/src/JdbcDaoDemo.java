/*
A data access object (DAO) is an object that provides an abstract interface to
some type of database or other persistence mechanism.
The DAO should do the CRUD operations.
 */

import java.sql.*;

class Person
{
    int id;
    String firstName;
    String lastName;
    String email;
    String dob;

    Person(){}

    public Person(String firstName, String lastName, String email, String dob)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }

    @Override
    public String toString()
    {
        return "Person{id = " + id + ", firstName = " + firstName + ", lastName = " + lastName +
                ", email = " + email + ", dob = " + dob + "}";
    }
}

class PersonDAO
{
    public Person getPerson(int id) throws Exception
    {
        try
        {

            Person p = new Person();
            p.id = id;

            String query = "Select * from Customer where id = " + id;

            Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/182362434/dbA.db");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();

            p.firstName = rs.getString("firstName");
            p.lastName = rs.getString("lastName");
            p.dob = rs.getString("dob");

            st.close();
            con.close();

            return p;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public int addPerson(Person p) throws Exception{

        Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/182362434/dbA.db");
        PreparedStatement st = con.prepareStatement("insert into customer (firstName, lastName, email, dob) values (?, ?, ?, ?)");

        st.setString(1, p.firstName);
        st.setString(2, p.lastName);
        st.setString(3, p.email);
        st.setString(4, p.dob);

        int result =  st.executeUpdate();

        st.close();
        con.close();

        return result;

    }

}

// Main class from here onwards. If you see below the main class is executing simple looking statements.
// Its very abstract here.
public class JdbcDaoDemo
{
    public static void main(String[] args) throws Exception
    {
        PersonDAO dao = new PersonDAO();

        // Getting someone with id = 1
        Person p = dao.getPerson(1);
        System.out.println(p);

        // Getting someone with id = 2
        p = dao.getPerson(2);
        System.out.println(p);

        // Inserting someone into the database and then fetching him and displaying the value.
        Person raj = new Person("Raj", "Kuth", "rk@gmail.com", "2010-01-01");
        dao.addPerson(raj);
        p = dao.getPerson(3);
        System.out.println(p);


    }
}
