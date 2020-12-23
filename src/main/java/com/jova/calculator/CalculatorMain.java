package com.jova.calculator;

public class CalculatorMain {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        CalculatorAdvanced calculatorAdvanced = new CalculatorAdvanced();

        // Trying out calculator and calculator advanced
        try{
            calculator.calculate(10.0, '+');
            calculator.calculate((12.5), '-');
            calculator.calculate(2.0, '/');
            calculator.calculate(5.0, '*');

            System.out.println("Calculator result: " + calculator.getCurrentValue());

            calculatorAdvanced.setCurrentValue(28.5);

            System.out.println("Calculator advanced result: " + calculatorAdvanced.hasCharacteristic('P'));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
