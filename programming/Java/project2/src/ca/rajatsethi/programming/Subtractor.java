package ca.rajatsethi.programming;

/**
 * Created by 182362434 on 2019/01/29.
 */
public class Subtractor extends CalculateBase{
    Subtractor(){}
    Subtractor(double leftVal, double rightVal){super(leftVal, rightVal);}

    public void calculate(){
        setResult(getLeftVal() - getRightVal());
    }
}
