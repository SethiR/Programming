/*
Another more complicated example of Function
 */

import java.util.function.Function;

public class Main19 {
    public static void main(String[] args) {

        Function<String, String> replace = (str) -> str.replace(":","=");
        Function<String, String> addBraces = (str) -> "{" + str + "}";

        String result = replace.andThen(addBraces).apply("key:value");

        System.out.println(result);

    }
}
