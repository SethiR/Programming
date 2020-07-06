/*
Vector (ThreadSafe) (Slow) vs ArrayList (Not ThreadSafe) (Fast)  --> Prefer ArrayList to Vector.

Vector is array underneath --> the array size is increased once the capacity is reached.

Vector and ArrayList both uses Array internally as data structure.

Vector’s methods are synchronized and ArrayList’s methods are not synchronized

In general, executing a ‘synchronized’ method results in costlier performance than a unsynchronized method. Keeping
the difference in mind, using Vector will incur a performance hit than the ArrayList. But, when there is a certain need
 for thread-safe operation Vector needs to be used.

 When you use Vector or ArrayList, always initialize to the largest capacity that the java program will need. Since
 incrementing the size is a costlier operation.


 */

import java.util.Vector;

public class Main6 {
    public static void main(String[] args) {

        Vector<Integer> v = new Vector<>();
        v.add(10);

        System.out.println(v.capacity());  // vector will always have a capacity. (fixed length) because underneath its an array.
    }
}
