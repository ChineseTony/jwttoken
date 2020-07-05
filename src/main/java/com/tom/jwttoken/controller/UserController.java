package com.tom.jwttoken.controller;


import com.tom.jwttoken.annotation.NeedToken;
import com.tom.jwttoken.entity.Result;
import com.tom.jwttoken.entity.TokenEntity;
import com.tom.jwttoken.entity.User;
import com.tom.jwttoken.myenum.ResultCodeEnum;
import com.tom.jwttoken.service.UserService;
import com.tom.jwttoken.util.JwtTokenUtils;
import com.tom.jwttoken.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;



    @PostMapping("/getToken")
    @ResponseBody
    public Result getToken(@RequestBody  User user){
        Result result = new Result();
        boolean flag = userService.login(user);
        if (flag){
            TokenEntity token = jwtTokenUtils.makeToken(user);
            result.setCode(ResultCodeEnum.LOGIN_SUCCESS.getCode());
            result.setMessage(ResultCodeEnum.LOGIN_SUCCESS.getMsg());
            result.setData(token);
        }else {
            result.setCode(ResultCodeEnum.LOGIN_ERROR.getCode());
            result.setMessage(ResultCodeEnum.LOGIN_ERROR.getMsg());
        }
        return result;
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    @NeedToken
    public Result getUserInfo(UserVo userVo){
        Result result = new Result(ResultCodeEnum.LOGIN_SUCCESS);
        result.setData(userVo);
        return result;
    }
}
