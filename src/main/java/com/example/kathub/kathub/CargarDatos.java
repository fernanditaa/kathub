package com.example.kathub.kathub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.kathub.kathub.model.Pedido;
import com.example.kathub.kathub.model.Producto;
import com.example.kathub.kathub.model.Usuario;
import com.example.kathub.kathub.repository.ProductoRepository;
import com.example.kathub.kathub.repository.UsuarioRepository;
import com.example.kathub.kathub.repository.PedidoRepository;

@Configuration
public class CargarDatos {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, ProductoRepository productoRepository, PedidoRepository pedidoRepository) {
        return args -> {
            
            Usuario u = new Usuario();
            u.setNombre("Fernanda");
            u.setApellido("Arraño");
            u.setTelefono("930357394");
            u.setDireccion("calle 1");
            u.setEmail("fer.@gmail.com");
            u.setContrasena("123456789");

            usuarioRepository.save(u);
            System.out.println("Usuario guardado: " + u.getNombre());

            Producto p = new Producto();
            p.setNombre("Chopper");
            p.setDescripcion("Amigurimio tejido de algodón");
            p.setPrecio(19.99);
            p.setStock(5);

            productoRepository.save(p);
            System.out.println("Producto guardado: " + p.getNombre());


            Pedido compra = new Pedido();
            compra.setUsuario(u);
            compra.setProducto(p);
            compra.setFecha(java.time.LocalDate.now());
            compra.setEstado("PENDIENTE");

            pedidoRepository.save(compra);
            System.out.println("Pedido guardado: " + compra.getId());
        };
    }
    
}
