package com.detyra.mvc.repository;

import com.detyra.mvc.domain.entity.CarEntity;
import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.domain.filter.Filter;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EngineRepository {
    List<EngineEntity> findAll(Filter...filters);
    EngineEntity save(EngineEntity engine);
    EngineEntity update(EngineEntity engine);
    EngineEntity findById(Integer id);
    EngineEntity delete(EngineEntity engine);
}
