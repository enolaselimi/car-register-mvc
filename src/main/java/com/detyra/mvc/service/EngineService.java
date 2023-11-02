package com.detyra.mvc.service;

import com.detyra.mvc.domain.dto.EngineDTO;
import com.detyra.mvc.domain.dto.EngineRequest;
import com.detyra.mvc.domain.filter.Filter;

import java.util.List;

public interface EngineService {
    List<EngineDTO> findAll(Filter...filters);
    EngineDTO save(EngineRequest engineRequest);
    EngineDTO update(EngineDTO engineDTO);
    EngineDTO findById(Integer id);
    EngineDTO delete(Integer id);
}
