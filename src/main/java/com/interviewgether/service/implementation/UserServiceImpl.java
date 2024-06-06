package com.interviewgether.service.implementation;

import com.interviewgether.exception.DAL.EmailAlreadyExistsException;
import com.interviewgether.exception.DAL.UserAlreadyExistsException;
import com.interviewgether.exception.DAL.UsernameAlreadyExistsException;
import com.interviewgether.model.User;
import com.interviewgether.repository.UserRepository;
import com.interviewgether.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws UserAlreadyExistsException {
        Assert.notNull(user, "User cannot be null");
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " doesn't exist"));
    }

    @Override
    public User update(User updatedUser) {
        Assert.notNull(updatedUser, "User cannot be null");
        readById(updatedUser.getUserId());
        return userRepository.save(updatedUser);
    }

    @Override
    public void delete(long id) {
        readById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User readByEmail(String email) {
        Assert.notNull(email, "Email cannot be null");
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " doesn't exist"));
    }

    @Override
    public User readByUsername(String username) {
        Assert.notNull(username, "Username cannot be null");
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User " + username + " doesn't exist"));
    }
}
