package com.tom.jwttoken.service;


import com.tom.jwttoken.entity.User;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    //简化操作不从数据库查询
    public boolean login(User user){
        if ("1".equalsIgnoreCase(user.getUserId() )
                && "1234".equalsIgnoreCase(user.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    public User findUserById(String userId){
        return new User(userId,"1234","wangto","m",20);
    }
}
