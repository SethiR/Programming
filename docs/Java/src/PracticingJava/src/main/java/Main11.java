/*
Java is always pass by value --> but this may not be what you think.
Java is passing the value (which is reference to a object).

 */

class A1{
    public int a;
}
class B1{
    public int b;
    void changeA(A1 obj){
        obj.a = 10;
    }
}
public class Main11 {
    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.a = 5;
        B1 b1 = new B1();
        b1.changeA(a1);
        System.out.println(a1.a);
    }
}
