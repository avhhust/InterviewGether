package com.interviewgether.service.implementation;

import com.interviewgether.dto.user.UserRegisterDTO;
import com.interviewgether.dto.user.UserTransformer;
import com.interviewgether.exception.DAL.EmailAlreadyExistsException;
import com.interviewgether.exception.DAL.UserAlreadyExistsException;
import com.interviewgether.exception.DAL.UsernameAlreadyExistsException;
import com.interviewgether.model.Role;
import com.interviewgether.model.User;
import com.interviewgether.model.UserAccount;
import com.interviewgether.repository.RoleRepository;
import com.interviewgether.repository.UserRepository;
import com.interviewgether.service.UserAccountService;
import com.interviewgether.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAccountService userAccountService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserAccountService userAccountService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userAccountService = userAccountService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void create(User user) throws UserAlreadyExistsException {
        Assert.notNull(user, "User cannot be null");
        if(userRepository.isEmailExists(user.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists", "email");
        }
        if(userRepository.isUsernameExists(user.getUsername())){
            throw new UsernameAlreadyExistsException("Username already exists", "username");
        }
        try{
            setDefaultRole(user);
            User persistedUser = userRepository.save(user);
            UserAccount account = userAccountService.create(persistedUser);
            persistedUser.setUserAccount(account);
        } catch (DataIntegrityViolationException e){
            // ToDo: Review later
            String errorMessage = e.getMostSpecificCause().getMessage();
            if(errorMessage.contains("username"))
                throw new UsernameAlreadyExistsException("Username already exists", "username");
            else if (errorMessage.contains("email"))
                throw new EmailAlreadyExistsException("Email already exists", "email");
            else
                throw new UserAlreadyExistsException("User already exists");
        }
    }

    @Override
    @Transactional
    public void create(UserRegisterDTO userRegisterDTO) {
        String encodedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());
        userRegisterDTO.setPassword(encodedPassword);
        create(UserTransformer.convertToUser(userRegisterDTO));
    }

    @Transactional
    public void setDefaultRole(User user){
        Role defaultRole = roleRepository.findByRoleName("USER").orElseGet(
                () -> roleRepository.save(new Role("USER"))
        );
        user.addRole(defaultRole);
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
                .orElseThrow(() -> new EntityNotFoundException("User with username: " + username + " doesn't exist"));
    }

    @Override
    public User readUserWithRolesByUsername(String username){
        Assert.notNull(username, "Username cannot be null");
        return userRepository
                .findByUsernameWithRoles(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username: " + username + " doesn't exist"));
    }
}