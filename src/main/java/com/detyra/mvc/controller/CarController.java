package com.detyra.mvc.controller;

import com.detyra.mvc.domain.dto.CarDTO;
import com.detyra.mvc.domain.dto.CarRequest;
import com.detyra.mvc.domain.entity.CarEntity;
import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.domain.entity.WheelsEntity;
import com.detyra.mvc.domain.mappers.CarConverter;
import com.detyra.mvc.domain.mappers.EngineConverter;
import com.detyra.mvc.domain.mappers.WheelsConverter;
import com.detyra.mvc.service.CarService;
import com.detyra.mvc.service.EngineService;
import com.detyra.mvc.service.WheelsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private EngineService engineService;

    @Autowired
    private WheelsService wheelsService;

//    private static final String TEMPLATE_LOCATION = "tailwindcss/";

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars(){
//        model.addAttribute("carsList", carService.findAll());
//        return TEMPLATE_LOCATION.concat("cars-view");
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.findById(id));
    }

//    @GetMapping("/form")
//    public String AddOrUpdateCar(@RequestParam(required = false) Integer carId){
//        if(carId == null){
//            model.addAttribute("title", "Add Car");
//            model.addAttribute("carForm", new CarDTO());
//            model.addAttribute("engines", engineService.findAll());
//            model.addAttribute("wheels", wheelsService.findAll());
//        } else {
//            model.addAttribute("title", "Update Car");
//            model.addAttribute("carForm", carService.findById(carId));
//            model.addAttribute("engines", engineService.findAll());
//            model.addAttribute("wheels", wheelsService.findAll());
//        }
//        return TEMPLATE_LOCATION.concat("cars-view-form");
//    }

    @PostMapping("/form")
    public ResponseEntity<Void> addCar(@RequestBody CarRequest carRequest, UriComponentsBuilder ucb) {
//        if(result.hasErrors()){
//            model.addAttribute("title", "Car Form");
//            model.addAttribute("engines", engineService.findAll());
//            model.addAttribute("wheels", wheelsService.findAll());
//            return TEMPLATE_LOCATION.concat("cars-view-form");
//        }
//        if(carDTO.getId() == null){
//            CarRequest carToAdd = new CarRequest(carDTO.getFromYear(),
//                    carDTO.getName(), carDTO.getToYear(), carDTO.getType(),
//                    carDTO.getEngine().getId(), carDTO.getWheels().getId());
//            carService.save(carToAdd);
//        }else{
//            carService.update(carDTO);
//        }

        CarDTO createdCar = carService.save(carRequest);
        URI locationOfCreatedCar = ucb
                .path("/cars/form/{carId}")
                .buildAndExpand(createdCar.getId())
                .toUri();
        return ResponseEntity.created(locationOfCreatedCar).build();
    }

    @PutMapping("/form/{carId}")
    public ResponseEntity<Void> updateCar(@PathVariable Integer carId, @RequestBody CarRequest carRequest){
        EngineEntity engine = EngineConverter.toEngineEntity(engineService.findById(carRequest.getEngineId()));
        WheelsEntity wheels = WheelsConverter.toWheelsEntity(wheelsService.findById(carRequest.getWheelsId()));
        CarEntity updatedCar = CarConverter.toCarEntity(carRequest, engine, wheels);
        CarDTO car = carService.update(CarConverter.toCarDTO(updatedCar));
        return car!=null?ResponseEntity.noContent().build():ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{carId}/delete")
    public ResponseEntity<CarDTO> deleteCar(@PathVariable Integer carId){
        return ResponseEntity.ok(carService.delete(carService.findById(carId)));
    }
}
