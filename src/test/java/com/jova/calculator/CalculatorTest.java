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

public class CalculatorTest {

    private Calculator calculator = new Calculator();
    private  final static Double DELTA = 0.00000000000001;

    @BeforeEach
    public void beforeEach(){
        calculator = new Calculator();
    }

    // Parametrization stream methods
    private static Stream<Arguments> parametersForAddition(){
        char operator = '+';
        return Stream.of(
                Arguments.of(operator, 0.0, 0.0, 0.0),
                Arguments.of(operator, 5.0, 5.0, 10.0),
                Arguments.of(operator, -5.0, -5.0, -10.0),
                Arguments.of(operator, 10.5, -10.5, 0.0),
                Arguments.of(operator, -50.0, 100.0, 50.0)
        );
    }

    private static Stream<Arguments> parametersForSubtraction(){
        char operator = '-';
        return Stream.of(
            Arguments.of(operator, 0.0, 0.0, 0.0),
            Arguments.of(operator, 0.0, 5.0, -5.0),
            Arguments.of(operator, 5.0, 5.0, 0.0),
            Arguments.of(operator, 9.0, 8.0, 1.0),
            Arguments.of(operator, -10.0, 100.0, -110.0)
        );
    }

    private static Stream<Arguments> parametersForMultiplication(){
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

    private static Stream<Arguments> parametersForDivision(){
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
    @Test
    public void testCalculator(){
        assertThat(calculator, notNullValue(Calculator.class));
        assertThat(calculator.getCurrentValue(), is(Double.valueOf(0.0)));
    }

    @ParameterizedTest
    @MethodSource("parametersForAddition")
    public void testAdditionParameterized(char operator, Double currentValue, Double value, Double result)
                                            throws NotSupportedOperationException, DivisionByZeroException {

        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(result));
    }

    @ParameterizedTest
    @MethodSource("parametersForSubtraction")
    public void testSubtractionParameterized(char operator, Double currentValue, Double value, Double result)
            throws NotSupportedOperationException, DivisionByZeroException {

        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(result));
    }

    @ParameterizedTest
    @MethodSource("parametersForMultiplication")
    public void testMultiplicationParameterized(char operator, Double currentValue, Double value, Double result)
            throws NotSupportedOperationException, DivisionByZeroException {

        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(closeTo(result, DELTA)));
    }

    @ParameterizedTest
    @MethodSource("parametersForDivision")
    public void testDivisionParameterized(char operator, Double currentValue, Double value, Double result)
            throws NotSupportedOperationException, DivisionByZeroException {

        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operator);
        assertThat(calculator.getCurrentValue(), is(closeTo(result, DELTA)));
    }

    @Test
    public void testInvalidOperatorParameter(){
        char operator = '%';
        Double value = 10.0;
        assertThrows(NotSupportedOperationException.class, () -> calculator.calculate(value, operator));
    }

    @Test
    public void testDivisionByZero(){
        char operator = '/';
        Double value = 0.0;
        assertThrows(DivisionByZeroException.class, () -> calculator.calculate(value, operator));
    }

}
