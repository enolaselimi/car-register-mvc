package com.detyra.mvc.repository.impl;

import com.detyra.mvc.domain.entity.CarEntity;
import com.detyra.mvc.repository.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_QUERY = "SELECT c FROM CarEntity c WHERE c.id = :id";
    private static final String FIND_ALL_QUERY = "SELECT c FROM CarEntity c";

    @Override
    public List<CarEntity> findAll(){
        return entityManager.createQuery(FIND_ALL_QUERY, CarEntity.class)
                .getResultList();
    }

    @Override
    public CarEntity save(CarEntity car) {
        entityManager.persist(car);
        return car;
    }

    @Override
    public CarEntity update(CarEntity car) {
        return entityManager.merge(car);
    }

    @Override
    public CarEntity findById(Integer id) {
        return entityManager.createQuery(FIND_QUERY, CarEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public CarEntity delete(CarEntity car) {
        CarEntity managed = entityManager.merge(car);
        entityManager.remove(managed);
        return managed;
    }
}
