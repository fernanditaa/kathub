package com.example.kathub.kathub.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kathub.kathub.model.MetodoPago;
import com.example.kathub.kathub.repository.MetodoPagoRepository;


@RestController
@RequestMapping("/api/metodos-pago")
@CrossOrigin(origins = "http://localhost:5173")
public class MetodoPagoController {
    
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @GetMapping
    public List<MetodoPago> listarMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerMetodoPagoPorId(@PathVariable Long id) {
        return metodoPagoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<MetodoPago> crearMetodoPago(@RequestBody MetodoPago metodoPago) {
        MetodoPago nuevoMetodoPago = metodoPagoRepository.save(metodoPago);
        return ResponseEntity.status(201).body(nuevoMetodoPago);
        }

    @PatchMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodoPago(@PathVariable Long id,
                                                            @RequestBody MetodoPago metodoPagoDetalles) {

        return metodoPagoRepository.findById(id)
                .map(mp -> {

                    if(metodoPagoDetalles.getNombre() != null){
                        mp.setNombre(metodoPagoDetalles.getNombre());
                    }

                    if(metodoPagoDetalles.getDescripcion() != null){
                        mp.setDescripcion(metodoPagoDetalles.getDescripcion());
                    }

                    MetodoPago metodoPagoActualizado = metodoPagoRepository.save(mp);
                    return ResponseEntity.ok(metodoPagoActualizado);
                    
                })
                .orElse(ResponseEntity.notFound().build());
            }

    @DeleteMapping("/{id}")
    
    public ResponseEntity<?> eliminarMetodoPago(@PathVariable Long id) {
        if(!metodoPagoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        metodoPagoRepository.deleteById(id);
        return ResponseEntity.ok("Metodo de pago eliminado correctamente");
    }

}
