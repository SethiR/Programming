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
