package com.tom.jwttoken.vo;


import com.tom.jwttoken.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVo {

    private String userId;


    private String nickname;

    private String sex;

    private Integer age;


    public UserVo(User user){
        BeanUtils.copyProperties(user,this);
    }

    public static void main(String[] args) {
        User  user = new User("u1234","1234","wangto","m",20);
        UserVo vo = new UserVo(user);
        System.out.println(vo);
    }
}
