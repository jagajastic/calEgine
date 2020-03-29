package com.company;

public class Substracter extends CalculateBase {
    public Substracter() {}
    public Substracter(double leftVal, double rightVal){
        super(leftVal, rightVal);
    }

    @Override
    public void calculate() {
        double value = getLeftVal() - getRightVal();
        setResult(value);
    }
}
