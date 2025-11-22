package com.example.kathub.kathub.controllerV2;

import java.util.List;

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

import com.example.kathub.kathub._exception.RecursoNoEncontradoException;
import com.example.kathub.kathub.model.Ventas;
import com.example.kathub.kathub.repository.VentaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/ventas")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Venta Controller V2", description = "Endpoints para gestionar ventas - Versión 2")
public class VentasControllerV2 {

    private final VentaRepository ventaRepository;

    public VentasControllerV2(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    // ============================
    // GET /api/v2/ventas
    // ============================
    @Operation(
        summary = "Listar todas las ventas",
        description = "Devuelve el listado completo de ventas registradas."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Ventas>> listarVentas() {
        List<Ventas> ventas = ventaRepository.findAll();
        return ResponseEntity.ok(ventas);
    }

    // ============================
    // GET /api/v2/ventas/{id}
    // ============================
    @Operation(
        summary = "Obtener una venta por ID",
        description = "Recupera los datos de una venta específica utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ventas> obtenerVentaPorId(@PathVariable Long id) {
        Ventas venta = ventaRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Venta no encontrada con id: " + id)
            );
        return ResponseEntity.ok(venta);
    }

    // ============================
    // POST /api/v2/ventas
    // ============================
    @Operation(
        summary = "Registrar una nueva venta",
        description = "Crea un nuevo registro de venta en el sistema."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Venta creada exitosamente")
    })
    @PostMapping
    public ResponseEntity<Ventas> crearVenta(@RequestBody Ventas venta) {
        Ventas nuevaVenta = ventaRepository.save(venta);
        return ResponseEntity.status(201).body(nuevaVenta);
    }

    // ============================
    // PUT /api/v2/ventas/{id}
    // ============================
    @Operation(
        summary = "Actualizar una venta",
        description = "Actualiza todos los datos de una venta existente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venta actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ventas> actualizarVenta(
            @PathVariable Long id,
            @RequestBody Ventas detalles) {

        Ventas venta = ventaRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Venta no encontrada con id: " + id)
            );

        // Ajusta estos campos a lo que tengas en tu entidad Ventas
        venta.setUsuario(detalles.getUsuario());
        venta.setMetodoPago(detalles.getMetodoPago());
        venta.setProducto(detalles.getProducto());
        venta.setTotal(detalles.getTotal());
        venta.setFecha(detalles.getFecha());
        venta.setEstado(detalles.getEstado());

        Ventas actualizada = ventaRepository.save(venta);
        return ResponseEntity.ok(actualizada);
    }

    // ============================
    // PATCH /api/v2/ventas/{id}
    // ============================
    @Operation(
        summary = "Actualizar parcialmente una venta",
        description = "Modifica solo algunos campos de la venta."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venta actualizada parcialmente"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Ventas> actualizarVentaParcial(
            @PathVariable Long id,
            @RequestBody Ventas detalles) {

        Ventas venta = ventaRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Venta no encontrada con id: " + id)
            );

        if (detalles.getUsuario() != null) {
            venta.setUsuario(detalles.getUsuario());
        }
        if (detalles.getMetodoPago() != null) {
            venta.setMetodoPago(detalles.getMetodoPago());
        }
        if (detalles.getProducto() != null) {
            venta.setProducto(detalles.getProducto());
        }
        if (detalles.getTotal() != null) {
            venta.setTotal(detalles.getTotal());
        }
        if (detalles.getFecha() != null) {
            venta.setFecha(detalles.getFecha());
        }
        if (detalles.getEstado() != null) {
            venta.setEstado(detalles.getEstado());
        }

        Ventas actualizada = ventaRepository.save(venta);
        return ResponseEntity.ok(actualizada);
    }

    // ============================
    // DELETE /api/v2/ventas/{id}
    // ============================
    @Operation(
        summary = "Eliminar una venta por ID",
        description = "Elimina una venta existente utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Venta eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id) {

        if (!ventaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Venta no encontrada con id: " + id);
        }

        ventaRepository.deleteById(id);
        return ResponseEntity.ok("Venta eliminada correctamente");
    }
}