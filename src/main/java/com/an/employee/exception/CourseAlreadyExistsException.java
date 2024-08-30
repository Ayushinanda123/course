package com.an.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class CourseAlreadyExistsException extends RuntimeException{
    public CourseAlreadyExistsException(String message){
        super(message);
    }
}
