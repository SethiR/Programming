package ca.rajatsethi.programming;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by sethir on 2019/02/01.
 */
public class HelpDesk
{

    private final Queue<Enquiry> enquiries = new PriorityQueue<>(BY_CATEGORY);  // creating a priority queue by using category comparator.

    public void eqnuire(final Customer customer, Category category)
    {
        enquiries.offer(new Enquiry(customer, category));
    }

    public static final Comparator<Enquiry> BY_CATEGORY = new Comparator<Enquiry>() {  // implementing comparator.
        @Override
        public int compare(Enquiry o1, Enquiry o2) {
            return o1.getCategory().compareTo(o2.getCategory());
        }
    };

    public void processAllEnquiries()
    {
        Enquiry enquiry;
        while ((enquiry = enquiries.poll()) != null)
        {
            enquiry.getCustomer().reply("Have you tried turning if off and on again?");
        }
    }

    public static void main(String[] args)
    {
        HelpDesk helpDesk = new HelpDesk();

        helpDesk.eqnuire(Customer.JACK, Category.TABLET);
        helpDesk.eqnuire(Customer.JILL, Category.PRINTER);
        helpDesk.eqnuire(Customer.MARY, Category.PHONE);

        helpDesk.processAllEnquiries();
    }
}
