import java.util.function.Predicate;

public class Main21 {
    public static void main(String[] args) {

        Predicate<String> hasLeftBrace = (str) -> str.startsWith("{");
        Predicate<String> hasRightBrace = (str) -> str.endsWith("}");

        System.out.println(hasLeftBrace.and(hasRightBrace).test("{hello world}"));
        System.out.println(hasLeftBrace.and(hasRightBrace).test("{hello worldf"));

    }
}
