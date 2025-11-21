package com.example.kathub.kathub.service;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.kathub.kathub.repository.VentaRepository;
import com.example.kathub.kathub.model.Ventas;
import java.util.List;

@Service
public class VentaService {
    
    @Autowired
    private VentaRepository ventaRepository;

    public List<Ventas> obtenerVentas() {
        return ventaRepository.findAll();
    }

    public Ventas buscarVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }
    public Ventas guardarVenta(Ventas venta) {
        if (venta.getFecha() == null) {
            venta.setFecha(java.time.LocalDate.now());
        }
        if (venta.getEstado() == null) {
            venta.setEstado("PAGADO");
    }
        return ventaRepository.save(venta);
    }

    public boolean eliminarVenta(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Ventas actualizarEstado(Long id, String nuevoEstado) {
        return ventaRepository.findById(id).map(venta -> {
            venta.setEstado(nuevoEstado);
            return ventaRepository.save(venta);
        }).orElse(null);
    }
}
