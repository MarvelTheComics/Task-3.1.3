package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;


    public void add(User user) {
        entityManager.persist(user);
    }


    public void delete(Integer id) {
        User user = entityManager.find(User.class, id);
        if(user != null) {
            entityManager.remove(user);
        }
    }


    public List<User> getUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }


    public void update(User user) {
        entityManager.merge(user);
    }


    public User getUserByID(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(entityManager.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email).getSingleResult());
    }

}
