package com.example.kathub.kathub.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha; //fecha de compra
    private String estado; //estado del pedido

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; //usuario que hizo el pedido

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto; //producto comprado
    
}
