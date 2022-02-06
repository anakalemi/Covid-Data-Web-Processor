package com.covidstats.dao;

import com.covidstats.model.Country;
import com.covidstats.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;

public class CountryDAO implements IDAO<Country>{

        @Override
        public List<Country> index() {
            return EntityManagerProvider.getEntityManager()
                    .createNamedQuery("Country.findAll", Country.class)
                    .getResultList();
        }

        @Override
        public Country show(Object key) {
            return EntityManagerProvider.getEntityManager().find(Country.class, key);
        }

        @Override
        public void store(Country country) {
            EntityManager entityManager = EntityManagerProvider.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(country);
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
        public void update(Country country) {
            EntityManager entityManager = EntityManagerProvider.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(country);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                try {
                    entityManager.getTransaction().rollback();
                } catch (Exception ignored){
                }
            }
            entityManager.close();
        }

        @Override
        public void destroy(Country country) {
            EntityManager entityManager = EntityManagerProvider.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                if (!entityManager.contains(country)) {
                    country = entityManager.merge(country);
                }
                entityManager.remove(country);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                try {
                    entityManager.getTransaction().rollback();
                } catch (Exception ignored){
                }
            }
            entityManager.close();
        }
    }


