package com.detyra.mvc.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="engine")
public class EngineEntity extends BaseEntity{
    private Integer power;
    private String type;
}
