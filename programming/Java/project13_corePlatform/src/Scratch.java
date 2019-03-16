interface IA{
    public void sayHello(String name);
}

public class Scratch {
    public static void main(String[] args) {

        IA ia = (name) -> { System.out.println("Hello " + name); };
        IA ij = (name) -> { System.out.println("Hello sir " + name); };

        ia.sayHello("Raj");
        ij.sayHello("Raj");

    }
}
