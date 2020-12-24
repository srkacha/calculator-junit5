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

public class CalculatorAdvancedTest {

    private CalculatorAdvanced calculatorAdvanced = new CalculatorAdvanced();

    @BeforeEach
    public void beforeEach(){
        calculatorAdvanced = new CalculatorAdvanced();
    }

    // Parametrization stream methods
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
    @Test
    public void testCalculatorAdvanced(){
        assertThat(calculatorAdvanced, notNullValue(CalculatorAdvanced.class));
        assertThat(calculatorAdvanced.getCurrentValue(), is(Double.valueOf(0.0)));
    }

    @ParameterizedTest
    @MethodSource("factorialParameters")
    public void testFactorialParameterized(char operator, Double currentValue, Double result)
            throws NotSupportedOperationException, NumberNotInAreaException {

        calculatorAdvanced.setCurrentValue(currentValue);
        calculatorAdvanced.calculateAdvanced(operator);
        assertThat(calculatorAdvanced.getCurrentValue(), is(result));
    }

    @ParameterizedTest
    @MethodSource("exponentiationParameters")
    public void testExponentiationParameterized(char operator, Double currentValue, Double result)
            throws NotSupportedOperationException, NumberNotInAreaException {

        calculatorAdvanced.setCurrentValue(currentValue);
        calculatorAdvanced.calculateAdvanced(operator);
        assertThat(calculatorAdvanced.getCurrentValue(), is(result));
    }

    @Test
    public void testFactorialInvalidCases(){
        char operator = '!';
        calculatorAdvanced.setCurrentValue(-1.0);
        assertThrows(NumberNotInAreaException.class, ()-> calculatorAdvanced.calculateAdvanced(operator));
        calculatorAdvanced.setCurrentValue(11.0);
        assertThrows(NumberNotInAreaException.class, ()-> calculatorAdvanced.calculateAdvanced(operator));
    }

    @Test
    public void testCalculateAdvancedInvalidCases(){
        char operator = 'A';
        assertThrows(NotSupportedOperationException.class, ()-> calculatorAdvanced.calculateAdvanced(operator));
    }

    @ParameterizedTest
    @MethodSource("armstrongNumberParameters")
    public void testArmstrongNumberParameterized(char operator, Double currentValue, Boolean result)
            throws NotSupportedOperationException, NumberNotInAreaException {

        calculatorAdvanced.setCurrentValue(currentValue);
        assertThat(calculatorAdvanced.hasCharacteristic(operator), is(result));
    }

    @ParameterizedTest
    @MethodSource("perfectNumberParameters")
    public void testPerfectNumberParameterized(char operator, Double currentValue, Boolean result)
            throws NotSupportedOperationException, NumberNotInAreaException {

        calculatorAdvanced.setCurrentValue(currentValue);
        assertThat(calculatorAdvanced.hasCharacteristic(operator), is(result));
    }

    @Test
    public void testHasCharacteristicInvalidCases(){
        char operator = 'X';
        assertThrows(NotSupportedOperationException.class, () -> calculatorAdvanced.hasCharacteristic(operator));
        calculatorAdvanced.setCurrentValue(0.0);
        assertThrows(NumberNotInAreaException.class, () -> calculatorAdvanced.hasCharacteristic('A'));
    }

}
