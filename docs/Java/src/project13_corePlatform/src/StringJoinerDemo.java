import java.util.StringJoiner;

public class StringJoinerDemo {
    public static void main(String[] args) {

        StringJoiner sj = new StringJoiner(", ");  // specify the delimiter

        sj.add("alpha");
        sj.add("beta");
        sj.add("gama");

        System.out.println(sj.toString());

        sj.add("abc").add("def");  // chaining methods --> return type of add is StringJoiner
        System.out.println(sj.toString()); // output --> alpha, beta, gama, abc, def

        StringJoiner sj1 = new StringJoiner("], [", "[", "]");
        sj1.add("alpha");
        sj1.add("beta");
        System.out.println(sj1.toString()); // [alpha], [beta]
    }
}
