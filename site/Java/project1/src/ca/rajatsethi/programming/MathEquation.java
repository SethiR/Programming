package ca.rajatsethi.programming;

public class MathEquation {
    private double leftVal;
    private double rightVal;
    private double result;
    private char opCode;


    //  ==== Constructors ====

    public MathEquation(){}

    public MathEquation(char opCode) {
        this.opCode = opCode;
    }

    public MathEquation(double leftVal, double rightVal, char opCode) {
        this(opCode);
        this.leftVal = leftVal;
        this.rightVal = rightVal;
    }


    //  ==== Getters and Setters ====
    public double getLeftVal() {
        return leftVal;
    }

    public void setLeftVal(double leftVal) {
        this.leftVal = leftVal;
    }

    public double getRightVal() {
        return rightVal;
    }

    public void setRightVal(double rightVal) {
        this.rightVal = rightVal;
    }

    public double getResult() {
        return result;
    }

    public char getOpCode() {
        return opCode;
    }

    public void setOpCode(char opCode) {
        this.opCode = opCode;
    }

    //  ==== Methods ====
    public void execute(){
        switch (opCode){
            case 'a':
                result = leftVal + rightVal;
                break;
            case 'd':
                result = leftVal / rightVal;
                break;
            case 'm':
                result = leftVal - rightVal;
                break;
            case 's':
                result = leftVal - rightVal;
                break;
            default:
                result = 0;
                break;
        }
    }

    // Overloading the execute method written above so that we can accept values when executing
    public void execute(double leftVal, double rightVal){
        this.leftVal = leftVal;
        this.rightVal = rightVal;
        execute();
    }

    // Overloading for integers only result
    public void execute(int leftVal, int rightVal){
        this.leftVal = leftVal;
        this.rightVal = rightVal;
        execute();
        this.result = (int) this.result;
    }
}
