package ca.rajatsethi.programming;

/**
 * Created by sethir on 2019/02/01.
 */
public class Enquiry {
    private final Customer customer;
    private final Category category;

    public Enquiry(Customer customer, Category category) {
        this.customer = customer;
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Enquiry{customer = " +
                customer +
                ", category = "+
                category +
                "}";
    }
}
