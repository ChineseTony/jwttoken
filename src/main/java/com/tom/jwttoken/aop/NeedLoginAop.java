package com.tom.jwttoken.aop;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.tom.jwttoken.annotation.NeedToken;
import com.tom.jwttoken.entity.User;
import com.tom.jwttoken.exception.MyException;
import com.tom.jwttoken.myenum.ResultCodeEnum;
import com.tom.jwttoken.properties.JwtProperties;
import com.tom.jwttoken.service.UserService;
import com.tom.jwttoken.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Component
@Aspect
@Slf4j
public class NeedLoginAop {

    @Autowired
    private UserService userService;

    @Autowired
    private  JwtProperties jwtProperties;



    //定义切面，所有的controller层都会监控
    @Pointcut("execution(* com.tom.jwttoken.controller..*.*(..))")
    public void doHander() {

    }

    @Around("doHander()")
    public Object exception(ProceedingJoinPoint joinPoint) throws MyException {
        Object result = null;
        try {
            //进入controller层前
            beforePoint(joinPoint);
            //放行
            result = joinPoint.proceed();
            //返回数据前
            afterPoint(joinPoint, result);
            return result;
        } catch (MyException e) {
            throw new MyException(ResultCodeEnum.SYSTEM_ERROR);
        } catch (Throwable e){
            throw new MyException(ResultCodeEnum.SYSTEM_ERROR);
        }

    }

    private Boolean beforePoint(ProceedingJoinPoint joinPoint) throws Exception {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        // 从 http 请求头中取出 token
        String token = request.getHeader("token");

        //得到要进入的是哪个controller方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();



        //加了@UserLoginToken注解的我要进行校验
        if (method.isAnnotationPresent(NeedToken.class)) {
            NeedToken needToken = method.getAnnotation(NeedToken.class);
            if (needToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new MyException(ResultCodeEnum.NO_TOKEN);
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new MyException(ResultCodeEnum.TOKEN_NO_USERID);
                }
                User user = userService.findUserById(userId);
                user.setPassword("");
                // 验证 token  要和上面生成token的密钥一致才能解析成功
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(
                        jwtProperties.getMiyao()+user.getPassword())).build();
                try {
                    //验证token
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new MyException(ResultCodeEnum.TOKEN_ERROR);
                }
                //得到这个方法控制器的所有形参
                Object[] args = joinPoint.getArgs();
                for (Object argItem : args) {
                    //如果这个控制器方法中有用户这个形参，
                    // 说明这个控制器需要用户的信息，那么我就把我这里解析出来的userId
                    // 赛进这个形参中，那样在控制器那边就能得到我赛的userId了
                    if (argItem instanceof UserVo) {
                        BeanUtils.copyProperties(user,argItem);
                    }
                }
                return true;
            }
        }
        return true;
    }


    private void afterPoint(ProceedingJoinPoint joinPoint, Object result) throws Exception {

    }

}