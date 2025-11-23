package com.example.kathub.kathub.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.kathub.kathub.assembler.dto.ProductoDto;
import com.example.kathub.kathub.model.Producto;

@Component
public class ProductoAssembler {

    public ProductoDto toDto(Producto entity){
        if(entity == null) return null;

        ProductoDto dto = new ProductoDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        dto.setStock(entity.getStock());
        return dto;
    }

    public Producto toEntity(ProductoDto dto) {
        if(dto == null) return null;

        Producto p = new Producto();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        
        return p;
    }

    public List<ProductoDto> toDoList(List<Producto> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList());
        
    }

}
