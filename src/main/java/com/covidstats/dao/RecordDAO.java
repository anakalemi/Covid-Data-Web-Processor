package com.covidstats.dao;

import com.covidstats.model.Record;
import com.covidstats.utils.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;

public class RecordDAO implements IDAO<Record>{

    @Override
    public List<Record> index() {
        return EntityManagerProvider.getEntityManager()
                .createNamedQuery("Record.findAll", Record.class)
                .getResultList();
    }

    @Override
    public Record show(Object key) {
        return EntityManagerProvider.getEntityManager().find(Record.class, key);
    }

    @Override
    public void store(Record record) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(record);
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
    public void update(Record record) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(record);
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
    public void destroy(Record record) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(record)) {
                record = entityManager.merge(record);
            }
            entityManager.remove(record);
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
