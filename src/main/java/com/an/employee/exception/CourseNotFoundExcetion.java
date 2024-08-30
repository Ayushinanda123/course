package com.an.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFoundExcetion extends RuntimeException{
    public CourseNotFoundExcetion(String message){
        super(message);
    }
}
