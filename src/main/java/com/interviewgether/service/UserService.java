package com.interviewgether.service;

import com.interviewgether.dto.user.UserAuthDTO;
import com.interviewgether.dto.user.UserTransformer;
import com.interviewgether.model.User;

public interface UserService {
    void create(User user);
    void create(UserAuthDTO userAuthDTO);
    User readById(long id);
    User update(User updatedUser);
    void delete(long id);
    User readByEmail(String email);
    User readByUsername(String username);
}
