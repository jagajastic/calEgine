package com.company.myapp;

import com.company.Adder;
import com.company.CalculateBase;
import com.company.CalculateHelper;
import com.company.Divider;
import com.company.DynamicHelper;
import com.company.InvalidStatementException;
import com.company.MathEquation;
import com.company.MathProcessing;
import com.company.Multiplier;
import com.company.PowerOf;
import com.company.Substracter;

public class Main {

    public static void main(String[] args) {
//        useCalculateHelper();
//        useMatheEquation();
//        useCalculatorBase();

        String[] statements = {
                "add 25.0 92.0", // 25.0 + 92.0 = 117.0
                "power 5.0 2.0", // 5.0 ^ 2.0 = 25.0
        };

        DynamicHelper helper = new DynamicHelper(new MathProcessing[] {
                new Adder(),
                new PowerOf()
        });

        for(String statement: statements){
            String output = helper.process(statement);
            System.out.println(output);
        }
    }

    static  void useCalculateHelper() {

        String[] statements = {
                "divide 1.0 ",
                "divide xx 25.0",
                "divide 100.0 50.0",
                "add 25.0 92.0",
                "substract 225.0 17.0",
                "mutiply 11.0 3.0",
        };

        CalculateHelper helper;
        helper = new CalculateHelper();
        for (String statement: statements){
            try{
                helper.process(statement);
                System.out.println(helper);
            }catch (InvalidStatementException e){
                System.out.println(e.getMessage());
                if (e.getCause() != null)
                    System.out.println(" Original exception: "+ e.getCause().getMessage());
            }

        }
    }
    static void useMatheEquation() {

        MathEquation[] equations = new MathEquation[4];
        equations[0] = new MathEquation( 'd', 100.0d, 50.0);
        equations[1] =  new MathEquation('a' ,25.0d, 92.0d);
        equations[2] =  new MathEquation('s', 225.0d, 17.0d);
        equations[3] =  new MathEquation('m', 11.0d, 3.0d );

        for (MathEquation equation: equations) {
            equation.execute();
            System.out.print("Result = ");
            System.out.println(equation.getResult());
        }

        System.out.println();
        System.out.println("Using Overloading");
        System.out.println();

        double leftDouble = 9.0d;
        double rightDouble = 4.0d;
        int leftInt = 9;
        int rightInt = 4;

        MathEquation equationOverload = new MathEquation('d');

        equationOverload.execute(leftDouble, rightDouble);
        System.out.print("result=");
        System.out.println(equationOverload.getResult());

        equationOverload.execute(leftInt, rightInt);
        System.out.print("result=");
        System.out.println(equationOverload.getResult());


    }
    static void useCalculatorBase() {

        System.out.println();
        System.out.println("Using Inheritance");
        System.out.println();

        CalculateBase[] calculators = {
                new Divider(100.0d, 50.0d),
                new Adder(25.0d, 92.0d),
                new Substracter(225.0d, 17.0d),
                new Multiplier(11.0d, 3.0d)
        };

        for(CalculateBase calculator: calculators) {
            calculator.calculate();
            System.out.print("result=");
            System.out.println(calculator.getResult());
        }
    }
}
