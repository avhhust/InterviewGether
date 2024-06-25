package com.interviewgether.service;

import com.interviewgether.model.UserAccount;

public interface UserAccountService{

    // ToDo: replace with userId
    UserAccount create(long userId);
    UserAccount readById(long id);
    UserAccount update(UserAccount userAccount);
    void delete(long id);
    UserAccount readByUserId(long userId);
}
