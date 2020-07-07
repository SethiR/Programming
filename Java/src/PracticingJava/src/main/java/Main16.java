import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main16 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        // Creating an implementation of Consumer Interface.
        Consumer<String> print = System.out::println;
        Consumer<String> printUpperCase = (item) -> System.out.println(item.toUpperCase());

        list.forEach(print.andThen(printUpperCase).andThen(print));
    }
}
