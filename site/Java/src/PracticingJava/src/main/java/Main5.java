import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main5 {
    public static void main(String[] args) {

        // Not all methods such as sort, insert in between are supported by collection so use list.
        List<Integer> values = new ArrayList<>();

        values.add(10);
        values.add(20);

        System.out.println(values.get(0));

        values.forEach(System.out::println); // printing each element

    }
}
