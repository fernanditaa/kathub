package com.example.kathub.kathub.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.kathub.kathub.assembler.dto.UsuarioDto;
import com.example.kathub.kathub.model.Usuario;

@Component
public class UsuarioAssembler {

    public UsuarioDto toDto(Usuario entity){
        if (entity == null) return null;

        UsuarioDto dto = new UsuarioDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setTelefono(entity.getTelefono());
        dto.setDireccion(entity.getDireccion());
        dto.setEmail(entity.getEmail());

        return dto;

    }

    public Usuario toEntity(UsuarioDto dto){
        if(dto == null) return null;

        Usuario entity = new Usuario();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());
        entity.setEmail(dto.getEmail());

        return entity;
    }
    public List<UsuarioDto> toDtoList(List<Usuario> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}