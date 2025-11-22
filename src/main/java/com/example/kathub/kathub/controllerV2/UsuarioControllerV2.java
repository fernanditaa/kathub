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
import com.example.kathub.kathub.model.Usuario;
import com.example.kathub.kathub.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Usuario Controller V2", description = "Endpoints for managing usuarios - Version 2")
public class UsuarioControllerV2 {

    @Autowired
    private final UsuarioService usuarioRepository;

    public UsuarioControllerV2(UsuarioService usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Operación exitosa"),
        @ApiResponse(responseCode="500", description="Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarioRepository.findAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Usuario no encontrado con id: " + id)
            );
        return ResponseEntity.ok(usuario);
    }

    // ============================
    // POST /api/v2/usuarios
    // ============================
    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Registra un nuevo usuario en el sistema."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    })
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

    // ============================
    // PUT /api/v2/usuarios/{id}
    // ============================
    @Operation(
        summary = "Actualizar un usuario",
        description = "Actualiza todos los datos de un usuario existente."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario detalles) {

        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Usuario no encontrado con id: " + id)
            );

        usuario.setNombre(detalles.getNombre());
        usuario.setApellido(detalles.getApellido());
        usuario.setEmail(detalles.getEmail());
        usuario.setTelefono(detalles.getTelefono());
        usuario.setDireccion(detalles.getDireccion());
        usuario.setContrasena(detalles.getContrasena());

        Usuario actualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(actualizado);
    }

    // ============================
    // PATCH /api/v2/usuarios/{id}
    // ============================
    @Operation(
        summary = "Actualizar parcialmente un usuario",
        description = "Modifica solo algunos campos del usuario."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado parcialmente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioParcial(
            @PathVariable Long id,
            @RequestBody Usuario detalles) {

        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() ->
                new RecursoNoEncontradoException("Usuario no encontrado con id: " + id)
            );

        if (detalles.getNombre() != null) {
            usuario.setNombre(detalles.getNombre());
        }
        if (detalles.getApellido() != null) {
            usuario.setApellido(detalles.getApellido());
        }
        if (detalles.getEmail() != null) {
            usuario.setEmail(detalles.getEmail());
        }
        if (detalles.getTelefono() != null) {
            usuario.setTelefono(detalles.getTelefono());
        }
        if (detalles.getDireccion() != null) {
            usuario.setDireccion(detalles.getDireccion());
        }
        if (detalles.getContrasena() != null) {
            usuario.setContrasena(detalles.getContrasena());
        }

        Usuario actualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(actualizado);
    }

    // ============================
    // DELETE /api/v2/usuarios/{id}
    // ============================
    @Operation(
        summary = "Eliminar un usuario por ID",
        description = "Elimina un usuario existente utilizando su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Usuario no encontrado con id: " + id);
        }

        usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }


}
