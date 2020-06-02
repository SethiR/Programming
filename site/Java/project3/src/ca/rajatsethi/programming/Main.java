package ca.rajatsethi.programming;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Product door = new Product("Wooden Door", 35);
        Product window = new Product("Wooden Window", 15);

        Product[] products = {door, window};
        System.out.println(Arrays.toString(products));  // print out in human legible format.
        // https://docs.oracle.com/javase/9/docs/api/java/util/Arrays.html#toString-java.lang.Object:A-

        // just to add we have to implement another function as arrays do not resize.
        Product handle = new Product("Handle", 4);
        products = add(products, handle);

        // arrays also do not check for duplicate, you can add handle again.
        products = add(products, handle);
        System.out.println(Arrays.toString(products));

    }

    // we wish to add another product to the products array. The problem is that the array cannot be resized.
    // so lets create another method which adds functionality to add another product to the array.

    public static Product[] add(Product[] array, Product product){
        int length = array.length;
        Product[] newArray = Arrays.copyOf(array, length+1);
        newArray[length] = product;
        return newArray;
    }
}
