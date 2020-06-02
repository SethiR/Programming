package ca.rajatsethi.programming;

import java.util.ArrayList;
import java.util.List;

public class NaiveProductLookupTable implements ProductLookupTable{

    private List<Product> products = new ArrayList<>();

    @Override
    public Product lookupByID(int id) {
        for (Product product : products){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product productToAdd) {
        for (Product product : products){
            if (product.getId() == productToAdd.getId()){
                throw new IllegalArgumentException("Unable to add : duplicate id : "
                        + product.getId());  // throwing exception because we found a duplicate id
            }
        }
        products.add(productToAdd);
    }

    @Override
    public void clear() {
        products.clear();
    }
}
