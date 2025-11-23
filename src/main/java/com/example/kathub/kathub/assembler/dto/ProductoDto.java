package com.example.kathub.kathub.assembler.dto;

import lombok.Data;

@Data
public class ProductoDto{

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
}
