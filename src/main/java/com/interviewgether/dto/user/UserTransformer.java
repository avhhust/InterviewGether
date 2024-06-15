package com.interviewgether.dto.user;

import com.interviewgether.model.User;

public class UserTransformer {
    public static User convertToUser(UserRegisterDTO userRegisterDTO){
        User user = new User();
        user.setEmail(userRegisterDTO.getEmail());
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(userRegisterDTO.getPassword());
        return user;
    }

    public static User convertToUser(UserLoginDTO userLoginDTO){
        User user = new User();
        user.setUsername(userLoginDTO.getUsername());
        user.setPassword(userLoginDTO.getPassword());
        return user;
    }

}