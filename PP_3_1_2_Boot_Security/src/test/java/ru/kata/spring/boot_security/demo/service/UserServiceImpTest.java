package ru.kata.spring.boot_security.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {
    Integer id = 1;
    User user = new User();
    Role role = new Role();
    Set <Role> roles = new HashSet<>(Set.of(role));

    @Mock
    UserDao userDao;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImp userService;

    @Test
    void add() {
        user.setPassword("123");
        when(passwordEncoder.encode("123")).thenReturn("password encode!");

        userService.add(user, roles);

        assertEquals("password encode!", user.getPassword());
        assertEquals(roles, user.getRoles());
        verify(userDao).add(user);
    }

    @Test
    void delete() {
        userService.delete(id);
        verify(userDao).delete(id);
    }

    @Test
    void update() {
        userService.update(user);
        verify(userDao).update(user);
    }

    @Test
    void getUsers() {
        List <User> users = new ArrayList<>(List.of(new User(), new User()));
        when(userDao.getUsers()).thenReturn(users);

        List <User> result = userService.getUsers();
        assertEquals(users, result);
        verify(userDao).getUsers();
    }

    @Test
    void getUserByID() {
        when(userDao.getUserByID(id)).thenReturn(user);

        User result = userService.getUserByID(id);
        assertEquals(user, result);
        verify(userDao).getUserByID(id);
    }
}