package ru.kata.spring.boot_security.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImpTest {
    Integer id = 1;
    Role role = new Role();

    @Mock
    RoleDao roleDao;

    @InjectMocks
    RoleServiceImp roleService;

    @Test
    void getRole() {
        when(roleDao.getRole(id)).thenReturn(role);

        Role result = roleService.getRole(id);
        assertEquals(role, result);
        verify(roleDao).getRole(id);
    }
}