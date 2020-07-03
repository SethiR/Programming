package ca.rajatsethi.programming;

/**
 * Created by 182362434 on 2019/01/30.
 */
public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + this.name + "," + this.price + "}";
    }

    public int getPrice() {
        return price;
    }
}
