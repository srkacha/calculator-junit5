package com.jova.calculator.exception;

public class NotSupportedOperationException extends Exception{
    public NotSupportedOperationException(){
        super("Operation is not supported!");
    }
}
