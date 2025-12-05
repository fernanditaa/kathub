package com.example.kathub.kathub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kathub.kathub.model.Ventas;
import com.example.kathub.kathub.repository.VentaRepository;

@Service
public class VentaService {
    
    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todas las ventas
    public List<Ventas> obtenerVentas() {
        return ventaRepository.findAll();
    }

    // Buscar venta por ID
    public Ventas buscarVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    // Guardar nueva venta
    @Transactional
    public Ventas guardarVenta(Ventas venta) {

        if (venta.getFecha() == null) {
            venta.setFecha(java.time.LocalDate.now());
        }

        if (venta.getEstado() == null || venta.getEstado().isEmpty()) {
            venta.setEstado("COMPLETADO");  // más consistente con tu carga inicial
        }

        return ventaRepository.save(venta);
    }

    // Eliminar venta
    @Transactional
    public boolean eliminarVenta(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar estado de venta
    @Transactional
    public Ventas actualizarEstado(Long id, String nuevoEstado) {
        return ventaRepository.findById(id).map(venta -> {
            venta.setEstado(nuevoEstado);
            return ventaRepository.save(venta);
        }).orElse(null);
    }
}
