package ca.rajatsethi.programming;

import java.util.HashMap;
import java.util.Map;

public class MapProductLookupTable implements ProductLookupTable{

    private final Map<Integer, Product> products = new HashMap<>();

    @Override
    public Product lookupByID(int id) {
        return products.get(id);
    }

    @Override
    public void addProduct(Product productToAdd) {
        if (products.containsKey(productToAdd.getId())){
            throw new IllegalArgumentException("Unable to add product, id already exists : " + productToAdd.getId());
        }
        products.put(productToAdd.getId(), productToAdd);
    }

    @Override
    public void clear() {
        products.clear();
    }
}
