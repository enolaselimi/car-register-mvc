package com.detyra.mvc.domain.mappers;

import com.detyra.mvc.domain.dto.CarDTO;
import com.detyra.mvc.domain.dto.CarRequest;
import com.detyra.mvc.domain.entity.CarEntity;
import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.domain.entity.WheelsEntity;

public class CarConverter {
    public static CarEntity toCarEntity(CarRequest carRequest, EngineEntity engine, WheelsEntity wheels){
        CarEntity carEntity = new CarEntity();
        carEntity.setName(carRequest.getName());
        carEntity.setType(carRequest.getType());
        carEntity.setFromYear(carRequest.getFromYear());
        carEntity.setToYear(carRequest.getToYear());
        carEntity.setEngine(engine);
        carEntity.setWheels(wheels);
        return carEntity;
    }

    public static CarDTO toCarDTO(CarEntity carEntity){
        CarDTO carDTO = new CarDTO();
        carDTO.setId(carEntity.getId());
        carDTO.setName(carEntity.getName());
        carDTO.setType(carEntity.getType());
        carDTO.setFromYear(carEntity.getFromYear());
        carDTO.setToYear(carEntity.getToYear());
        carDTO.setEngine(EngineConverter.toEngineDTO(carEntity.getEngine()));
        carDTO.setWheels(WheelsConverter.toWheelsDTO(carEntity.getWheels()));
        return carDTO;
    }

    public static CarEntity toCarEntity(CarDTO carDTO, EngineEntity engine, WheelsEntity wheels){
        CarEntity carEntity = new CarEntity();
        carEntity.setId(carDTO.getId());
        carEntity.setName(carDTO.getName());
        carEntity.setType(carDTO.getType());
        carEntity.setFromYear(carDTO.getFromYear());
        carEntity.setToYear(carDTO.getToYear());
        carEntity.setEngine(engine);
        carEntity.setWheels(wheels);
        return carEntity;
    }
}
