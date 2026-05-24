package ru.kata.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;


    public void add(User user) {
        entityManager.persist(user);
        System.out.println("repo ok");
    }


    public void delete(Integer id) {
        User user = Optional.ofNullable(entityManager.find(User.class, id))
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found!"));
        entityManager.remove(user);
    }


    public List<User> getUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }


    public void update(User user) {
        entityManager.merge(user);
    }


    public Optional<User> getUserByID(Integer id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(entityManager.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email).getSingleResult());
    }

}
