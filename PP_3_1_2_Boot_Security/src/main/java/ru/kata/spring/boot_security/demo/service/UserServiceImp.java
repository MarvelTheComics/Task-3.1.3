package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.record.RequestRecordEdit;
import ru.kata.spring.boot_security.demo.record.RequestRecordReg;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleService roleService;

    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder, RoleService roleService, UserMapper userMapper) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }


    @Transactional
    @Override
    public void add(RequestRecordReg requestRecordReg) {
        User newUser = userMapper.requestReg(requestRecordReg);
        newUser.setRoles(new HashSet<>(Set.of(roleService.getRole(
                requestRecordReg.roleId()))));
        newUser.setId(null);
        newUser.setPassword(passwordEncoder.encode(requestRecordReg.password()));
        userDao.add(newUser);
        System.out.println("service ok");
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(RequestRecordEdit requestRecordEdit, Integer id) {
        User editUser = userMapper.requestEdit(requestRecordEdit,
                userDao.getUserByID(id).orElseThrow(() -> new UsernameNotFoundException("User no found!")));
        if(requestRecordEdit.password() != null && !requestRecordEdit.password().isBlank()) {
            editUser.setPassword(passwordEncoder.encode(requestRecordEdit.password()));
        }
        if(requestRecordEdit.roleId() != null) {
            editUser.setRoles(new HashSet<>(Set.of(roleService.getRole(requestRecordEdit.roleId()))));
        }
        userDao.update(editUser);
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional
    @Override
    public User getUserById(Integer id) {
        return userDao.getUserByID(id).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
