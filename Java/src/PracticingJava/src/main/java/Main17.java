import java.util.function.Supplier;

public class Main17 {
    public static void main(String[] args) {

        // Check javadoc for Supplier Interface.
        Supplier<Double> getRandom = Math::random;

        System.out.println(getRandom.get());

    }
}
