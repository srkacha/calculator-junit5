package com.jova.calculator;

import com.jova.calculator.exception.NotSupportedOperationException;
import com.jova.calculator.exception.NumberNotInAreaException;

/**
 * <h1>CalculatorAdvanced</h1>
 * CalculatorAdvanced class implements an interface that enables the user
 * to calculate advanced math operations such as integer part factorial and exponentiation.
 * It also enables the user to check if given number has a property of being an Armstrong or Perfect number.
 * For more info about Armstrong and Perfect numbers check the appropriate methods.
 *
 * @author Srdjan Jovic
 * @version 1.0
 * @since 2020-12-23
 */
public class CalculatorAdvanced extends Calculator{

    // Private helper methods
    /**
     * Helper method that checks if the provided char ASCII value is numerical.
     *
     * @param value Value to be checked if it is numerical.
     *
     * @return Returns true if the character is numerical, otherwise returns false.
     */
    private boolean isCharNumerical(char value){
        return ( value >= 48 ) && ( value <= 57 );
    }

    /**
     * Helper method that checks if the action provided to the calculateAdvanced method is supported.
     * Returns true if the action is an numerical character or an '!', returns false otherwise.
     *
     * @param action Parameter to be checked if it is supported.
     *
     * @return Boolean value of true if the action is supported, false if not.
     */
    private boolean isActionSupported(char action){
        return (true == isCharNumerical(action)) || ('!' == action);
    }

    /**
     * Helper method that checks if the hasCharacteristic mathod parameter is valid.
     * Returns true if the parameter equals 'A' (Armstrong) or 'P' (Perfect), otherwise returns false.
     *
     * @param value Value to be checked if it is valid.
     *
     * @return Boolean value of true if the value is valid, false if not.
     */
    private boolean isCharacteristicValueParamValid(char value){
        return ( 'A' == value ) || ( 'P' == value );
    }

    /**
     * Helper method that calculates the factorial value for the input value.
     * Input value has to be previously validated and be in the range of [0, 10].
     *
     * @param value Value for which the factorial is calculated.
     *
     * @return Integer value of the calculated factorial.
     */
    private Integer calculateFactorial(Integer value){
        Integer result = value;
        Integer multiplier = result - 1;

        while (1 <= multiplier){
            result = result * multiplier;
            multiplier--;
        }

        return result;
    }

    /**
     * Helper method that calculates the exponentiation using value as the first operand and exponent
     * as the second operand. If both values are equal to zero, then it returns 1.
     *
     * @param value The first operand of the exponentiation.
     *
     * @param exponent The second operand of the exponentination.
     *
     * @return Integer value of the resulting exponentiation.
     */
    private Integer calculateExponentiation(Integer value, Integer exponent){
        Integer result = 1;

        // Special case where both values are 0
        if (0 == result && 0 == exponent) return 1;

        while (0 < exponent){
            result = result * value;
            exponent--;
        }

        return result;
    }

    /**
     * Helper method that checks if the provided number has the property of being an Armstrong number.
     * An n digit number is an Armstrong number if the sum of his digits to the power of n is equal to that
     * same number. For example 153 is Armstrong number because 1 + 125 + 27 is equal to 153.
     *
     * @param value The number for which the property is checked.
     *
     * @return Boolean value of true if it is an Armstrong number, false if not.
     */
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

    /**
     * Helper method that checks if the provided number has the property of being a Perfect number.
     * A number is perfect when the sum of all his divisors including the number one, but not including the number
     * itself is equal to that same number. For example 28 is a perfect number because 1 + 2 + 4 + 7 + 14 is equal
     * to 28.
     *
     * @param value The number for which the property is checked.
     *
     * @return Boolean value of true if the number is perfect, false if not.
     */
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
    /**
     * Method enables the user to calculate the factorial for the current calculator state value, or to calculate
     * the exponentiation of the current calculator state value to the power of some number in the range of [0, 9].
     * The calculations use the integer part of the current calculator state value and store the result as the new
     * current state value.
     *
     * @param action The parameter that determines which calculation is going to be made. If the value is '!' then
     * factorial is going to be calculated, for this calculation the current state value has to be in range of [0, 10]
     * otherwise an exception will be thrown. If the value is a numerical character in the range of 0 - 9 then
     * the exponentiation will be calculated and stored as the new current state value for the calculator. Other
     * values will cause the method to throw an exception.
     *
     * @throws NotSupportedOperationException Thrown when the operator is not supported, as in not being a '!'
     * character or a numerical character in the range of 0 - 9.
     *
     * @throws NumberNotInAreaException Thrown when the operation is factorial but the current value is not in range
     * of [0, 10].
     */
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

    /**
     * Method enables the user to check if the integer part of the current calculator state value has a property
     * of being either an Armstrong number or a Perfect number. For more info about those properties read the
     * documentation for the appropriate methods that do the property checking. For both calculations the
     * integer part of the current value has to be 1 or greater, otherwise an exception will be thrown.
     *
     * @param value Depending if this value is equal to 'A' or 'P', checking for the Armstrong or Perfect property
     * will be checked. Other values will cause the method to throw an exception.
     *
     * @return Boolean value of true if the current calculator value has the requested property, false if not.
     *
     * @throws NotSupportedOperationException Thrown when the provided operation character is not equal to 'A' or 'P'.
     *
     * @throws NumberNotInAreaException Thrown when the current integer part value of the calculator state is less
     * than 1.
     */
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
