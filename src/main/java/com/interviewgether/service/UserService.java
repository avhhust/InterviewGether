package com.interviewgether.service;

import com.interviewgether.dto.user.UserAuthDTO;
import com.interviewgether.dto.user.UserTransformer;
import com.interviewgether.model.User;

public interface UserService {
    User create(User user);
    default User create(UserAuthDTO userAuthDTO){
        return create(UserTransformer.convertToUser(userAuthDTO));
    }
    User readById(long id);
    User update(User updatedUser);
    void delete(long id);
    void deleteByUsername(String username);
    User readByEmail(String email);
    User readByUsername(String username);
}
