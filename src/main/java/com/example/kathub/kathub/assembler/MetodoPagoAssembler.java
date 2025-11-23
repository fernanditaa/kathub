package com.example.kathub.kathub.assembler;

import org.springframework.stereotype.Component;

import com.example.kathub.kathub.assembler.dto.MetodoPagoDto;
import com.example.kathub.kathub.model.MetodoPago;

@Component
public class MetodoPagoAssembler {

    public MetodoPagoDto toDto(MetodoPago entity){
        if(entity == null) return null;

        MetodoPagoDto dto = new MetodoPagoDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public MetodoPago toEntity(MetodoPagoDto dto){
        if(dto == null) return null;

        MetodoPago mp = new MetodoPago();
        mp.setId(dto.getId());
        mp.setNombre(dto.getNombre());
        return mp;
    }
    
}
