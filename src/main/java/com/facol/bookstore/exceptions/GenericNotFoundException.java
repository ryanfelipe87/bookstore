package com.facol.bookstore.exceptions;

public class GenericNotFoundException extends RuntimeException{

    public GenericNotFoundException(String message){
        super(message);
    }
}
