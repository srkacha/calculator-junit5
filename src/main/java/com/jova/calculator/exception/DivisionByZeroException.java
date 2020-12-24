package com.jova.calculator.exception;

/**
 * <h1>DivisionByZeroException</h1>
 * Exception that is thrown when division with zero is detected in one of the
 * Calculator classes.
 *
 * @author Srdjan Jovic
 * @version 1.0
 * @since 2020-12-23
 */
public class DivisionByZeroException extends Exception{
    public DivisionByZeroException(){
        super("Division by zero is not allowed!");
    }
}
