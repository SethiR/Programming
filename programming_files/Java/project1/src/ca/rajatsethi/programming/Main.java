package ca.rajatsethi.programming;

public class Main {
    public static void main(String[] args) {

        MathEquation[] equations = new MathEquation[4];

        for (MathEquation equation : equations) {
            equation = new MathEquation(2.0, 2.1, 'a');
            equation.execute();
            System.out.println(equation.getResult());
        }
    }


//    public static MathEquation create(double leftVal, double rightVal, char opCode){
//        MathEquation equation = new MathEquation();
//        equation.setLeftVal(leftVal);
//        equation.setRightVal(rightVal);
//        equation.setOpCode(opCode);
//        return equation;
//    }

}