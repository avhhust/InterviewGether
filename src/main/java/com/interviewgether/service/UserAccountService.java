package com.interviewgether.service;

import com.interviewgether.model.User;
import com.interviewgether.model.UserAccount;

public interface UserAccountService{

    // ToDo: replace with userId
    UserAccount create(User user);
    UserAccount readById(long id);
    UserAccount update(UserAccount userAccount);
    void delete(long id);

    // ToDo: Remove or get userId
    UserAccount readByUser(User user);

}
