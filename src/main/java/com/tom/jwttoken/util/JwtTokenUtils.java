package com.tom.jwttoken.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tom.jwttoken.entity.TokenEntity;
import com.tom.jwttoken.entity.User;
import com.tom.jwttoken.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class JwtTokenUtils {

    @Autowired
    private  JwtProperties jwtProperties;

    private JwtTokenUtils(){

    }

    public  TokenEntity makeToken(User user) {
        //withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
        TokenEntity tokenEntity = new TokenEntity();
        String token="";
        Date date = new Date(System.currentTimeMillis()+ jwtProperties.getExpiretTime());
        token= JWT.create()
                .withAudience(user.getUserId())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(jwtProperties.getMiyao()+user.getPassword()));
        tokenEntity.setToken(token);
        tokenEntity.setExpireTime(DateUtil.format(date));
        return tokenEntity;
    }

//    刷新token


}
