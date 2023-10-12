package com.detyra.mvc.service.impl;

import com.detyra.mvc.domain.dto.EngineDTO;
import com.detyra.mvc.domain.dto.EngineRequest;
import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.domain.mappers.EngineConverter;
import com.detyra.mvc.repository.EngineRepository;
import com.detyra.mvc.service.EngineService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static com.detyra.mvc.domain.mappers.EngineConverter.toEngineDTO;
@Service
public class EngineServiceImpl implements EngineService {

    @Autowired
    private EngineRepository engineRepository;

    @Override
    public List<EngineDTO> findAll() {
        List<EngineEntity> engines = engineRepository.findAll();
        return engines.stream()
                .map(e -> toEngineDTO(e))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EngineDTO save(EngineRequest engineRequest) {
        return toEngineDTO(engineRepository.save(EngineConverter.toEngineEntity(engineRequest)));
    }

    @Transactional
    @Override
    public EngineDTO update(EngineDTO engineDTO) {
        return toEngineDTO(engineRepository.update(EngineConverter.toEngineEntity(engineDTO)));
    }

    @Override
    public EngineDTO findById(Integer id) {
        return toEngineDTO(engineRepository.findById(id));
    }

    @Transactional
    @Override
    public EngineDTO delete(Integer id) {
        EngineEntity engineToDelete =  EngineConverter.toEngineEntity(findById(id));
        return toEngineDTO(engineRepository.delete(engineToDelete));
    }
}
