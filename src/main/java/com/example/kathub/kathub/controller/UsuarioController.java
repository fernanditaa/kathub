package com.example.kathub.kathub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import com.example.kathub.kathub.service.UsuarioService;

import com.example.kathub.kathub.model.Usuario;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> ListarTodos() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        if(usuarioService.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está en uso");
        }
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.findByEmailAndContrasena(
            request.getEmail(),
            request.getContrasena()
        
        );

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {

        return usuarioService.findById(id)
                .map(usuario -> {
                    if (usuarioDetalles.getNombre() != null) {
                        usuario.setNombre(usuarioDetalles.getNombre());
                    }
                    if (usuarioDetalles.getApellido() != null){
                        usuario.setApellido(usuarioDetalles.getApellido());
                    }
                    if (usuarioDetalles.getTelefono() != null){
                        usuario.setTelefono(usuarioDetalles.getTelefono());
                    }
                    if (usuarioDetalles.getDireccion() != null){
                        usuario.setDireccion(usuarioDetalles.getDireccion());
                    }
                    if (usuarioDetalles.getEmail() != null){
                        usuario.setEmail(usuarioDetalles.getEmail());
                    }
                    if (usuarioDetalles.getContrasena() != null){
                        usuario.setContrasena(usuarioDetalles.getContrasena());
                    }

                    Usuario usuarioActualizado = usuarioService.save(usuario);
                    return ResponseEntity.ok(usuarioActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
            if (!usuarioService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            usuarioService.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        }

        public static class LoginRequest {
            private String email;
            private String contrasena;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getContrasena() {
                return contrasena;
            }

            public void setContrasena(String contrasena) {
                this.contrasena = contrasena;
            }
        }
}
