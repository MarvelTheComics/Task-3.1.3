package ru.kata.spring.boot_security.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImpTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserDetailServiceImp userDetailServiceImp;

    @DisplayName("Correct Username")
    @Test
    void loadUserByUsername() {
        User user = new User();
        String userName = "username";
        user.setEmail(userName);
        Set<Role> roles = new HashSet<>(Set.of(new Role("ROLE_USER")));
        user.setRoles(roles);
        when(userDao.findByEmail(userName)).thenReturn(Optional.of(user));

        UserDetails result = userDetailServiceImp.loadUserByUsername(userName);

        assertEquals(userName, result.getUsername());
        assertTrue(
                result.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))
        );
        verify(userDao).findByEmail(userName);
    }

    @DisplayName("Incorrect Username")
    @Test
    void ExceptionByIncorrectUsername() {
        String userName = "username";
        when(userDao.findByEmail(userName)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailServiceImp.loadUserByUsername(userName));
        verify(userDao).findByEmail(userName);
    }
}