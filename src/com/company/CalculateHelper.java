package com.company;

public class CalculateHelper {
    private static final char ADD_SYMBOL = '+';
    private static final char SUBTRACT_SYMBOL = '-';
    private static final char MULTIPLY_SYMBOL = '*';
    private static final char DIVIDE_SYMBOL = '/';

    MathCommand command;
    double leftVal;
    double rightVal;
    double result;

    public  void process (String statement) throws InvalidStatementException {
        String[] parts = statement.split(" ");

        if(parts.length != 3)
            throw  new InvalidStatementException("Incorrect number of fields", statement);

        String commandString = parts[0]; //add

        try{
            leftVal = Double.parseDouble(parts[1]); // 1.0
            rightVal =  Double.parseDouble(parts[2]); // 2.0

        }catch (NumberFormatException e){
            throw new InvalidStatementException("Non-number data", statement, e);
        }

        setCommandFromString(commandString);
        if (command == null)
            throw new InvalidStatementException("Invalid command", statement);

        CalculateBase calculator = null;
        switch (command){
            case Add:
                calculator = new Adder(leftVal, rightVal);
                break;
            case  Subtract:
                calculator = new Substracter(leftVal, rightVal);
                break;

            case Multiply:
                calculator = new Multiplier(leftVal, rightVal);
                break;
            case Divide:
                calculator = new Divider(leftVal, rightVal);
                break;
        }

        calculator.calculate();
        result = calculator.getResult();
    }

    private void setCommandFromString(String commandString){
        // add -> MathCommand.Add

        if(commandString.equalsIgnoreCase(MathCommand.Add.toString()))
            command = MathCommand.Add;
        else if(commandString.equalsIgnoreCase(MathCommand.Subtract.toString()))
            command = MathCommand.Subtract;
        else if(commandString.equalsIgnoreCase(MathCommand.Multiply.toString()))
            command = MathCommand.Multiply;
        else if(commandString.equalsIgnoreCase(MathCommand.Divide.toString()))
            command = MathCommand.Divide;
    }

    @Override
    public String toString () {
        // 1.0 + 2.0 = 3.0
        char symbol = ' ';
        switch (command){
            case Add:
                symbol = ADD_SYMBOL;
                break;
            case Subtract:
                symbol = SUBTRACT_SYMBOL;
                break;
            case Multiply:
                symbol = MULTIPLY_SYMBOL;
                break;
            case Divide:
                symbol = DIVIDE_SYMBOL;
                break;
        }

        StringBuffer sb = new StringBuffer(20);
        sb.append(leftVal);
        sb.append(' ');
        sb.append(symbol);
        sb.append(' ');
        sb.append(rightVal);
        sb.append(" = ");
        sb.append(result);

        return  sb.toString();
    }

}
