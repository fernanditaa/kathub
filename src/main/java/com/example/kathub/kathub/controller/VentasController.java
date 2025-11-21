package com.example.kathub.kathub.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kathub.kathub.model.Ventas;
import com.example.kathub.kathub.repository.VentaRepository;


@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:5173")
public class VentasController {

    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping
    public ResponseEntity<List<Ventas>> verHistorial() {
        List<Ventas> ventas = ventaRepository.findAll();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ventas> buscarVentas(@PathVariable Long id) {
        return ventaRepository.findById(id)
                .map(venta -> ResponseEntity.ok(venta))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ventas> crearVenta(@RequestBody Ventas venta) {
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDate.now());
        }
        if (venta.getEstado() == null) {
            venta.setEstado("PENDIENTE");
            
        }
        Ventas nuevaVenta = ventaRepository.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ventas> actualizarVenta(@PathVariable Long id, @RequestBody Ventas ventaDetalles) {

        Ventas venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        venta.setUsuario(ventaDetalles.getUsuario());
        venta.setMetodoPago(ventaDetalles.getMetodoPago());
        venta.setTotal(ventaDetalles.getTotal());
        venta.setFecha(ventaDetalles.getFecha());
        venta.setEstado(ventaDetalles.getEstado());

        Ventas ventaActualizada = ventaRepository.save(venta);
        return ResponseEntity.ok(ventaActualizada);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Ventas> actualizarEstadoVenta(@PathVariable Long id, @RequestBody cambiarEstadoRequest request) {
        Ventas venta = ventaRepository.findById(id)
                .orElse(null);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        venta.setEstado(request.getEstado());
        Ventas ventaActualizada = ventaRepository.save(venta);
        return ResponseEntity.ok(ventaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        if(!ventaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ventaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public static class cambiarEstadoRequest {
        private String estado;

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
}