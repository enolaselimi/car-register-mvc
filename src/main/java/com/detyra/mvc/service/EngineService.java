package com.detyra.mvc.service;

import com.detyra.mvc.domain.dto.EngineDTO;
import com.detyra.mvc.domain.dto.EngineRequest;

import java.util.List;

public interface EngineService {
    List<EngineDTO> findAll();
    EngineDTO save(EngineRequest engineRequest);
    EngineDTO update(EngineDTO engineDTO);
    EngineDTO findById(Integer id);
    EngineDTO delete(Integer id);
}
