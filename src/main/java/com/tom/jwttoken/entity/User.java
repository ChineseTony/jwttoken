package com.tom.jwttoken.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User  {

    private String userId;

    private String password;

    private String nickname;

    private String sex;

    private Integer age;


}
