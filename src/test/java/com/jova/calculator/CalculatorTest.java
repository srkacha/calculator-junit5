package com.jova.calculator;

import com.jova.calculator.exception.DivisionByZeroException;
import com.jova.calculator.exception.NotSupportedOperationException;
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
 * <h1>CalculatorTest</h1>
 * CalculatorTest is a testing class that fully covers all of the code
 * implemented in Calculator class. Tests are parametrized and are using
 * Hamcrest matchers.
 *
 * @author Srdjan Jovic
 * @version 1.0
 * @since 2020-12-23
 */
public class CalculatorTest {

    /**
     * Calculator object that is referenced in all test methods.
     */
    private Calculator calculator = new Calculator();

    /**
     * Delta value that is used to allow approximation of double values in testing,
     * set to expect at least the first 14 decimals to match.
     */
    private  final static Double DELTA = 0.00000000000001;

    /**
     * Method that is called before each test is run.
     */
    @BeforeEach
    public void beforeEach(){
        calculator = new Calculator();
    }

    // Parametrization stream methods
    /**
     * Generates the stream of argument objects for addition testing.
     *
     * @return Stream of Arguments objects.
     */
    private static Stream<Arguments> additionParameters(){
        char operator = '+';
        return Stream.of(
                Arguments.of(operator, 0.0, 0.0, 0.0),
                Arguments.of(operator, 5.0, 5.0, 10.0),
                Arguments.of(operator, -5.0, -5.0, -10.0),
                Arguments.of(operator, 10.5, -10.5, 0.0),
                Arguments.of(operator, -50.0, 100.0, 50.0)
        );
    }

    /**
     * Generates the stream of argument objects for subtraction testing.
     *
     * @return Stream of Arguments objects.
     */
    private static Stream<Arguments> subtractionParameters(){
        char operator = '-';
        return Stream.of(
            Arguments.of(operator, 0.0, 0.0, 0.0),
            Arguments.of(operator, 0.0, 5.0, -5.0),
            Arguments.of(operator, 5.0, 5.0, 0.0),
            Arguments.of(operator, 9.0, 8.0, 1.0),
            Arguments.of(operator, -10.0, 100.0, -110.0)
        );
    }

    /**
     * Generates the stream of argument objects for multiplication testing.
     *
     * @return Stream of Arguments objects.
     */
    private static Stream<Arguments> multiplicationParameters(){
        char operator = '*';
        return Stream.of(
                Arguments.of(operator, 0.0, 0.0, 0.0),
                Arguments.of(operator, 0.0, 5.0, 0.0),
                Arguments.of(operator, 1.0, 5.0, 5.0),
                Arguments.of(operator, -1.0, 9.9, -9.9),
                Arguments.of(operator, 10.2, -5.5, -56.1),
                Arguments.of(operator, 0.000123, 0.000123, 0.000000015129)
        );
    }

    /**
     * Generates the stream of argument objects for division testing.
     *
     * @return Stream of Arguments objects.
     */
    private static Stream<Arguments> divisionParameters(){
        char operator = '/';
        return Stream.of(
                Arguments.of(operator, 0.0, 1.0, 0.0),
                Arguments.of(operator, 1.0, 5.0, 0.2),
                Arguments.of(operator, 10.0, -5.0, -2.0),
                Arguments.of(operator, 20.0, 0.5, 40.0),
                Arguments.of(operator, -5.0, -10.0, 0.5)
        );
    }

    // Test methods
    /**
     * Tests the reference for the Calculator object, also tests the initial value.
     */
    @Test
    public void testCalculator(){
        assertThat(calculator, notNullValue(Calculator.class));
        assertThat(calculator.getCurrentValue(), is(Double.valueOf(0.0)));
    }

    /**
     * Tests the addition calculation for the calculate method, parametrized to consume parameters
     * from additionParameters method.
     *
     * @param operator Calculation operator.
     * @param currentValue Calculator requested current value.
     * @param value Second operand for calculate method.
     * @param result Expected result for the calculation.
     * @throws NotSupportedOperationException
     * @throws DivisionByZeroException
     */
    @ParameterizedTest
    @MethodSource("additionParameters")
    public void testAdditionParameterized(char operator, Double currentValue, Double value, Double result)
            throws NotSupportedOperationException, DivisionByZeroException {

        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(result));
    }

    /**
     * Tests the subtraction calculation for the calculate method, parametrized to consume parameters
     * from subtractionParameters method.
     *
     * @param operator Calculation operator.
     * @param currentValue Calculator requested current value.
     * @param value Second operand for calculate method.
     * @param result Expected result for the calculation.
     * @throws NotSupportedOperationException
     * @throws DivisionByZeroException
     */
    @ParameterizedTest
    @MethodSource("subtractionParameters")
    public void testSubtractionParameterized(char operator, Double currentValue, Double value, Double result)
            throws NotSupportedOperationException, DivisionByZeroException {

        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(result));
    }

    /**
     * Tests the multiplication calculation for the calculate method, parametrized to consume parameters
     * from multiplicationParameters method.
     *
     * @param operator Calculation operator.
     * @param currentValue Calculator requested current value.
     * @param value Second operand for calculate method.
     * @param result Expected result for the calculation.
     * @throws NotSupportedOperationException
     * @throws DivisionByZeroException
     */
    @ParameterizedTest
    @MethodSource("multiplicationParameters")
    public void testMultiplicationParameterized(char operator, Double currentValue, Double value, Double result)
            throws NotSupportedOperationException, DivisionByZeroException {

        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(closeTo(result, DELTA)));
    }

    /**
     * Tests the division calculation for the calculate method, parametrized to consume parameters
     * from divisionParameters method.
     *
     * @param operator Calculation operator.
     * @param currentValue Calculator requested current value.
     * @param value Second operand for calculate method.
     * @param result Expected result for the calculation.
     * @throws NotSupportedOperationException
     * @throws DivisionByZeroException
     */
    @ParameterizedTest
    @MethodSource("divisionParameters")
    public void testDivisionParameterized(char operator, Double currentValue, Double value, Double result)
            throws NotSupportedOperationException, DivisionByZeroException {

        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(closeTo(result, DELTA)));
    }

    /**
     * Tests the invalid operator input for calculate method.
     */
    @Test
    public void testInvalidOperatorParameter(){
        char operator = '%';
        Double value = 10.0;
        assertThrows(NotSupportedOperationException.class, () -> calculator.calculate(value, operator));
    }

    /**
     * Tests the division by zero case for calculate method.
     */
    @Test
    public void testDivisionByZero(){
        char operator = '/';
        Double value = 0.0;
        assertThrows(DivisionByZeroException.class, () -> calculator.calculate(value, operator));
    }

}
