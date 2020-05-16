package ca.rajatsethi.programming;

public interface ProductLookupTable {

    Product lookupByID(int id);
    void addProduct(Product productToAdd);
    void clear();

}
