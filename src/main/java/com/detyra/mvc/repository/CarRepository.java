package com.detyra.mvc.repository;
import com.detyra.mvc.domain.entity.CarEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CarRepository {
    List<CarEntity> findAll();
    CarEntity save(CarEntity car);
    CarEntity update(CarEntity car);
    CarEntity findById(Integer id);
    CarEntity delete(CarEntity car);
}
