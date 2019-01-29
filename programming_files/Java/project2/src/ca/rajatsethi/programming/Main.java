package ca.rajatsethi.programming;

public class Main {
    public static void main(String[] args) {

        Adder add = new Adder(10.5,20.1);
        add.calculate();
        System.out.println(add.getResult());

        CalculateBase[] calculators = {
                new Adder(10.1, 20.5),
                new Subtractor(1.2, 1.1)
        };

        for (CalculateBase calculator : calculators){
            calculator.calculate();
            System.out.println(calculator.getResult());
        }

    }
}