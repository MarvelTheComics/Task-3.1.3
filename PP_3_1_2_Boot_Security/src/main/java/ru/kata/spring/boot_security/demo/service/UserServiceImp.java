package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dto.RequestUserDtoEdit;
import ru.kata.spring.boot_security.demo.dto.RequestUserDtoRegistration;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.model.User;

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
        this.userMapper = userMapper;
        this.roleService = roleService;
    }


    @Transactional
    @Override
    public void add(RequestUserDtoRegistration requestUserDtoRegistration) {
        User newUser = userMapper.requestUserDtoToUserReg(requestUserDtoRegistration);
        if(requestUserDtoRegistration.getRoleId() == null) {
            newUser.setRoles(new HashSet<>(Set.of(roleService.getRole(1))));
        } else {
            newUser.setRoles(new HashSet<>(Set.of(roleService.getRole(
                            requestUserDtoRegistration.getRoleId()))));
        }
        newUser.setPassword(passwordEncoder.encode(requestUserDtoRegistration.getPassword()));
        newUser.setId(null);
        userDao.add(newUser);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(RequestUserDtoEdit requestUserDtoEdit, Integer id) {
        User editUser = userMapper.requestUserDtoToUserUpdate(requestUserDtoEdit,
                userDao.getUserByID(id).orElseThrow(() -> new UsernameNotFoundException("User no found!")));
        if(requestUserDtoEdit.getPassword() != null && !requestUserDtoEdit.getPassword().isBlank()) {
            editUser.setPassword(passwordEncoder.encode(requestUserDtoEdit.getPassword()));
        }
        if(requestUserDtoEdit.getRoleId() != null) {
            editUser.setRoles(new HashSet<>(Set.of(roleService.getRole(requestUserDtoEdit.getRoleId()))));
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
