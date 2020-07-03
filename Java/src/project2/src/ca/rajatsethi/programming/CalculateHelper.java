package ca.rajatsethi.programming;

/**
 * Created by 182362434 on 2019/01/29.
 */
public class CalculateHelper {
    MathCommand command;
    double leftVal;
    double rightVal;
    double result;

    public void process(String statement){
        String[] parts = statement.split(" ");
        String commandString = parts[0];
        leftVal = Double.parseDouble(parts[1]);
        rightVal = Double.parseDouble(parts[2]);
        setCommandFromString(commandString);
    }

    private void setCommandFromString(String commandString){
        if (commandString.equalsIgnoreCase(MathCommand.Add.toString())){
            command = MathCommand.Add;
        }
        else if(commandString.equalsIgnoreCase(MathCommand.Subtract.toString())){
            command = MathCommand.Subtract;
        }
    }

}
