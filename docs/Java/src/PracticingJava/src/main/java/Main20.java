import java.util.function.Predicate;

public class Main20 {
    public static void main(String[] args) {

        Predicate<String> isLongerThan5 = (str) -> str.length() > 5;
        Boolean result = isLongerThan5.test("Hello World");
        System.out.println(result);
    }
}
