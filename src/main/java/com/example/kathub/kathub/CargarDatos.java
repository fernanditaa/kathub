package com.example.kathub.kathub;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.kathub.kathub.model.MetodoPago;
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

           
                System.out.println("Datos ya cargados, no se realizará ninguna acción.");
               
            
            
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
            p.setCategoria("Amigurumi");
            p.setMedida(" ");
            p.setPrecio(19.99);
            p.setStock(5);
            

            productoRepository.save(p);
            System.out.println("Producto guardado: " + p.getNombre());


            List<Producto> productos = List.of(
                new Producto("TurboAbuela", "Personaje del anime 'DanDaDan'", "20 cm", 20000.0, 5, "Amigurumi"),
                new Producto("Recuerdo en forma de corazón", "Souvenir por docena para Matrimonio", "5 cm", 13000.0, 10, "Recuerdo"),
                new Producto("Boina", "Tejido en lana hipoalergénica", "Talla adulta", 28000.0, 7, "Gorro"),
                new Producto("Zoey", "Personaje de la película 'Las guerreras del K-pop'", "18 cm", 15000.0, 5, "Amigurumi"),
                new Producto("Chupete tejido", "Recuerdo tejido en forma de chupete", "6 cm", 9000.0, 12, "Recuerdo"),
                new Producto("Gorro estilo Slouchy", "Gorro de lana con patrón de trenzas", "Talla adulta", 18000.0, 6, "Gorro"),
                new Producto("Coraje", "Amigurimi de 'Coraje, el perro cobarde'", "22 cm", 25000.0, 4, "Amigurumi"),
                new Producto("Vestidos", "Mini vestidos para recuerdos", "7 cm", 7000.0, 15, "Recuerdo"),
                new Producto("Gorro de gatito", "Gorro tejido con orejas de gato", "Talla infantil", 12000.0, 8, "Gorro"),
                new Producto("Rumi", "Personaje de 'Las guerreras del K-pop'", "18 cm", 15000.0, 5, "Amigurumi"),
                new Producto("Sombreros", "Mini sombreros tejidos para recuerdos", "6 cm", 7000.0, 12, "Recuerdo"),
                new Producto("Gorro Gohan", "Gorro inspirado en Dragon Ball Z", "Talla infantil", 10000.0, 10, "Gorro"),
                new Producto("Mira", "Personaje de 'Las guerreras del K-pop'", "18 cm", 15000.0, 5, "Amigurumi"),
                new Producto("Moños", "Pequeños moños tejidos para recuerdos", "4 cm", 9000.0, 20, "Recuerdo"),
                new Producto("Gorro candy", "Gorro multicolor estilo 'Candy' con pompón XL", "Talla adulta", 15000.0, 7, "Gorro"),
                new Producto("Kurama", "Personaje de la serie 'Naruto'", "25 cm", 35000.0, 3, "Amigurumi"),
                new Producto("Cruces", "Recuerdos en forma de cruz", "7 cm", 9000.0, 15, "Recuerdo"),
                new Producto("Gorro", "Gorro rosa con orejeras y tres pompones", "Talla infantil", 8000.0, 10, "Gorro"),
                new Producto("Harry Styles", "Amigurimi personalizado del cantante Harry Styles", "24 cm", 25000.0, 5, "Amigurumi"),
                new Producto("Body", "Recuerdo tejido con forma de body de bebé", "6 cm", 7000.0, 12, "Recuerdo"),
                new Producto("Gorro turbante", "Turbante gris con brillos y flor", "Talla bebé", 10000.0, 8, "Gorro")
            );
 

            // Guardar todos los productos
            productos.forEach(productoRepository::save);

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
