package ca.rajatsethi.programming;

/**
 * Created by sethir on 2019/02/01.
 */
public class Customer
{
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public void reply(final String message)
    {
        System.out.println(this.name +" : "+ message);
    }

    public static final Customer JACK = new Customer("Jack");
    public static final Customer JILL = new Customer("Jill");
    public static final Customer MARY = new Customer("Mary");
}
