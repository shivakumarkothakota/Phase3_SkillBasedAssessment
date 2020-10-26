package com.rest.its.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException {
    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(List<String> msgs) {
        this(String.join(";", msgs));
    }

}
