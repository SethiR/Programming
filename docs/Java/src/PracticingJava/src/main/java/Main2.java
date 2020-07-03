/*
Working with interface : Program to interface and not implementation.
 */

interface A{
    void a();
}

interface B{
    void b();
}

class Derived implements A, B{
    public void a(){
        System.out.println("In A");
    }
    public void b(){
        System.out.println("In B");
    }
    public void c(){
        System.out.println("In C");
    }
}

public class Main2 {
    public static void main(String[] args) {
        Object a = new Derived();
//        a.a();
//        a.b();
//        a.c();
    }
}