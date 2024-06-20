package com.interviewgether.repository;

import com.interviewgether.model.Role;
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
    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldReturnTrueWhenEmailExists() {
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
    void shouldReturnFalseWhenEmailDoesntExist() {
        //given
        String email = "test@email.com";
        //when
        boolean result = userRepository.isEmailExists(email);
        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnTrueWhenUsernameExists() {
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
    void shouldReturnFalseWhenUsernameDoesntExist() {
        //given
        String username = "username";
        //when
        boolean result = userRepository.isUsernameExists(username);
        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnUserByUsername(){
        String username = "username";
        User user = new User();
        user.setUsername(username);
        user.setEmail("test@email.com");
        user.setPassword("password");
        userRepository.save(user);

        User founded = userRepository.findByUsername(username).orElse(new User());

        assertThat(founded).isEqualTo(user);
    }

    @Test
    void shouldReturnUserWithFetchedRolesByUsername() {
        String username = "username";
        User givenUser = new User();
        givenUser.setUsername(username);
        givenUser.setEmail("email@test.com");
        givenUser.setPassword("password");
        Role roleUser = roleRepository.save(new Role("USER"));
        givenUser.addRole(roleUser);
        userRepository.save(givenUser);

        User retrieved = userRepository.findByUsernameWithRoles(username).orElse(new User());

        assertThat(retrieved).isEqualTo(givenUser);
        assertThat(retrieved.getRoles()).contains(roleUser);
    }
}
