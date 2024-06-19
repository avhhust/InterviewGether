package com.interviewgether.repository;

import com.interviewgether.model.Role;
import com.interviewgether.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldSelectAllRolesByUser(){
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@test.com");
        user.setPassword("password");
        Role roleUser = roleRepository.save(new Role("USER"));
        Role roleAdmin = roleRepository.save(new Role("ADMIN"));
        user.addRole(roleAdmin);
        user.addRole(roleUser);
        userRepository.save(user);

        List<Role> foundedRoles = roleRepository.findAllByUser(user.getUserId());

        assertThat(foundedRoles).containsAll(user.getRoles());
    }

}
