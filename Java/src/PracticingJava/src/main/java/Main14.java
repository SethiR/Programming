// A functional interface which has 1 unimplemented method as below. (It can have other default methods which are implemented)
interface Printer{
    void print(String message);
}

public class Main14 {
    public static void main(String[] args) {
        show();
    }

    public static void show(){
        String prefix = "-";
        greet( message -> System.out.println(prefix + message));  // lambda expression
    }
    public static void greet(Printer printer){
        printer.print("Hello World");
    }
}
