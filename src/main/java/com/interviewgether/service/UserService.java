package com.interviewgether.service;

import com.interviewgether.dto.user.UserRegisterDTO;
import com.interviewgether.model.User;

public interface UserService {
    void create(User user);
    void create(UserRegisterDTO userRegisterDTO);
    User readById(long id);
    User update(User updatedUser);
    void delete(long id);
    User readByEmail(String email);
    User readByUsername(String username);
    User readUserWithRolesByUsername(String username);
}
