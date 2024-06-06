package com.interviewgether.dto.user;

import com.interviewgether.model.User;

public class UserTransformer {
    public static User convertToUser(UserAuthDTO userAuthDTO){
        User user = new User();
        user.setEmail(userAuthDTO.getEmail());
        user.setUsername(userAuthDTO.getUsername());
        user.setPassword(userAuthDTO.getPassword());
        return user;
    }

    public static UserAuthDTO convertToUserCreateDTO(User user){
        return new UserAuthDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

}