package com.interviewgether.service.implementation;


import com.interviewgether.model.Role;
import com.interviewgether.repository.RoleRepository;
import com.interviewgether.repository.UserRepository;
import com.interviewgether.service.RoleService;
import com.interviewgether.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Role create(Role role) {
        Assert.notNull(role, "Role cannot be null");
        return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) {
        return roleRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role with Id " + id + " not found"));
    }

    @Override
    public Role update(Role updatedRole) {
        Assert.notNull(updatedRole, "Role cannot be null");
        readById(updatedRole.getId());
        return roleRepository.save(updatedRole);
    }

    @Override
    public void delete(long id) {
        readById(id);
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAllByUser(long userId) {
        // to avoid circular dependency
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " doesn't exist"));
        return roleRepository.findAllByUser(userId);
    }

    @Override
    public Role findByRoleName(String name) {
        Assert.notNull(name, "Role name cannot be null");
        return roleRepository
                .findByRoleName(name)
                .orElseThrow(() -> new EntityNotFoundException("Role " + name + " doesn't exists"));
    }
}
