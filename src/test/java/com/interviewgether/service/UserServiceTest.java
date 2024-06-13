package com.interviewgether.service;
import com.interviewgether.dto.user.UserAuthDTO;
import com.interviewgether.exception.DAL.EmailAlreadyExistsException;
import com.interviewgether.exception.DAL.UsernameAlreadyExistsException;
import com.interviewgether.model.User;
import com.interviewgether.repository.UserRepository;
import com.interviewgether.service.implementation.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserAccountService userAccountService;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldCreateUserWhenValidUserProvided() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        //when
        userService.create(user);
        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
        User captured = userArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(user);
    }

    @Test
    void shouldCreateUserWithProvidedUserAuthDTO() {
        UserAuthDTO userAuthDTO =
                new UserAuthDTO("username", "email", "password");
        when(userRepository.save(any(User.class))).thenAnswer(ans -> ans.getArguments()[0]);

        userService.create(userAuthDTO);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1))
                .save(userArgumentCaptor.capture());
        User captured = userArgumentCaptor.getValue();

        assertThat(captured.getUsername()).isEqualTo(userAuthDTO.getUsername());
        assertThat(captured.getEmail()).isEqualTo(userAuthDTO.getEmail());
        assertThat(captured.getPassword()).isEqualTo(userAuthDTO.getPassword());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreatingUserWithNull() {
        User nullUser = null;
        assertThatThrownBy(() -> userService.create(nullUser))
                .hasMessageContaining("User cannot be null");
    }

    @Test
    void shouldCreateUserAccountWhenCreatingUser(){
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        //when
        userService.create(user);
        //then
        verify(userAccountService, times(1))
                .create(user);
    }

    // ToDo: Check whether UserAccount is assigned to User

    @Test
    void shouldThrowEmailAlreadyExistsExceptionWhenEmailExists(){
        User user = new User();
        user.setEmail("email@test.com");
        when(userRepository.isEmailExists(anyString())).thenReturn(true);

        assertThatThrownBy(() -> userService.create(user))
                .isInstanceOf(EmailAlreadyExistsException.class)
                .hasMessageContaining("Email already exists");
    }

    @Test
    void shouldThrowUsernameAlreadyExistsExceptionWhenUsernameExists(){
        User user = new User();
        user.setUsername("username");
        when(userRepository.isUsernameExists(anyString())).thenReturn(true);

        assertThatThrownBy(() -> userService.create(user))
                .isInstanceOf(UsernameAlreadyExistsException.class)
                .hasMessageContaining("Username already exists");
    }

    @Test
    void shouldThrowExceptionWhenDataIntegrityViolationOccursDueToUsername(){
        when(userRepository.save(any(User.class)))
                .thenThrow(new DataIntegrityViolationException("username"));

        assertThatThrownBy(() -> userService.create(new User()))
                .isInstanceOf(UsernameAlreadyExistsException.class)
                .hasMessageContaining("Username already exists");
    }

    @Test
    void shouldThrowExceptionWhenDataIntegrityViolationOccursDueToEmail(){
        when(userRepository.save(any(User.class)))
                .thenThrow(new DataIntegrityViolationException("email"));

        assertThatThrownBy(() -> userService.create(new User()))
                .isInstanceOf(EmailAlreadyExistsException.class)
                .hasMessageContaining("Email already exists");
    }

    @Test
    void shouldReadUserByExistingId() {
        User user = new User();
        long id = 1L;
        user.setUserId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User retrieved = userService.readById(id);

        ArgumentCaptor<Long> argCaptor =
                ArgumentCaptor.forClass(long.class);

        verify(userRepository).findById(argCaptor.capture());
        long capturedId = argCaptor.getValue();

        assertThat(capturedId).isEqualTo(id);
        assertThat(retrieved.getUserId()).isEqualTo(id);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenIdDoesntExist() {
        long id = 1L;
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.readById(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("User with id " + id + " doesn't exist");
    }

    @Test
    void shouldUpdateUserWhenValidUserProvided() {
        User user = new User();
        user.setPassword("password");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        userService.update(user);

        ArgumentCaptor<User> userArgCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgCaptor.capture());
        User captured = userArgCaptor.getValue();

        assertThat(captured).isEqualTo(user);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenUpdatingUserWithNull() {
        User nullUser = null;
        assertThatThrownBy(() -> userService.update(nullUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User cannot be null");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldDeleteUserByExistingId() {
        long id = 1L;
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));

        userService.delete(id);

        ArgumentCaptor<Long> argCaptor =
                ArgumentCaptor.forClass(long.class);
        verify(userRepository).deleteById(argCaptor.capture());
        long capturedId = argCaptor.getValue();

        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void shouldReturnUserByValidUsername() {
        String username = "username";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        User retrieved = userService.readByUsername(username);

        ArgumentCaptor<String> argCaptor =
                ArgumentCaptor.forClass(String.class);
        verify(userRepository).findByUsername(argCaptor.capture());
        String captured = argCaptor.getValue();

        assertThat(captured).isEqualTo(username);
        assertThat(retrieved).isEqualTo(user);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenReadingByInvalidEmail() {
        assertThatThrownBy(() -> userService.readByEmail(null))
                .hasMessageContaining("Email cannot be null");
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenUsernameDoesntExist() {
        String username = "username";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.readByUsername(username))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("User " + username + " doesn't exist");
    }

    @Test
    void shouldReturnUserByValidEmail() {
        String email = "test@email.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User retrieved = userService.readByEmail(email);

        ArgumentCaptor<String> argCaptor =
                ArgumentCaptor.forClass(String.class);
        verify(userRepository).findByEmail( argCaptor.capture());
        String captured = argCaptor.getValue();

        assertThat(captured).isEqualTo(email);
        assertThat(retrieved).isEqualTo(user);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenReadingByInvalidUsername() {
        assertThatThrownBy(() -> userService.readByUsername(null))
                .hasMessageContaining("Username cannot be null");
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenEmailDoesntExist() {
        String email = "test@email.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.readByEmail(email))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("User with email: " + email + " doesn't exist");
    }
}