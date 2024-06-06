package com.interviewgether.service;

import com.interviewgether.model.User;

public interface UserService {
    User create(User user);
    User readById(long id);
    User update(User updatedUser);
    void delete(long id);
    User readByEmail(String email);
    User readByUsername(String username);
}
