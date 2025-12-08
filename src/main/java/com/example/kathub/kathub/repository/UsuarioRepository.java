package com.example.kathub.kathub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kathub.kathub.model.Usuario;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailAndContrasena(String email, String contrasena);

    Usuario findByEmail(String email);
    boolean existsByEmail(String email);

    boolean existsByRol(String rol);
//regitrarUsuario
    
}
