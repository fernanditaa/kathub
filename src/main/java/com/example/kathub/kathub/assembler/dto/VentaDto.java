package com.example.kathub.kathub.assembler.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VentaDto{

    private Long id;
    private LocalDate fecha;
    private Double total;
    private String estado;
    private Long usuarioId;
    private Long productoId;
    private Long metodoPagoId;
}