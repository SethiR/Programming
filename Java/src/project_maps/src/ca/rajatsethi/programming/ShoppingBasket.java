package ca.rajatsethi.programming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShoppingBasket {

    private final List<Product> products = new ArrayList<>();
    private int total_weight = 0;

    public void add(Product product){
        products.add(product);
        total_weight += product.getWeight();
    }

    public List<Product> getItems(){
        return Collections.unmodifiableList(products);
    }

    @Override
    public String toString() {
        return "Shopping basket of " + products + "with weight of " + total_weight + " kg";
    }

    public static void main(String[] args) {
        ShoppingBasket s1 = new ShoppingBasket();
        s1.add(new Product(1, "Apple", 10));

        System.out.println(s1);

//        s1.getItems().add(new Product(2, "Banana", 2));
//        System.out.println(s1);
    }
}
