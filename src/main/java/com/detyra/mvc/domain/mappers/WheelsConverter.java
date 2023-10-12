package com.detyra.mvc.domain.mappers;

import com.detyra.mvc.domain.dto.WheelsDTO;
import com.detyra.mvc.domain.dto.WheelsRequest;
import com.detyra.mvc.domain.entity.WheelsEntity;

public class WheelsConverter {
    public static WheelsEntity toWheelsEntity(WheelsRequest wheelsRequest){
        WheelsEntity wheelsEntity = new WheelsEntity();
        wheelsEntity.setSize(wheelsRequest.getSize());
        wheelsEntity.setType(wheelsRequest.getType());
        return wheelsEntity;
    }

    public static WheelsDTO toWheelsDTO(WheelsEntity wheelsEntity){
        WheelsDTO wheelsDTO = new WheelsDTO();
        wheelsDTO.setId(wheelsEntity.getId());
        wheelsDTO.setSize(wheelsEntity.getSize());
        wheelsDTO.setType(wheelsEntity.getType());
        return wheelsDTO;
    }

    public static WheelsEntity toWheelsEntity(WheelsDTO wheelsDTO){
        WheelsEntity wheelsEntity = new WheelsEntity();
        wheelsEntity.setId(wheelsDTO.getId());
        wheelsEntity.setSize(wheelsDTO.getSize());
        wheelsEntity.setType(wheelsDTO.getType());
        return wheelsEntity;
    }
}
