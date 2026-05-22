package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.RequestUserDtoEdit;
import ru.kata.spring.boot_security.demo.dto.RequestUserDtoRegistration;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface UserService {
    void add(RequestUserDtoRegistration requestUserDtoRegistration);
    void delete(Integer id);
    void update(RequestUserDtoEdit requestUserDtoEdit, Integer id);
    List<User> getUsers();
    User getUserById(Integer id);
}
