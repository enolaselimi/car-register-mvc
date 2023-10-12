package com.detyra.mvc.repository.impl;

import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.repository.EngineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.catalina.Engine;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EngineRepositoryImpl implements EngineRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_QUERY = "SELECT e FROM EngineEntity e WHERE e.id = :id";
    private static final String FIND_ALL_QUERY = "SELECT e FROM EngineEntity e";

    @Override
    public List<EngineEntity> findAll() {
        return entityManager.createQuery(FIND_ALL_QUERY, EngineEntity.class)
                .getResultList();
    }

    @Override
    public EngineEntity save(EngineEntity engine) {
        entityManager.persist(engine);
        return(engine);
    }

    @Override
    public EngineEntity update(EngineEntity engine) {
        return entityManager.merge(engine);
    }

    @Override
    public EngineEntity findById(Integer id) {
        return entityManager.createQuery(FIND_QUERY, EngineEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public EngineEntity delete(EngineEntity engine) {
        EngineEntity managed = entityManager.merge(engine);
        entityManager.remove(managed);
        return managed;
    }
}
