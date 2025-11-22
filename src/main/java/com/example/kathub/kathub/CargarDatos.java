package com.example.kathub.kathub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.kathub.kathub.model.MetodoPago;
import com.example.kathub.kathub.model.Pedido;
import com.example.kathub.kathub.model.Producto;
import com.example.kathub.kathub.model.Usuario;
import com.example.kathub.kathub.model.Ventas;
import com.example.kathub.kathub.repository.MetodoPagoRepository;
import com.example.kathub.kathub.repository.PedidoRepository;
import com.example.kathub.kathub.repository.ProductoRepository;
import com.example.kathub.kathub.repository.VentaRepository;
import com.example.kathub.kathub.service.UsuarioService;

@Configuration
public class CargarDatos {

    @Bean
    CommandLineRunner initDatabase(UsuarioService usuarioRepository,
                                    ProductoRepository productoRepository,
                                    PedidoRepository pedidoRepository,
                                    VentaRepository ventaRepository,
                                    MetodoPagoRepository metodoPagoRepository) {
        return args -> {

            if(usuarioRepository.count() > 0) {
                System.out.println("Datos ya cargados, no se realizará ninguna acción.");
                return;
            }
            
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
            p.setDescripcion("Amigurimi tejido de algodón");
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

            MetodoPago mp = new MetodoPago();
            mp.setNombre("Tarjeta de Crédito");
            metodoPagoRepository.save(mp);

            Ventas venta = new Ventas();
            venta.setUsuario(u);
            venta.setMetodoPago(mp);
            venta.setTotal(19.99);
            venta.setFecha(java.time.LocalDate.now());
            venta.setEstado("COMPLETADO");
            venta.setProducto(p);
            ventaRepository.save(venta);
            System.out.println("Venta guardada: " + venta.getId());

        };
    }
    
}
