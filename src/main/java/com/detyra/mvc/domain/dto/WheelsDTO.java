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
public class WheelsDTO extends BaseDomainDTO {
    private Integer id;
    @NotEmpty(message = "Wheels Size is required")
    @Positive(message = "Wheels Size must be a positive number.")
    private String size;
    @NotBlank(message = "Wheels Type is required.")
    private String type;
}
