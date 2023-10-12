package com.detyra.mvc.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO extends BaseDomainDTO{
    private Integer id;
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Year format is invalid.")
    private String fromYear;
    @NotBlank(message = "Car Name is required")
    private String name;
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Year format is invalid.")
    private String toYear;
    @NotBlank(message = "Car Type is required")
    private String type;
//    @Valid
    private EngineDTO engine;
//    @Valid
    private WheelsDTO wheels;
}
