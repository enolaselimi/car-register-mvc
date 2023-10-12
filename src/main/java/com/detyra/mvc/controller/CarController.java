package com.detyra.mvc.controller;

import com.detyra.mvc.domain.dto.CarDTO;
import com.detyra.mvc.domain.dto.CarRequest;
import com.detyra.mvc.service.CarService;
import com.detyra.mvc.service.EngineService;
import com.detyra.mvc.service.WheelsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private EngineService engineService;

    @Autowired
    private WheelsService wheelsService;

    private static final String TEMPLATE_LOCATION = "tailwindcss/";

    @GetMapping()
    public String getAllCars(Model model){
        model.addAttribute("carsList", carService.findAll());
        return TEMPLATE_LOCATION.concat("cars-view");
    }

    @GetMapping("/form")
    public String AddOrUpdateCar(Model model, @RequestParam(required = false) Integer carId){
        if(carId == null){
            model.addAttribute("title", "Add Car");
            model.addAttribute("carForm", new CarDTO());
            model.addAttribute("engines", engineService.findAll());
            model.addAttribute("wheels", wheelsService.findAll());
        } else {
            model.addAttribute("title", "Update Car");
            model.addAttribute("carForm", carService.findById(carId));
            model.addAttribute("engines", engineService.findAll());
            model.addAttribute("wheels", wheelsService.findAll());
        }
        return TEMPLATE_LOCATION.concat("cars-view-form");
    }

    @PostMapping("/form")
    public String addOrUpdateCar(@ModelAttribute(name = "carForm") @Valid CarDTO carDTO,
                                 BindingResult result, Model model){
        if(result.getErrorCount() > 0){
            model.addAttribute("engines", engineService.findAll());
            model.addAttribute("wheels", wheelsService.findAll());
            return TEMPLATE_LOCATION.concat("cars-view-form");
        }
        if(carDTO.getId() == null){
            CarRequest carToAdd = new CarRequest(carDTO.getFromYear(),
                    carDTO.getName(), carDTO.getToYear(), carDTO.getType(),
                    carDTO.getEngine().getId(), carDTO.getWheels().getId());
            carService.save(carToAdd);
        }else{
            carService.update(carDTO);
        }
        return "redirect:/cars";
    }

    @GetMapping("/{carId}/delete")
    public String deleteCar(@PathVariable Integer carId){
        CarDTO carDTO = carService.delete(carService.findById(carId));
        return "redirect:/cars";
    }
}
