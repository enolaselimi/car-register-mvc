package com.detyra.mvc.repository.impl;

import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.domain.filter.Filter;
import com.detyra.mvc.repository.EngineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EngineRepositoryImpl implements EngineRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_QUERY = "SELECT e FROM EngineEntity e WHERE e.id = :id";

    @Override
    public List<EngineEntity> findAll(Filter...filters) {
        String FIND_ALL_QUERY = "SELECT * FROM ENGINE WHERE 1=1 ";
        if(filters == null){
            return entityManager.createNativeQuery(FIND_ALL_QUERY, EngineEntity.class)
                    .getResultList();
        }
        if(filters[0].getValue() != null){
            FIND_ALL_QUERY += "AND "+filters[0].getField()+" "+filters[0].getOperator()+" "+filters[0].getValue()+" ";
        }
        if(filters[1].getValue() != null){
            FIND_ALL_QUERY += "AND "+filters[1].getField()+" "+filters[1].getOperator()+" '"+filters[1].getValue()+"' ";
        }

        if(filters[0].getSort() != null){
            FIND_ALL_QUERY += "ORDER BY "+filters[0].getField()+" "+filters[0].getSort();
        } else if (filters[1].getSort() != null) {
            FIND_ALL_QUERY += "ORDER BY "+filters[1].getField()+" "+filters[1].getSort();
        }

        return entityManager.createNativeQuery(FIND_ALL_QUERY, EngineEntity.class)
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
