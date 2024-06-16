package com.interviewgether.repository;

import com.interviewgether.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(
            value = "SELECT r.role_id, r.role_name FROM roles r " +
            "INNER JOIN users_roles ur ON r.role_id = ur.role_id " +
            "INNER JOIN users u ON u.user_id = ur.user_id " +
            "WHERE u.user_id = ?1",
            nativeQuery = true
    )
    List<Role> findAllByUser(long userId);

    Optional<Role> findByRoleName(String roleName);
}
