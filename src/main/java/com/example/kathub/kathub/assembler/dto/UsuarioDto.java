package com.example.kathub.kathub.assembler.dto;

import lombok.Data;

@Data
public class UsuarioDto{

    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String email;
}