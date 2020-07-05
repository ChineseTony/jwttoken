package com.tom.jwttoken.properties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix="com.tom.jwt")
@Component// 以组件的方式使用，使用的时候可以直接注入
public class JwtProperties {

    private Integer expiretTime;

    private String miyao;


}
