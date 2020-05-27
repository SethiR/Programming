package ca.rajatsethi.programming;

import java.util.HashMap;
import java.util.Map;

public class Java8Enhacements {
    public static void main(String[] args) {
        final Map<Integer, Product> products = new HashMap<>();

        products.put(1, new Product(1, "Door", 20));
        products.put(2, new Product(2, "Window", 25));
        products.put(3, new Product(3, "Frame", 30));


        Product defualtProduct = new Product(-1, "Default", 0);

        // getOrDefault --> If something is not there in the map it will get you default value.
        System.out.println(products.getOrDefault(10, defualtProduct));

        // replace
        System.out.println(products.replace(1, new Product(1, "Big Door", 50)));

        // replaceAll --> with new products of weight 10
        products.replaceAll((id, oldProduct) ->
            new Product(id, oldProduct.getName(), oldProduct.getWeight() + 10)
        );
        System.out.println(products);

        //computeIfAbsent  -> creates new entry in map if the key is missing.
        Product result = products.computeIfAbsent(10, (id) -> new Product(id, "Custom Product", 25));
        System.out.println(result);
        System.out.println(products);


        // with java-8 you can loop on the map itself using lambda expressions
        products.forEach((key, value) ->
        {
            System.out.println(key + " -> " + value);
        });

    }
}
