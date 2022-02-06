package com.covidstats.dao;

import com.covidstats.model.User;
import com.covidstats.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO implements UserIDAO{

    @Override
    public List<User> index() {
        return EntityManagerProvider.getEntityManager()
                .createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    @Override
    public User show(Object key) {
        return EntityManagerProvider.getEntityManager().find(User.class, key);
    }

    @Override
    public void store(User user) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception ignored) {
            }
        }
        entityManager.close();
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception ignored) {
            }
        }
        entityManager.close();
    }

    @Override
    public void destroy(User user) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception ignored) {
            }
        }
        entityManager.close();
    }

    @Override
    public User authenticateUser(String email, String password) {
        return EntityManagerProvider.getEntityManager()
                .createNamedQuery("User.authenticate", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }
}
