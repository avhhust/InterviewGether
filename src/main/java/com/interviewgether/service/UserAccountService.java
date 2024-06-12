package com.interviewgether.service;

import com.interviewgether.model.User;
import com.interviewgether.model.UserAccount;

public interface UserAccountService{
    UserAccount create(User user);
    UserAccount readById(long id);
    UserAccount update(UserAccount userAccount);
    void delete(long id);
    UserAccount readByUser(User user);

}
