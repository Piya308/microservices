package com.priyanka.cards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException   extends RuntimeException{

    public CardAlreadyExistsException (String msg){
        super(msg);
    }
}
