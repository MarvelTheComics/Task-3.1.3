package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;


public interface UserDao {
    void add(User user);
    void delete(Integer id);
    List<User> getUsers();
    void update(User user);
    User getUserByID(Integer id);
    Optional<User> findByEmail(String email);
}
