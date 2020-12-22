package com.jova.calculator;

import com.jova.calculator.exception.DivisionByZeroException;
import com.jova.calculator.exception.NotSupportedOperationException;

public class Calculator {
    // Class attributes
    private Double currentValue;
    private static final char[] VALID_OPERATORS = new char[]{
        '+', '-', '*', '/'
    };

    // Constructor
    public Calculator(){
        currentValue = 0.0;
    }

    // Getters and setters
    public Double getCurrentValue(){
        return this.currentValue;
    }

    public void setCurrentValue(Double value){
        this.currentValue = value;
    }

    // Private helper methods
    private boolean isOperationSupported(char operator){
        boolean supported = false;

        for (char c: VALID_OPERATORS){
            if ( c == operator ){
                supported = true;
                break;
            }
        }

        return supported;
    }

    private boolean isDivisionByZero(Double value, char operator){
        return ( 0.0 == value ) && ( '/' == operator );
    }

    // Public methods
    public void calculate(Double value, char operator) throws  NotSupportedOperationException, DivisionByZeroException {
        // Validating input parameters
        if ( false == isOperationSupported((operator)) ){
            throw new NotSupportedOperationException(operator);
        } else if ( true == isDivisionByZero(value, operator) ){
            throw new DivisionByZeroException();
        }

        switch (operator){
            case '+':
                currentValue = currentValue + value;
                break;
            case '-':
                currentValue = currentValue - value;
                break;
            case '*':
                currentValue = currentValue * value;
                break;
            case '/':
                currentValue = currentValue / value;
                break;
        }
    }
}
