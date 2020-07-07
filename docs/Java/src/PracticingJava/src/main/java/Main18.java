import java.util.function.Function;

public class Main18 {
    public static void main(String[] args) {
        Function<String, Integer> map = String::length;
        System.out.println(map.apply("hello world"));
    }
}
