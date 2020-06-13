package ca.rajatsethi.programming;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ViewsOverMaps {
    public static void main(String[] args) {
        final Map<Integer, Product> products = new HashMap<>();

        products.put(1, new Product(1, "Door", 35));
        products.put(2, new Product(2, "Window", 55));
        products.put(3, new Product(3, "Frame", 75));

        System.out.println(products);
        System.out.println();

        Set<Integer> ids = products.keySet();
        System.out.println(ids);
        System.out.println();

        // if you remove something from this set of ids now it also gets removed from the products map
        ids.remove(1);
        System.out.println(ids);
        System.out.println(products);
        System.out.println();


        // values()
        Collection<Product> values = products.values();
        System.out.println(values);
        System.out.println();

        Set<Map.Entry<Integer, Product>> entries = products.entrySet();
        System.out.println(entries);
        System.out.println();
        for (Map.Entry<Integer, Product> entry : entries){
            System.out.println(entry);
            if (entry.getKey() == 2){
                entry.setValue(new Product(entry.getKey(), "Pipe", 10));  // you can update the value for the entry
            }
        }
        System.out.println(entries);
        System.out.println();

    }
}
