package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found by name: " + name));
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> findAll() {
        return roleDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Role findById(long id) {
        return roleDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found by id: " + id));
    }
}
