package com.example.kathub.kathub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import com.example.kathub.kathub.model.Producto;
import com.example.kathub.kathub.service.ProductoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerCatalogo() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.save(producto);
        return productoService.save(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {

        return productoService.findById(id)
        .map(producto -> {
            producto.setNombre(productoDetalles.getNombre());
            producto.setDescripcion(productoDetalles.getDescripcion());
            producto.setPrecio(productoDetalles.getPrecio());
            producto.setStock(productoDetalles.getStock());

            Producto productoActualizado = productoService.save(producto);
            return ResponseEntity.ok(productoActualizado);
        })
        .orElse(ResponseEntity.notFound().build());
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Producto> actualizarProductoParcial(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        return productoService.findById(id)
        .map(producto -> {
            if (productoDetalles.getNombre() != null) {
                producto.setNombre(productoDetalles.getNombre());
            }
            if (productoDetalles.getDescripcion() != null) {
                producto.setDescripcion(productoDetalles.getDescripcion());
            }
            if (productoDetalles.getPrecio() != null) {
                producto.setPrecio(productoDetalles.getPrecio());
            }
            if (productoDetalles.getStock() != null) {
                producto.setStock(productoDetalles.getStock());
            }

            Producto productoActualizado = productoService.save(producto);
            return ResponseEntity.ok(productoActualizado);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if(!productoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
