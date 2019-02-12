package ca.rajatsethi.programming;

import java.util.Comparator;

public class Product {
    private int id;
    private String name;
    private int weight;

    public Product(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    // Implementing comparator
    public static final Comparator<Product> BY_WEIGHT = Comparator.comparing(Product::getWeight);
    public static final Comparator<Product> BY_NAME = Comparator.comparing(Product::getName);

    @Override
    public String toString() {
        return "{ " + id + ", " + name + ", " + weight + " }";
    }
}
