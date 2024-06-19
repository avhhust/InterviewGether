package com.interviewgether.service;

import com.interviewgether.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role employee);
    Role readById(long id);
    Role update(Role updatedEmployee);
    void delete(long id);
    List<Role> getAll();
    List<Role> getRolesByUser(long userId);
    Role findByRoleName(String name);
}
