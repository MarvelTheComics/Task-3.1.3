package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.record.RequestRecordEdit;
import ru.kata.spring.boot_security.demo.record.RequestRecordReg;

import java.util.List;

public interface UserService {
    void add(RequestRecordReg requestRecordReg);
    void delete(Integer id);
    void update(RequestRecordEdit requestRecordEdit, Integer id);
    List<User> getUsers();
    User getUserById(Integer id);
}
