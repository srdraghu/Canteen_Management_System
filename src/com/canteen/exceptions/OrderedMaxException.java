package com.canteen.exceptions;

public class OrderedMaxException extends RuntimeException{

    OrderedMaxException()
    {
        super("Dear Customers, You have ordered out of max");
    }

    OrderedMaxException(String message)
    {
        super(message);
    }
}