public class StringFormattingDemo {
    public static void main(String[] args) {
//        funcA();
//        funcB();
        funcC();
    }

    static void funcA(){
        int a = 13, b = 12, c = 11, d = 5;

        System.out.printf("My nephews are %d, %d, %d and %d years old", a, b, c, d);  // My nephews are 13, 12, 11 and 5 years old
    }

    static void funcB(){
        double n = 10, d = 3;
        System.out.println(n/d);  // 3.3333333333333335
        String output = String.format("%.1f", n/d);
        System.out.println(output); // 3.3
    }

    static void funcC(){ // flag radix
        int a = 32;
        System.out.printf("%#o\n", a); // 040
        System.out.printf("%#x", a);   // 0x20
    }

    static void funcD(){

    }
}
