package com.detyra.mvc.controller;

import com.detyra.mvc.domain.dto.WheelsDTO;
import com.detyra.mvc.domain.dto.WheelsRequest;
import com.detyra.mvc.service.WheelsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars/wheels")
public class WheelsController {
    @Autowired
    private WheelsService wheelsService;

    private static final String TEMPLATE_LOCATION = "tailwindcss/";

    @GetMapping
    public String getAllWheels(Model model){
        model.addAttribute("wheelsList", wheelsService.findAll());
        return TEMPLATE_LOCATION.concat("wheels-view");
    }

    @GetMapping("/form")
    public String addOrUpdateWheels(Model model, @RequestParam(required = false) Integer wheelsId){
        if(wheelsId == null){
            model.addAttribute("title", "Add Wheels");
            model.addAttribute("wheelsForm", new WheelsDTO());
        } else {
            model.addAttribute("title", "Update Wheels");
            model.addAttribute("wheelsForm", wheelsService.findById(wheelsId));
        }
        return TEMPLATE_LOCATION.concat("wheels-view-form");
    }

    @PostMapping("/form")
    public String addOrUpdateWheels(@ModelAttribute(name = "wheelsForm") @Valid WheelsDTO wheelsDTO,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return TEMPLATE_LOCATION.concat("wheels-view-form");
        }
        if(wheelsDTO.getId() == null){
            wheelsService.save(new WheelsRequest(wheelsDTO.getSize(),wheelsDTO.getType()));
        } else {
            wheelsService.update(wheelsDTO);
        }
        return "redirect:/cars/wheels";
    }

    @GetMapping("/{wheelsId}/delete")
    public String deleteWheels(@PathVariable Integer wheelsId){
        WheelsDTO wheelsDTO = wheelsService.delete(wheelsId);
        return "redirect:/cars/wheels";
    }
}
