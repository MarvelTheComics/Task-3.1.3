package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImp implements RoleDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getRoles() {
        return em.createQuery("FROM Role", Role.class)
                .getResultList();
    }

    @Override
    public Optional<Role> getRole(Integer id) {
        return Optional.ofNullable(em.find(Role.class, id));
    }

}
