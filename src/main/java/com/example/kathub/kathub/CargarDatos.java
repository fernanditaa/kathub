package com.example.kathub.kathub;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.kathub.kathub.model.Producto;
import com.example.kathub.kathub.repository.MetodoPagoRepository;
import com.example.kathub.kathub.repository.PedidoRepository;
import com.example.kathub.kathub.repository.ProductoRepository;
import com.example.kathub.kathub.repository.VentaRepository;
import com.example.kathub.kathub.service.UsuarioService;

@Configuration
public class CargarDatos {

    @Bean
    CommandLineRunner initDatabase(UsuarioService usuarioService,
                                    ProductoRepository productoRepository,
                                    PedidoRepository pedidoRepository,
                                    VentaRepository ventaRepository,
                                    MetodoPagoRepository metodoPagoRepository) {
        return args -> {

           
            System.out.println("Iniciando carga de datos inicial...");
               
            if (productoRepository.count() > 0){
                System.out.println("Datos ya cargados");
                return;
            }
            

            List<Producto> productos = List.of(
                new Producto("TurboAbuela", "Personaje del anime 'DanDaDan'", "20 cm", 20000.0, 5, "Amigurumi",  "http://localhost:8080/images/turboabuela.webp"),
                new Producto("Recuerdo en forma de corazón", "Souvenir por docena para Matrimonio", "5 cm", 13000.0, 10, "Recuerdo",  "http://localhost:8080/images/corazon.webp"),
                new Producto("Boina", "Tejido en lana hipoalergénica", "Talla adulta", 28000.0, 7, "Gorro",  "http://localhost:8080/images/boina.webp"),
                new Producto("Zoey", "Personaje de la película 'Las guerreras del K-pop'", "18 cm", 15000.0, 5, "Amigurumi",  "http://localhost:8080/images/zoei.webp"),
                new Producto("Chupete tejido", "Recuerdo tejido en forma de chupete", "6 cm", 9000.0, 12, "Recuerdo",  "http://localhost:8080/images/chupete.webp"),
                new Producto("Gorro estilo Slouchy", "Gorro de lana con patrón de trenzas", "Talla adulta", 18000.0, 6, "Gorro", "http://localhost:8080/images/gorro2.webp"),
                new Producto("Coraje", "Amigurimi de 'Coraje, el perro cobarde'", "22 cm", 25000.0, 4, "Amigurumi", "http://localhost:8080/images/coraje.webp"),
                new Producto("Vestidos", "Mini vestidos para recuerdos", "7 cm", 7000.0, 15, "Recuerdo", "http://localhost:8080/images/vestidos.webp"),
                new Producto("Gorro de gatito", "Gorro tejido con orejas de gato", "Talla infantil", 12000.0, 8, "Gorro", "http://localhost:8080/images/gorrogato.webp"),
                new Producto("Rumi", "Personaje de 'Las guerreras del K-pop'", "18 cm", 15000.0, 5, "Amigurumi", "http://localhost:8080/images/rumi.webp"),
                new Producto("Sombreros", "Mini sombreros tejidos para recuerdos", "6 cm", 7000.0, 12, "Recuerdo", "http://localhost:8080/images/sombreros.webp"),
                new Producto("Gorro Gohan", "Gorro inspirado en Dragon Ball Z", "Talla infantil", 10000.0, 10, "Gorro", "http://localhost:8080/images/gorrogohan.webp"),
                new Producto("Mira", "Personaje de 'Las guerreras del K-pop'", "18 cm", 15000.0, 5, "Amigurumi", "http://localhost:8080/images/mira.webp"),
                new Producto("Moños", "Pequeños moños tejidos para recuerdos", "4 cm", 9000.0, 20, "Recuerdo", "http://localhost:8080/images/moño.webp"),
                new Producto("Gorro candy", "Gorro multicolor estilo 'Candy' con pompón XL", "Talla adulta", 15000.0, 7, "Gorro", "http://localhost:8080/images/gorrounicornio.webp"),
                new Producto("Kurama", "Personaje de la serie 'Naruto'", "25 cm", 35000.0, 3, "Amigurumi", "https://kathub.onrender.com/images/kurama.webp"),
                new Producto("Cruces", "Recuerdos en forma de cruz", "7 cm", 9000.0, 15, "Recuerdo", "http://localhost:8080/images/cruz.webp"),
                new Producto("Gorro", "Gorro rosa con orejeras y tres pompones", "Talla infantil", 8000.0, 10, "Gorro", "http://localhost:8080/images/gorro1.webp"),
                new Producto("Harry Styles", "Amigurimi personalizado del cantante Harry Styles", "24 cm", 25000.0, 5, "Amigurumi", "http://localhost:8080/images/styles.webp"),
                new Producto("Body", "Recuerdo tejido con forma de body de bebé", "6 cm", 7000.0, 12, "Recuerdo", "http://localhost:8080/images/body.webp"),
                new Producto("Gorro turbante", "Turbante gris con brillos y flor", "Talla bebé", 10000.0, 8, "Gorro", "http://localhost:8080/images/turbante.webp")
            );

            productos.forEach(productoRepository::save);
            System.out.println("Productos cargados " + productos.size());

        };
    }
    
}
