package com.tom.jwttoken.myenum;

public enum ResultCodeEnum {

    LOGIN_ERROR("0001","登录失败"),
    LOGIN_SUCCESS("0002","登录成功"),
    NO_TOKEN("0003","需要token"),
    TOKEN_NO_USERID("0004","token没有userID"),
    TOKEN_ERROR("0005","验证token失败"),
    TOKEN_SUCCESS("0006","验证token成功"),
    SYSTEM_ERROR("9999","系统异常");


    private String code;

    private String msg;


    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
