## Problems with arrays

- Cannot resize
- very low level concept
- do not provide many functionality like add, duplicacy check etc...

With below example I try to show the problems with arrays. lets say we have a simple class `Product` and we are doing some operations on it in the main program below

```Java
// Product.java
package ca.rajatsethi.programming;

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
}
```

```Java
// main.java
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

```

```java
// Console output
[Product{Wooden Door,35}, Product{Wooden Window,15}]
[Product{Wooden Door,35}, Product{Wooden Window,15}, Product{Handle,4}, Product{Handle,4}]
```

---

**Java Collections**

- Java collections ships with the JDK
- Any application in Java (which is not very basic) will use the collections frameworks.
- The data structures are diverse
    - Some of them provide ordered access.
    - Some of them provide uniqueness.
    - Some of them provide ability for pairs.


## Defining collections

__Collection of Collections__

All the java interfaces which deal with collections extend the `Collection`.

Types of collections

- Lists (Array list and linked list)
- Sets (Hash Set)
    - Sorted Set (Tree set)
- Queue (Priority queue) and Deque or double ended queue (Linked list and Array Deque)
- Map (Hash map) and Sorted Map (Treemap)

Lists is interface its implementation is array list and linked list. Same as others. Outside brackets --> interfaces, inside brackets --> their specific implementations. The interface will drive their charactersistics, but for 1 interface there can be multiple implementations e.g. for list we have 2.

---

**Collection Behaviour**

- All collections extend the `iterable` interface.

Some of the other methods in the collection interface are shown below.

- size()
- isEmpty()
- add(element)
- addAll(collection)
- remove(element)
- removeAll(collection)
- retainall(collection)
- contains(element)
- containsAll(collection)
- clear()

Example of working with collections (in this case we are using `ArrayList` ) is given below.

```Java
// Product.java
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
```

```Java
// main.java
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
```


## Lists

There are 2 types of lists.

- Arraylist
- Linked List

In the example below we are using ArrayList and wrapping it in out Shipment class which represents something in our domain. (This is a common practice). The shipment class will have functions which make sense in the real world and in the background it will use Arraylist.

```Java
// Product.Java
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
```

```Java
// Shipment.Java
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
```

```Java
// main.java
package ca.rajatsethi.programming;

public class Main {

    public static void main(String[] args) {
        Shipment ship = new Shipment();

        Product floorPannel = new Product("Floor Pannel", 30);
        Product window = new Product("Window", 10);
        Product door = new Product("Door", 45);

        ship.add(floorPannel);
        ship.add(window);
        ship.add(door);

        // Because we implemented the iterable on the Product for the
        // Shipment class we can iterate over it.
        for(Product p: ship){
            System.out.println(p);
        }

        ship.prepare();

        System.out.println("Small Van products = " + ship.getSmall_van_products());
        System.out.println("Large Van products = " + ship.getLarge_van_products());
    }
}
```

---

## Sets

There are the following types of set implementations which are avaiable to us.

- HashSet
- TreeSet
- EnumSet (Designed to be efficient when dealing with enum types)

---

__Hash Set__

- These are based on HashMap. (Calls `hashCode()` on element and looks up the location).
- Hash Sets are good general purpose implementations.
- They resize when run out of space.

_How `equals` works_

Standard java checks the equals using the hash code. If 2 objects hashcode value is same then they are equals. i.e. they are the same object. Your implementation of equals can differ and you will have to override it.

```Java
object.hashCode() == object.hashCode()
```


__Tree Set__

- Tree set is based on tree map. Similarly hash set was based on hash map.
- Uses sorted binary tree.
- It keeps the elements in specified order. (it implements `SortedSet` and `NavigableSet`)


__Enum Set__

- Only allow to store enum objects.
- Uses a bitset based on ordinal of the enum.

Two other interfaces  mentioned below which extend the behaviour of Sets. They talk about enforcing orders.

- SortedSet
- NavigableSet

_SortedSet_

- E first()
- E last()
- SortedSet<E> tailSet(E fromElement)
- SortedSet<E> headSet(E toElement)
- SortedSet<E> subSet(E fromElement, E toElement)

_NavigableSet_
This extends sortedSet and is implemented by Treeset.

- E lower(E e)
- E higher(E e)
- E floor(E e)
- E cieling(E e)
- E pollFirst()
- E pollLast()

---

## Queues Deque and Stacks

__Queue__

- First In First Out

Methods

- offer() --> Use offer method instead of add() when adding to queue. As some of the queues are bounded (max in queue) and if you use add() method to add something to queue when its full it throws an exception. offer() will just return false.
- poll() --> remove and return value. The remove() method throws exception when queue is empty and you wish to remove something so instead of using it use poll() method.
- peek() --> use peek, element() throws exception when empty, peek returns null.


Basic example of Queue provided below. The below queue has been implemented using the LinkedList implementation.

```Java
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        // Instanciating a new queue of type linked list
        Queue<Integer> q = new LinkedList<>();

        // adding elements to queue
        q.add(10);
        q.add(20);

        // printing out the queue
        System.out.println(q);

        // get first element
        System.out.println(q.element());
        System.out.println(q);

        // iterating over queue
        for (int i: q ) { System.out.println(i); }

        // remove element from the queue
        System.out.println("Remove element from queue : " + q.remove());
        System.out.println(q);
    }
}
```

A more real world example of queue is provided below.