package com.interviewgether.repository;

import com.interviewgether.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END FROM User u " +
            "WHERE u.email = ?1"
    )
    Boolean isEmailExists(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END FROM User u " +
            "WHERE u.username = ?1"
    )
    Boolean isUsernameExists(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u " +
            "JOIN FETCH u.roles " +
            "WHERE u.username = :username"
    )
    Optional<User> findByUsernameWithRoles(String username);
}
