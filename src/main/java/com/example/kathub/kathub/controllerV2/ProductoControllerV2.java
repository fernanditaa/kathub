package com.example.kathub.kathub.controllerV2;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.kathub.kathub.model.Producto;
import com.example.kathub.kathub.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v2/productos")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Producto Controller V2", description = "Endpoints for managing productos - Version 2")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Operation(
        summary= "Listar todos los productos",
        description = "Devuelve el catalogo completo de productos disponibles."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }
    @Operation(
        summary= "Obtener un producto por ID",
        description = "Recupera los detalles de un producto específico utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Producto no encontrado con id: " + id)
            );
        return ResponseEntity.ok(producto);
    }
    
    @Operation(
        summary= "crear un producto",
        description = "Registra un nuevo producto en el catalogo."
    )

    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    })

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity.status(201).body(nuevoProducto);
    }

    @Operation(
        summary= "Actualizar un producto",
        description = "Actualiza los detalles de un producto existente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto detalles) {

                Producto producto = productoService.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Producto no encontrado con id: " + id)
            );

        producto.setNombre(detalles.getNombre());
        producto.setDescripcion(detalles.getDescripcion());
        producto.setPrecio(detalles.getPrecio());
        producto.setStock(detalles.getStock());

        Producto actualizado = productoService.save(producto);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(
        summary= "Obtener un producto por ID",
        description = "Recupera los detalles de un producto específico utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Producto> actualizarProductoParcial(
                    @PathVariable Long id,
                    @RequestBody Producto detalles) {

                        Producto producto = productoService.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Producto no encontrado con id: " + id)
            );

        if (detalles.getNombre() != null) {
            producto.setNombre(detalles.getNombre());
        }
        if (detalles.getDescripcion() != null) {
            producto.setDescripcion(detalles.getDescripcion());
        }
        if (detalles.getPrecio() != null) {
            producto.setPrecio(detalles.getPrecio());
        }
        if (detalles.getStock() != null) {
            producto.setStock(detalles.getStock());
        }

        Producto actualizado = productoService.save(producto);
        return ResponseEntity.ok(actualizado);

    }

    @Operation(
        summary= "Obtener un producto por ID",
        description = "Recupera los detalles de un producto específico utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        if(!productoService.existsById(id)) {
            throw new RecursoNoEncontradoException("Producto no encontrado con id: " + id);
        }
        productoService.deleteById(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
    
}
