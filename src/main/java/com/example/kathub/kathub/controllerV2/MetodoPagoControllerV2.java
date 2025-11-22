package com.example.kathub.kathub.controllerV2;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.kathub.kathub.model.MetodoPago;
import com.example.kathub.kathub.repository.MetodoPagoRepository;
import com.example.kathub.kathub._exception.RecursoNoEncontradoException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/metodos-pago")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Metodo de Pago Controller V2", description = "Endpoints para gestionar métodos de pago - Versión 2")
public class MetodoPagoControllerV2 {

    private final MetodoPagoRepository metodoPagoRepository;

    public MetodoPagoControllerV2(MetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    // GET todos
    @Operation(
        summary = "Listar todos los métodos de pago",
        description = "Devuelve el listado completo de métodos de pago disponibles."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    @GetMapping
    public ResponseEntity<List<MetodoPago>> listarMetodosPago() {
        List<MetodoPago> metodos = metodoPagoRepository.findAll();
        return ResponseEntity.ok(metodos);
    }

    // GET por id
    @Operation(
        summary = "Obtener un método de pago por ID",
        description = "Recupera los datos de un método de pago específico utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerMetodoPagoPorId(@PathVariable Long id) {
        MetodoPago metodo = metodoPagoRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Método de pago no encontrado con id: " + id)
            );
        return ResponseEntity.ok(metodo);
    }

    // POST
    @Operation(
        summary = "Crear un nuevo método de pago",
        description = "Registra un nuevo método de pago en el sistema."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Método de pago creado exitosamente")
    })
    @PostMapping
    public ResponseEntity<MetodoPago> crearMetodoPago(@RequestBody MetodoPago metodoPago) {
        MetodoPago nuevo = metodoPagoRepository.save(metodoPago);
        return ResponseEntity.status(201).body(nuevo);
    }

    // PUT
    @Operation(
        summary = "Actualizar un método de pago",
        description = "Actualiza todos los datos de un método de pago existente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Método de pago actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodoPago(
            @PathVariable Long id,
            @RequestBody MetodoPago detalles) {

        MetodoPago metodo = metodoPagoRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Método de pago no encontrado con id: " + id)
            );

        metodo.setNombre(detalles.getNombre());
        // Si luego agregas más campos, actualízalos aquí

        MetodoPago actualizado = metodoPagoRepository.save(metodo);
        return ResponseEntity.ok(actualizado);
    }

    // PATCH
    @Operation(
        summary = "Actualizar parcialmente un método de pago",
        description = "Modifica solo algunos campos del método de pago."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Método de pago actualizado parcialmente"),
        @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodoPagoParcial(
            @PathVariable Long id,
            @RequestBody MetodoPago detalles) {

        MetodoPago metodo = metodoPagoRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Método de pago no encontrado con id: " + id)
            );

        if (detalles.getNombre() != null) {
            metodo.setNombre(detalles.getNombre());
        }

        MetodoPago actualizado = metodoPagoRepository.save(metodo);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @Operation(
        summary = "Eliminar un método de pago por ID",
        description = "Elimina un método de pago existente utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Método de pago eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMetodoPago(@PathVariable Long id) {

        if (!metodoPagoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Método de pago no encontrado con id: " + id);
        }

        metodoPagoRepository.deleteById(id);
        return ResponseEntity.ok("Método de pago eliminado correctamente");
    }
}