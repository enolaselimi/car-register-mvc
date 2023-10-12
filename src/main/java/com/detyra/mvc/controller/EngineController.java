package com.detyra.mvc.controller;

import com.detyra.mvc.domain.dto.EngineDTO;
import com.detyra.mvc.domain.dto.EngineRequest;
import com.detyra.mvc.service.EngineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars/engine")
public class EngineController {
    @Autowired
    private EngineService engineService;
    private static final String TEMPLATE_LOCATION = "tailwindcss/";

    @GetMapping
    public String getAllEngines(Model model){
        model.addAttribute("engineList", engineService.findAll());
       return TEMPLATE_LOCATION.concat("engine-view");
    }

    @GetMapping("/form")
    public String addEngine(Model model){
        model.addAttribute("title", "Engine Registration");
        model.addAttribute("engineForm", new EngineDTO());
        return TEMPLATE_LOCATION.concat("engine-view-form");
    }

    @GetMapping("/form/{engineId}")
    public String updateEngine(Model model, @PathVariable Integer engineId){
        model.addAttribute("title", "Engine Update");
        model.addAttribute("engineForm", engineService.findById(engineId));
        return TEMPLATE_LOCATION.concat("engine-view-form");
    }


    @PostMapping("/form")
    public String addEngine(@ModelAttribute(name = "engineForm") @Valid EngineDTO engineDTO,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return TEMPLATE_LOCATION.concat("engine-view-form");
        }
        if(engineDTO.getId() != null){
            engineService.update(engineDTO);
        }else{
            engineService.save(new EngineRequest(engineDTO.getPower(), engineDTO.getType()));
        }
        return "redirect:/cars/engine";
    }

    @GetMapping("/{engineId}/delete")
    public String deleteEngine(@PathVariable Integer engineId){
        EngineDTO deletedEngine = engineService.delete(engineId);
        return "redirect:/cars/engine";
    }
}
