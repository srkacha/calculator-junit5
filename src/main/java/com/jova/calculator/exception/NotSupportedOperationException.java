package com.jova.calculator.exception;

public class NotSupportedOperationException extends Exception{
    public NotSupportedOperationException(char operation){
        super(operation + " is not a supported operation!");
    }
}
