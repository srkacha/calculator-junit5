package com.jova.calculator.exception;

/**
 * <h1>NotSupportedOperationException</h1>
 * Exception that is thrown when the operation is not supported in one of the
 * calculator classes.
 *
 * @author Srdjan Jovic
 * @version 1.0
 * @since 2020-12-23
 */
public class NotSupportedOperationException extends Exception{
    public NotSupportedOperationException(){
        super("Operation is not supported!");
    }
}
