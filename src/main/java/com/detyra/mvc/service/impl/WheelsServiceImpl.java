package com.detyra.mvc.service.impl;

import com.detyra.mvc.domain.dto.WheelsDTO;
import com.detyra.mvc.domain.dto.WheelsRequest;
import com.detyra.mvc.domain.entity.WheelsEntity;
import com.detyra.mvc.domain.mappers.WheelsConverter;
import com.detyra.mvc.repository.WheelsRepository;
import com.detyra.mvc.service.WheelsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.detyra.mvc.domain.mappers.WheelsConverter.toWheelsDTO;

@Service
public class WheelsServiceImpl implements WheelsService {
    @Autowired
    private WheelsRepository wheelsRepository;

    @Override
    public List<WheelsDTO> findAll() {
        List<WheelsEntity> wheels = wheelsRepository.findAll();
        return wheels.stream()
                .map(w -> toWheelsDTO(w))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public WheelsDTO save(WheelsRequest wheelsRequest) {
        return toWheelsDTO(wheelsRepository.save(WheelsConverter.toWheelsEntity(wheelsRequest)));
    }

    @Transactional
    @Override
    public WheelsDTO update(WheelsDTO wheelsDTO) {
        return toWheelsDTO(wheelsRepository.update(WheelsConverter.toWheelsEntity(wheelsDTO)));
    }

    @Override
    public WheelsDTO findById(Integer id) {
        return toWheelsDTO(wheelsRepository.findById(id));
    }

    @Transactional
    @Override
    public WheelsDTO delete(Integer id) {
        return toWheelsDTO(wheelsRepository.delete(WheelsConverter.toWheelsEntity(findById(id))));
    }
}
