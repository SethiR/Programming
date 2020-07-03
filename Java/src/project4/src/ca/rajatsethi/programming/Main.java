package ca.rajatsethi.programming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        Product door = new Product("Door", 15);
        Product floorPannel = new Product("Floor Pannel", 60);
        Product window = new Product("Window", 30);

        Collection<Product> products = new ArrayList<>(); // creating a collection of products <T> -> <Product>

        // now that our collection is created we can add our products to the `products` collection.
        products.add(door);
        products.add(floorPannel);
        products.add(window);

        // printing out the whole collection
        System.out.println(products);


        // iterating on collection using for loop.
        for(Product product: products){
            System.out.println(product);
        }


        // iterating on collection the long way.
        // this way is useful if you wish to modify the collection while looping on it
        // e.g. if you wish to remove product from the products collection while looping on it as shown below.
        final Iterator<Product> productIterator = products.iterator();
        while(productIterator.hasNext()){
            Product product = productIterator.next();
            if (product.getPrice() > 20) {
                System.out.println(product);
            }
            else{
                productIterator.remove();  // if price is less than that then remove from the collection.
            }
        }
        // printing out the whole collection to confirm its removed.
        System.out.println(products);

        // trying other methods
        System.out.println("Is collection empty : " + products.isEmpty());
        System.out.println("Collection size : " + products.size());
        System.out.println("Contains floorPannel : " + products.contains(floorPannel));

    }
}
