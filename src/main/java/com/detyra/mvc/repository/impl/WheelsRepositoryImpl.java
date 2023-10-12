package com.detyra.mvc.repository.impl;

import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.domain.entity.WheelsEntity;
import com.detyra.mvc.repository.WheelsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WheelsRepositoryImpl implements WheelsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_QUERY = "SELECT w FROM WheelsEntity w WHERE w.id = :id";
    private static final String FIND_ALL_QUERY = "SELECT w FROM WheelsEntity w";

    @Override
    public List<WheelsEntity> findAll() {
        return entityManager.createQuery(FIND_ALL_QUERY, WheelsEntity.class)
                .getResultList();
    }

    @Override
    public WheelsEntity save(WheelsEntity wheels) {
        entityManager.persist(wheels);
        return wheels;
    }

    @Override
    public WheelsEntity update(WheelsEntity wheels) {
        return entityManager.merge(wheels);
    }

    @Override
    public WheelsEntity findById(Integer id) {
        return entityManager.createQuery(FIND_QUERY, WheelsEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public WheelsEntity delete(WheelsEntity wheels) {
        WheelsEntity managed = entityManager.merge(wheels);
        entityManager.remove(managed);
        return managed;
    }
}
