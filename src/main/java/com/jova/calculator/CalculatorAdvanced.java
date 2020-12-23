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

    private boolean isCharacteristicValueParamValid(char value){
        return ( 'A' == value ) || ( 'P' == value );
    }

    private Integer calculateFactorial(Integer value){
        Integer result = value;
        Integer multiplier = result - 1;

        while (1 <= multiplier){
            result = result * multiplier;
            multiplier--;
        }

        return result;
    }

    private Integer calculateExponentiation(Integer value, Integer exponent){
        Integer result = value;

        // Special case where both values are 0
        if (0 == result && 0 == exponent) return 1;

        while (1 < exponent){
            result = result * value;
            exponent--;
        }

        return result;
    }

    private Boolean isArmstrongNumber(Integer value){
        Integer sum = 0;
        Integer numOfDigits = value.toString().length();
        Integer tempValue = value;

        while(0 != tempValue){
            Integer digit = tempValue % 10;
            sum += calculateExponentiation(digit, numOfDigits);
            tempValue /= 10;
        }

        return value.equals(sum);
    }

    private Boolean isPerfectNumber(Integer value){
        Integer sum = 0;

        for (Integer divider = 1; divider <= (value / 2); divider++){
            if (value % divider == 0){
                sum += divider;
            }
        }

        return  value.equals(sum);
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
            result = calculateFactorial(currentValue.intValue()).doubleValue();
        }else{ // When the action is a digit
            Integer exponent = (int)action - 48;
            result = calculateExponentiation(currentValue.intValue(), exponent).doubleValue();
        }

        setCurrentValue(result);
    }

    public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException{
        Boolean result = false;
        Double currentValue = getCurrentValue();
        Integer integerPart = currentValue.intValue();

        // Validating input parameters
        if (false == isCharacteristicValueParamValid(value)){
            throw new NotSupportedOperationException();
        }

        if (integerPart < 1){
            throw new NumberNotInAreaException();
        }

        switch (value){
            case 'A':
                result = isArmstrongNumber(integerPart);
                break;
            case 'P':
                result = isPerfectNumber(integerPart);
                break;
        }

        return result;
    }
}
