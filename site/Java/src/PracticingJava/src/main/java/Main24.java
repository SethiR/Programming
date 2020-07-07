import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main24 {
    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);

        list2.add(4);
        list2.add(5);
        list2.add(6);

        Stream<List<Integer>> stream = Stream.of(list1, list2);

        // Using flatmap
        stream
            .flatMap(list -> list.stream())
            .forEach(System.out::println);

    }
}
