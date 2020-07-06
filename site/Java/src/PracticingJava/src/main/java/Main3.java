/*
Working with collections.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Main3 {
    public static void main(String[] args) {

        // Collection of objects
        Collection values = new ArrayList();
        values.add("hello");
        values.add("1");
        values.add(32);
        values.add(6.19f);
        Iterator i = values.iterator(); // use iterator to fetch values from collection
        System.out.println(i.next());
        System.out.println(i.next());

        while(i.hasNext()){
            System.out.println(i.next());
        }

        for(Object x : values){
            System.out.println(x);
        }
    }
}
