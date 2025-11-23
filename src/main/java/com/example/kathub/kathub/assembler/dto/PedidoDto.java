package com.example.kathub.kathub.assembler.dto;

import java.time.LocalDate;

import lombok.Data;

@Data

public class PedidoDto{

    private Long id;
    private LocalDate fecha;
    private String estado;
    private Long usuarioId;
    private Long productoId;
}
    

