package com.jova.calculator;

import com.jova.calculator.exception.DivisionByZeroException;
import com.jova.calculator.exception.NotSupportedOperationException;

/**
 * <h1>Calculator</h1>
 * Calculator class implements an interface that enables the user
 * to calculate basic math expressions using four arithmetic operators:
 * addition, subtraction, division and multiplication.
 *
 * @author Srdjan Jovic
 * @version 1.0
 * @since 2020-12-23
 */
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
    /**
     * This method is used for retrieving the current calculator state value.
     *
     * @return Double Returns current calculator state value.
     */
    public Double getCurrentValue(){
        return this.currentValue;
    }

    /**
     * This method is used for setting the current calculator state value.
     *
     * @param value This is the new calculator state value.
     */
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
    /**
     * This method is used for executing the four available arithmetic operations
     * on the current calculator state value.
     *
     * @param value This is the second operand for the requested operation, where the first operand is the
     * value of current calculator state. Can not be zero if division is requested.
     *
     * @param operator This is the requested operation. Valid values for this parameter are [+, -, *, /].
     * Any other value will result in an exception.
     *
     * @throws NotSupportedOperationException Thrown when the provided operator is not among the supported
     * operator list.
     *
     * @throws DivisionByZeroException Thrown when division by zero is detected.
     */
    public void calculate(Double value, char operator) throws  NotSupportedOperationException, DivisionByZeroException {
        // Validating input parameters
        if ( false == isOperationSupported((operator)) ){
            throw new NotSupportedOperationException();
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
