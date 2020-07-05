package com.tom.jwttoken;

import com.tom.jwttoken.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableConfigurationProperties(JwtProperties.class)
public class JwttokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwttokenApplication.class, args);
    }

}
