package com.example.kathub.kathub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "productos")
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String medida;
    private Double precio;
    private Integer stock;
    private String categoria;
    private String imagenUrl;

    public Producto(){
    }

    public Producto(String nombre, String descripcion, String medida, Double precio, Integer stock, String categoria){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.medida = medida;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }
    
}
