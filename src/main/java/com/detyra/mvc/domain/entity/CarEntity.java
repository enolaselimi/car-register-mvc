package com.detyra.mvc.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="car")
public class CarEntity extends BaseEntity {
    private String fromYear;
    private String name;
    private String toYear;
    private String type;
    @ManyToOne
    @JoinColumn(name="engine_id", referencedColumnName = "id")
    private EngineEntity engine;
    @ManyToOne
    @JoinColumn(name="wheels_id", referencedColumnName = "id")
    private WheelsEntity wheels;
}
