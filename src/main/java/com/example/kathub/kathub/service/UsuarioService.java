package com.example.kathub.kathub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.kathub.kathub.model.Usuario;
import com.example.kathub.kathub.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findByEmailAndContrasena(String email, String contrasena) {
        return usuarioRepository.findByEmailAndContrasena(email, contrasena);
    }

    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
