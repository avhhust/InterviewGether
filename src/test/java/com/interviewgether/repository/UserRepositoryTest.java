package com.interviewgether.repository;

import com.interviewgether.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void Should_ReturnTrue_When_EmailExists() {
        //given
        String email = "test@email.com";
        User user = new User();
        user.setEmail(email);
        user.setUsername("username");
        user.setPassword("password");
        userRepository.save(user);
        //when
        boolean result = userRepository.isEmailExists(email);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void Should_ReturnFalse_When_EmailDoesntExist() {
        //given
        String email = "test@email.com";
        //when
        boolean result = userRepository.isEmailExists(email);
        //then
        assertThat(result).isFalse();
    }

    @Test
    void Should_ReturnTrue_When_UsernameExists() {
        //given
        String username = "username";
        User user = new User();
        user.setUsername(username);
        user.setEmail("test@email.com");
        user.setPassword("password");
        userRepository.save(user);
        //when
        boolean result = userRepository.isUsernameExists(username);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void Should_ReturnFalse_When_UsernameDoesntExist() {
        //given
        String username = "username";
        //when
        boolean result = userRepository.isUsernameExists(username);
        //then
        assertThat(result).isFalse();
    }


}
