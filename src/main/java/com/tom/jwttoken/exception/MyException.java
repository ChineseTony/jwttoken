package com.tom.jwttoken.exception;

import com.tom.jwttoken.entity.Result;
import com.tom.jwttoken.myenum.ResultCodeEnum;


public class MyException extends Exception {

    private Result result;

    public MyException(Result result) {
        this.result = result;
    }

    public MyException(ResultCodeEnum resultCodeEnum) {
        result = new Result(resultCodeEnum);

    }

    public Result getResult() {
        return result;
    }
}
