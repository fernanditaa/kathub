package com.example.kathub.kathub.assembler;

import org.springframework.stereotype.Component;

import com.example.kathub.kathub.assembler.dto.PedidoDto;
import com.example.kathub.kathub.model.Pedido;

@Component
public class PedidoAssembler {

    public PedidoDto toDto(Pedido entity){
        if(entity == null) return null;

        PedidoDto dto = new PedidoDto();
        dto.setId(entity.getId());
        dto.setFecha(entity.getFecha());
        dto.setEstado(entity.getEstado());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setProductoId(entity.getProducto().getId());
        return dto;
    }
    
}
