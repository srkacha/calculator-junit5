package com.jova.calculator;

import com.jova.calculator.exception.NotSupportedOperationException;
import com.jova.calculator.exception.NumberNotInAreaException;

public class CalculatorAdvanced extends Calculator{

    // Private helper methods
    private boolean isCharNumerical(char value){
        return ( value >= 48 ) && ( value <= 57 );
    }

    private boolean isActionSupported(char action){
        return (true == isCharNumerical(action)) || ('!' == action);
    }

    private Double calculateIntegerPartFactorial(Double value){
        Integer result = value.intValue();
        Integer multiplier = new Integer(result - 1);

        while (1 <= multiplier){
            result = result * multiplier;
            multiplier--;
        }

        return result.doubleValue();
    }

    // Public methods
    public void calculateAdvanced(char action) throws  NotSupportedOperationException, NumberNotInAreaException{
        Double currentValue = getCurrentValue();
        Double result;

        // Validating input parameters
        if (false == isActionSupported(action)){
            throw new NotSupportedOperationException();
        }

        if ('!' == action){
            if ((currentValue < 0) || currentValue > 10){
                throw new NumberNotInAreaException();
            }
            result = calculateIntegerPartFactorial(currentValue);
            setCurrentValue(result);
        }
    }
}
