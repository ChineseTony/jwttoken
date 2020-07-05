package com.tom.jwttoken.aop;

import com.tom.jwttoken.entity.Result;
import com.tom.jwttoken.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MyException.class)
    public Result handleCustomException(MyException myException) {
        Result result = myException.getResult();
        return result;
    }
}
