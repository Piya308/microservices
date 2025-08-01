package com.priyanka.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException  extends RuntimeException{

    public LoanAlreadyExistsException(String msg){
        super(msg);
    }
}
