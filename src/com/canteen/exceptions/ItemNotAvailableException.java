package com.canteen.exceptions;


public class ItemNotAvailableException extends RuntimeException {

    ItemNotAvailableException(){
        super("Dear Customer, The requested item is not available.");
    }

    ItemNotAvailableException(String message)
    {
        super(message);
    }

}
