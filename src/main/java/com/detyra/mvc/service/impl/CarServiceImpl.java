package com.detyra.mvc.service.impl;

import com.detyra.mvc.domain.dto.CarDTO;
import com.detyra.mvc.domain.dto.CarRequest;
import com.detyra.mvc.domain.dto.EngineDTO;
import com.detyra.mvc.domain.entity.CarEntity;
import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.domain.entity.WheelsEntity;
import com.detyra.mvc.domain.mappers.CarConverter;
import com.detyra.mvc.domain.mappers.EngineConverter;
import com.detyra.mvc.domain.mappers.WheelsConverter;
import com.detyra.mvc.repository.CarRepository;
import com.detyra.mvc.service.CarService;
import com.detyra.mvc.service.EngineService;
import com.detyra.mvc.service.WheelsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static com.detyra.mvc.domain.mappers.CarConverter.toCarDTO;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private EngineService engineService;
    @Autowired
    private WheelsService wheelsService;

    @Override
    public List<CarDTO> findAll() {
        List<CarEntity> carsList = carRepository.findAll();
        return carsList.stream()
                .map(c -> toCarDTO(c))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CarDTO save(CarRequest car) {
        Integer engineId = car.getEngineId();
        EngineEntity carToAddEngine = EngineConverter.toEngineEntity(engineService.findById(engineId));

        Integer wheelsId = car.getWheelsId();
        WheelsEntity carToAddWheels = WheelsConverter.toWheelsEntity(wheelsService.findById(wheelsId));

        CarEntity carToAdd = CarConverter.toCarEntity(car, carToAddEngine, carToAddWheels);
        return toCarDTO(carRepository.save(carToAdd));
    }

    @Transactional
    @Override
    public CarDTO update(CarDTO car) {
        EngineEntity carToUpdateEngine = EngineConverter.toEngineEntity(car.getEngine());
        WheelsEntity carToUpdateWheels = WheelsConverter.toWheelsEntity(car.getWheels());
        CarEntity carToUpdate = CarConverter.toCarEntity(car, carToUpdateEngine, carToUpdateWheels);
        return toCarDTO(carRepository.update(carToUpdate));
    }

    @Override
    public CarDTO findById(Integer id) {
        return toCarDTO(carRepository.findById(id));
    }

    @Transactional
    @Override
    public CarDTO delete(CarDTO car) {
        EngineEntity carToDeleteEngine = EngineConverter.toEngineEntity(car.getEngine());
        WheelsEntity carToDeleteWheels = WheelsConverter.toWheelsEntity(car.getWheels());
        CarEntity carToDelete = carRepository.delete(CarConverter.toCarEntity(car, carToDeleteEngine, carToDeleteWheels));
        return toCarDTO(carToDelete);
    }
}
