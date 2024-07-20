package com.ganesh.productservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IDNotFoundException extends Exception{
    public IDNotFoundException(String msg){
        super(msg);
    }
}
