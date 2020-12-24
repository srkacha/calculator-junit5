package com.jova.calculator.exception;

/**
 * <h1>NumberNotInAreaException</h1>
 * Exception that is thrown when some numerical parameters are not in requested range
 * in one of the Calculator classes.
 *
 * @author Srdjan Jovic
 * @version 1.0
 * @since 2020-12-23
 */
public class NumberNotInAreaException extends Exception{
    public  NumberNotInAreaException(){
        super("Current value has to be between 0 and 10!");
    }
}
