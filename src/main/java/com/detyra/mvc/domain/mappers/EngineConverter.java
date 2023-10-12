package com.detyra.mvc.domain.mappers;

import com.detyra.mvc.domain.dto.EngineDTO;
import com.detyra.mvc.domain.dto.EngineRequest;
import com.detyra.mvc.domain.entity.EngineEntity;

public class EngineConverter {

    public static EngineEntity toEngineEntity(EngineRequest engineRequest){
        EngineEntity engineEntity = new EngineEntity();
        engineEntity.setPower(engineRequest.getPower());
        engineEntity.setType(engineRequest.getType());
        return engineEntity;
    }

    public static EngineDTO toEngineDTO(EngineEntity engineEntity){
        EngineDTO engineDTO = new EngineDTO();
        engineDTO.setId(engineEntity.getId());
        engineDTO.setPower(engineEntity.getPower());
        engineDTO.setType(engineEntity.getType());
        return engineDTO;
    }

    public static EngineEntity toEngineEntity(EngineDTO engineDTO){
        EngineEntity engineEntity = new EngineEntity();
        engineEntity.setId(engineDTO.getId());
        engineEntity.setPower(engineDTO.getPower());
        engineEntity.setType(engineDTO.getType());
        return engineEntity;
    }
}
