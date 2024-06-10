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
        // Check if user with such email already exists
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            //  If so then return custom exception
            throw new EmailAlreadyExistsException("Email already exists", "email");
        }
        // Check if user with such username already exists
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            //  If so then return custom exception
            throw new UsernameAlreadyExistsException("Username already exists", "username");
        }
        try{
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            // In case when
            String causeFieldName = extractFieldFromExceptionMessage(e.getMostSpecificCause().getMessage());
            throw new UserAlreadyExistsException(causeFieldName);
        }
    }

    private String extractFieldFromExceptionMessage(String message){
        return message.contains("username") ? "username" : "email";
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
    public void deleteByUsername(String username) {
        // find user by username and extract its Id, then pass to delete() method
        delete(readByUsername(username).getUserId());
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
