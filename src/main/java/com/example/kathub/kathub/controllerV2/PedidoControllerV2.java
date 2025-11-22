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
import com.example.kathub.kathub.model.Pedido;
import com.example.kathub.kathub.repository.PedidoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Pedido Controller V2", description = "Endpoints para gestionar pedidos - Versión 2")
public class PedidoControllerV2 {

    private final PedidoRepository pedidoRepository;

    public PedidoControllerV2(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // GET todos
    @Operation(
        summary = "Listar todos los pedidos",
        description = "Devuelve el listado completo de pedidos registrados."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    // GET por id
    @Operation(
        summary = "Obtener un pedido por ID",
        description = "Recupera los datos de un pedido específico utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Pedido no encontrado con id: " + id)
            );
        return ResponseEntity.ok(pedido);
    }

    // POST
    @Operation(
        summary = "Crear un nuevo pedido",
        description = "Registra un nuevo pedido en el sistema."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente")
    })
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoRepository.save(pedido);
        return ResponseEntity.status(201).body(nuevoPedido);
    }

    // PUT
    @Operation(
        summary = "Actualizar un pedido",
        description = "Actualiza todos los datos de un pedido existente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(
            @PathVariable Long id,
            @RequestBody Pedido detalles) {

        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Pedido no encontrado con id: " + id)
            );

        pedido.setUsuario(detalles.getUsuario());
        pedido.setProducto(detalles.getProducto());
        pedido.setFecha(detalles.getFecha());
        pedido.setEstado(detalles.getEstado());

        Pedido actualizado = pedidoRepository.save(pedido);
        return ResponseEntity.ok(actualizado);
    }

    // PATCH
    @Operation(
        summary = "Actualizar parcialmente un pedido",
        description = "Modifica solo algunos campos del pedido."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido actualizado parcialmente"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedidoParcial(
            @PathVariable Long id,
            @RequestBody Pedido detalles) {

        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Pedido no encontrado con id: " + id)
            );

        if (detalles.getUsuario() != null) {
            pedido.setUsuario(detalles.getUsuario());
        }
        if (detalles.getProducto() != null) {
            pedido.setProducto(detalles.getProducto());
        }
        if (detalles.getFecha() != null) {
            pedido.setFecha(detalles.getFecha());
        }
        if (detalles.getEstado() != null) {
            pedido.setEstado(detalles.getEstado());
        }

        Pedido actualizado = pedidoRepository.save(pedido);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @Operation(
        summary = "Eliminar un pedido por ID",
        description = "Elimina un pedido existente utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {

        if (!pedidoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Pedido no encontrado con id: " + id);
        }

        pedidoRepository.deleteById(id);
        return ResponseEntity.ok("Pedido eliminado correctamente");
    }
}