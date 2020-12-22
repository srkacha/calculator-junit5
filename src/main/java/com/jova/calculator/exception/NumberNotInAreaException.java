package com.jova.calculator.exception;

public class NumberNotInAreaException extends Exception{
    public  NumberNotInAreaException(){
        super("Current value has to be between 0 and 10!");
    }
}
