package ca.rajatsethi.programming;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shipment implements Iterable<Product>{

    private static final int PRODUCT_NOT_PRESENT = -1;
    private static final int SMALL_VAN_MAX_ITEM_WEIGHT = 20;

    private List<Product> products = new ArrayList<>();  // products arraylist
    private List<Product> small_van_products = new ArrayList<>();
    private List<Product> large_van_products = new ArrayList<>();

    // Getters
    public List<Product> getSmall_van_products() {
        return small_van_products;
    }

    public List<Product> getLarge_van_products() {
        return large_van_products;
    }

    // Iterator
    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    // Adding product
    public void add(Product p)
    {
        products.add(p);
    }

    // replacing the product
    public void replace(Product oldProduct, Product newProduct)
    {
        int oldProductIndex = products.indexOf(oldProduct);
        if (oldProductIndex != PRODUCT_NOT_PRESENT)
        {
            products.set(oldProductIndex, newProduct);
        }
    }

    // Seperating products into different Vans
    public void prepare()
    {
        products.sort(Product.BY_WEIGHT);  // sort by weight ascending.
        int splitPoint = findSplitPoint();
        small_van_products = products.subList(0, splitPoint);
        large_van_products = products.subList(splitPoint, products.size());
    }

    // Helper product
    private int findSplitPoint() {
        for(Product p: products){
            if (p.getWeight() > SMALL_VAN_MAX_ITEM_WEIGHT){
                return products.indexOf(p);
            }
        }
        return 0;
    }
}
