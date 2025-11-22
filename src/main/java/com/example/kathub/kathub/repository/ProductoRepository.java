package com.example.kathub.kathub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kathub.kathub.model.Producto;
import com.example.kathub.kathub.model.Usuario;

@Repository
public interface  ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Usuario> findByEmail(String email);

    // Verificar si ya existe un usuario con ese email
    boolean existsByEmail(String email);
}
