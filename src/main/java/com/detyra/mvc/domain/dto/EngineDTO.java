package com.detyra.mvc.domain.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EngineDTO extends BaseDomainDTO{
    private Integer id;
    @NotNull(message = "Engine Power is required")
    @Positive(message = "Engine Power must be a positive number.")
    private Integer power;
    @NotBlank(message = "Engine Type is required.")
    private String type;
}
