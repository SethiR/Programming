package ca.rajatsethi.programming;

import java.util.Comparator;

public class Product{
    // Attributes of the Product Class
    private String name;
    private int weight;

    // Constructor
    public Product(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    // Getters
    public int getWeight() {
        return weight;
    }

    // String representation of product
    @Override
    public String toString() {
        return "Product{ " +
                this.name
                + " , "
                + this.weight
                + "}";
    }

    // implementing the comparator
    public static final Comparator<Product> BY_WEIGHT = Comparator.comparing(Product::getWeight);

}