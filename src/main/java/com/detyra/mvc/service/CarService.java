package com.detyra.mvc.service;

import com.detyra.mvc.domain.dto.CarDTO;
import com.detyra.mvc.domain.dto.CarRequest;

import java.util.List;

public interface CarService {
    List<CarDTO> findAll();
    CarDTO save(CarRequest car);
    CarDTO update(CarDTO car);
    CarDTO findById(Integer id);
    CarDTO delete(CarDTO car);
}
