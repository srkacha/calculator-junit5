package com.jova.calculator;

import com.jova.calculator.exception.NotSupportedOperationException;
import com.jova.calculator.exception.NumberNotInAreaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <h1>CalculatorAdvancedTest</h1>
 * CalculatorAdvancedTest is a testing class that fully covers all of the code
 * implemented in CalculatorAdvanced class. Tests are parametrized and are using
 * Hamcrest matchers.
 *
 * @author Srdjan Jovic
 * @version 1.0
 * @since 2020-12-23
 */
public class CalculatorAdvancedTest {

    /**
     * CalculatorAdvanced object that is referenced in all test methods.
     */
    private CalculatorAdvanced calculatorAdvanced = new CalculatorAdvanced();

    /**
     * Method that is called before each test is run.
     */
    @BeforeEach
    public void beforeEach(){
        calculatorAdvanced = new CalculatorAdvanced();
    }

    // Parametrization stream methods
    /**
     * Generates the stream of argument objects for factorial calculation testing.
     *
     * @return Stream of Arguments objects.
     */
    private static Stream factorialParameters(){
        char operator = '!';
        return Stream.of(
                Arguments.of(operator, 1.0, 1.0),
                Arguments.of(operator, 3.0, 6.0),
                Arguments.of(operator, 3.5, 6.0),
                Arguments.of(operator, 5.0, 120.0),
                Arguments.of(operator, 5.99, 120.0)
        );
    }

    /**
     * Generates the stream of argument objects for exponentiation calculation testing.
     *
     * @return Stream of Arguments objects.
     */
    private static Stream exponentiationParameters(){
        return Stream.of(
                Arguments.of('0', 0.0, 1.0),
                Arguments.of('5', 0.0, 0.0),
                Arguments.of('5', 2.0, 32.0),
                Arguments.of('2', -5.0, 25.0),
                Arguments.of('2', -5.5, 25.0),
                Arguments.of('3', -3.0, -27.0)
        );
    }

    /**
     * Generates the stream of argument objects for Armstrong number property testing.
     *
     * @return Stream of Arguments objects.
     */
    private static Stream armstrongNumberParameters(){
        char operator = 'A';
        return Stream.of(
                Arguments.of(operator, 1.0, true),
                Arguments.of(operator, 1.5, true),
                Arguments.of(operator, 10.0, false),
                Arguments.of(operator, 152.999, false),
                Arguments.of(operator, 153.0, true)
        );
    }

    /**
     * Generates the stream of argument objects for Perfect number property testing.
     *
     * @return Stream of Arguments objects.
     */
    private static Stream perfectNumberParameters(){
        char operator = 'P';
        return Stream.of(
                Arguments.of(operator, 1.0, false),
                Arguments.of(operator, 6.0, true),
                Arguments.of(operator, 6.99, true),
                Arguments.of(operator, 27.99, false),
                Arguments.of(operator, 28.0, true)
        );
    }

    // Test methods
    /**
     * Tests the reference for the CalculatorAdvanced object, also tests the initial value.
     */
    @Test
    public void testCalculatorAdvanced(){
        assertThat(calculatorAdvanced, notNullValue(CalculatorAdvanced.class));
        assertThat(calculatorAdvanced.getCurrentValue(), is(Double.valueOf(0.0)));
    }

    /**
     * Tests the factorial calculation for the calculateAdvanced method, parameterized to consume
     * parameters from factorialParameters method.
     *
     * @param operator Calculation operator.
     * @param currentValue Requested current value for calculator.
     * @param result Expected result for the calculation.
     * @throws NotSupportedOperationException
     * @throws NumberNotInAreaException
     */
    @ParameterizedTest
    @MethodSource("factorialParameters")
    public void testFactorialParameterized(char operator, Double currentValue, Double result)
            throws NotSupportedOperationException, NumberNotInAreaException {

        calculatorAdvanced.setCurrentValue(currentValue);
        calculatorAdvanced.calculateAdvanced(operator);
        assertThat(calculatorAdvanced.getCurrentValue(), is(result));
    }

    /**
     * Tests the exponentiation calculation for the calculateAdvanced method, parameterized to consume
     * parameters from exponentiationParameters method.
     *
     * @param operator Calculation operator.
     * @param currentValue Requested current value for calculator.
     * @param result Expected result for the calculation.
     * @throws NotSupportedOperationException
     * @throws NumberNotInAreaException
     */
    @ParameterizedTest
    @MethodSource("exponentiationParameters")
    public void testExponentiationParameterized(char operator, Double currentValue, Double result)
            throws NotSupportedOperationException, NumberNotInAreaException {

        calculatorAdvanced.setCurrentValue(currentValue);
        calculatorAdvanced.calculateAdvanced(operator);
        assertThat(calculatorAdvanced.getCurrentValue(), is(result));
    }

    /**
     * Tests out the invalid cases for factorial calculation in calculateAdvanced method.
     */
    @Test
    public void testFactorialInvalidCases(){
        char operator = '!';
        calculatorAdvanced.setCurrentValue(-1.0);
        assertThrows(NumberNotInAreaException.class, ()-> calculatorAdvanced.calculateAdvanced(operator));
        calculatorAdvanced.setCurrentValue(11.0);
        assertThrows(NumberNotInAreaException.class, ()-> calculatorAdvanced.calculateAdvanced(operator));
    }

    /**
     * Tests out the invalid operator case for the calculateAdvanced method.
     */
    @Test
    public void testCalculateAdvancedInvalidCases(){
        char operator = 'A';
        assertThrows(NotSupportedOperationException.class, ()-> calculatorAdvanced.calculateAdvanced(operator));
    }

    /**
     * Tests the Armstrong number check for the hasCharacteristic method, parameterized to consume
     * parameters from armstrongNumberParameters method.
     *
     * @param operator Calculation operator.
     * @param currentValue Requested current value for calculator.
     * @param result Expected result for the calculation.
     * @throws NotSupportedOperationException
     * @throws NumberNotInAreaException
     */
    @ParameterizedTest
    @MethodSource("armstrongNumberParameters")
    public void testArmstrongNumberParameterized(char operator, Double currentValue, Boolean result)
            throws NotSupportedOperationException, NumberNotInAreaException {

        calculatorAdvanced.setCurrentValue(currentValue);
        assertThat(calculatorAdvanced.hasCharacteristic(operator), is(result));
    }

    /**
     * Tests the Perfect number check for the hasCharacteristic method, parameterized to consume
     * parameters from perfectNumberParameters method.
     *
     * @param operator Calculation operator.
     * @param currentValue Requested current value for calculator.
     * @param result Expected result for the calculation.
     * @throws NotSupportedOperationException
     * @throws NumberNotInAreaException
     */
    @ParameterizedTest
    @MethodSource("perfectNumberParameters")
    public void testPerfectNumberParameterized(char operator, Double currentValue, Boolean result)
            throws NotSupportedOperationException, NumberNotInAreaException {

        calculatorAdvanced.setCurrentValue(currentValue);
        assertThat(calculatorAdvanced.hasCharacteristic(operator), is(result));
    }

    /**
     * Tests out the invalid cases for the hasCharacteristic method.
     */
    @Test
    public void testHasCharacteristicInvalidCases(){
        char operator = 'X';
        assertThrows(NotSupportedOperationException.class, () -> calculatorAdvanced.hasCharacteristic(operator));
        calculatorAdvanced.setCurrentValue(0.0);
        assertThrows(NumberNotInAreaException.class, () -> calculatorAdvanced.hasCharacteristic('A'));
    }

}
