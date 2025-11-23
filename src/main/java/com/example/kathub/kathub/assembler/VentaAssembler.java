package com.example.kathub.kathub.assembler;

import org.springframework.stereotype.Component;

import com.example.kathub.kathub.assembler.dto.VentaDto;
import com.example.kathub.kathub.model.Ventas;

@Component
public class VentaAssembler {
    
    public VentaDto toDto(Ventas entity){
        if (entity == null) return null;

        VentaDto dto = new VentaDto();
        dto.setId(entity.getId());
        dto.setFecha(entity.getFecha());
        dto.setEstado(entity.getEstado());
        dto.setTotal(entity.getTotal());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setProductoId(entity.getProducto().getId());
        dto.setMetodoPagoId(entity.getMetodoPago().getId());

        return dto;
    }
}
