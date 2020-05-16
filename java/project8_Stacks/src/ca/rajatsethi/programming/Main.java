package ca.rajatsethi.programming;

public class Main {

    public static void main(String[] args) {
	    Calculator calculator = new Calculator();

        System.out.println(calculator.evaluate("1 + 2"));
        System.out.println(calculator.evaluate("1 + 2 - 11 - 12 - 18 + 109"));
        System.out.println(calculator.evaluate("1 + 6"));
    }
}
