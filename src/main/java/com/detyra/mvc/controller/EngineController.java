package com.detyra.mvc.controller;

import com.detyra.mvc.domain.dto.EngineDTO;
import com.detyra.mvc.domain.dto.EngineRequest;
import com.detyra.mvc.domain.entity.EngineEntity;
import com.detyra.mvc.domain.filter.Filter;
import com.detyra.mvc.domain.mappers.EngineConverter;
import com.detyra.mvc.service.EngineService;
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
import java.util.Objects;

@RestController
@RequestMapping("/cars/engine")
public class EngineController {
    @Autowired
    private EngineService engineService;
//    private static final String TEMPLATE_LOCATION = "tailwindcss/";

    @GetMapping
    public ResponseEntity<List<EngineDTO>> getAllEngines(@RequestParam(required = false) String power,
                                                         @RequestParam(required = false) String type,
                                                         @RequestParam(required = false) String sort){
//      model.addAttribute("engineList", engineService.findAll());
//      return TEMPLATE_LOCATION.concat("engine-view");

        if(type != null){
            type = type.replace("%20"," ");
        }

        if(sort != null){
            String[] sortValue = sort.split(":");
            if(Objects.equals(sortValue[0], "power")){
                powerFilter.setSort(sortValue[1]);
            } else if (Objects.equals(sortValue[0], "type")) {
                typeFilter.setSort(sortValue[1]);
            }
        }

        return ResponseEntity.ok(engineService.findAll(powerFilter,typeFilter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EngineDTO> getEngineById(@PathVariable Integer id){
        return ResponseEntity.ok(engineService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createEngine(@RequestBody EngineRequest engineRequest, UriComponentsBuilder ucb){
        EngineDTO engineDTO = engineService.save(engineRequest);
        URI locationOfCreatedEngine = ucb
                .path("/cars/engine/{id}")
                .buildAndExpand(engineDTO.getId())
                .toUri();
        return ResponseEntity.created(locationOfCreatedEngine).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateEngine(@PathVariable Integer id, @RequestBody EngineRequest engineRequest){
        EngineEntity engine = EngineConverter.toEngineEntity(engineRequest);
        EngineDTO engineDTO = EngineConverter.toEngineDTO(engine);
        engineDTO.setId(id);
        return engineService.update(engineDTO)!=null?ResponseEntity.noContent().build():ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EngineDTO> deleteEngine(@PathVariable Integer id){
        return ResponseEntity.ok(engineService.delete(id));
    }

//    @GetMapping("/form")
//    public String addEngine(Model model){
//        model.addAttribute("title", "Engine Registration");
//        model.addAttribute("engineForm", new EngineDTO());
//        return TEMPLATE_LOCATION.concat("engine-view-form");
//    }

//    @GetMapping("/form/{engineId}")
//    public String updateEngine(Model model, @PathVariable Integer engineId){
//        model.addAttribute("title", "Engine Update");
//        model.addAttribute("engineForm", engineService.findById(engineId));
//        return TEMPLATE_LOCATION.concat("engine-view-form");
//    }


//    @PostMapping("/form")
//    public String addEngine(@ModelAttribute(name = "engineForm") @Valid EngineDTO engineDTO,
//                            BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return TEMPLATE_LOCATION.concat("engine-view-form");
//        }
//        if(engineDTO.getId() != null){
//            engineService.update(engineDTO);
//        }else{
//            engineService.save(new EngineRequest(engineDTO.getPower(), engineDTO.getType()));
//        }
//        return "redirect:/cars/engine";
//    }

//    @GetMapping("/{engineId}/delete")
//    public String deleteEngine(@PathVariable Integer engineId){
//        engineService.delete(engineId);
//        return "redirect:/cars/engine";
//    }
}
