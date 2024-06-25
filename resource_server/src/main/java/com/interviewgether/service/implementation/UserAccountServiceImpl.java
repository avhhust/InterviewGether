package com.interviewgether.service.implementation;

import com.interviewgether.model.UserAccount;
import com.interviewgether.repository.UserAccountRepository;
import com.interviewgether.service.UserAccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.OffsetDateTime;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount create(long userId) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userId);
        userAccount.setCreatedAt(OffsetDateTime.now());
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount readById(long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserAccount with id " + id + " is not found"));
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        Assert.notNull(userAccount, "UserAccount must not be null");
        return userAccountRepository.save(userAccount);
    }

    @Override
    public void delete(long id) {
        readById(id);
        userAccountRepository.deleteById(id);
    }

    // ToDo: implement readByUserId
    @Override
    public UserAccount readByUserId(long userId) {
        return null;
    }
}
