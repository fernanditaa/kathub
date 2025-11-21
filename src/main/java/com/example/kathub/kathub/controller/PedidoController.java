package com.example.kathub.kathub.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.kathub.kathub.model.MetodoPago;
import com.example.kathub.kathub.model.Pedido;
import com.example.kathub.kathub.model.Producto;
import com.example.kathub.kathub.model.Usuario;
import com.example.kathub.kathub.repository.MetodoPagoRepository;
import com.example.kathub.kathub.repository.ProductoRepository;
import com.example.kathub.kathub.repository.UsuarioRepository;
import com.example.kathub.kathub.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:5173")// para abrir despues con el front
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @GetMapping
    public List <Pedido> verPedidos() {
        return pedidoService.obtenerPedidos();
    }

    //listamos los pedidos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> listarPorUsuario(@PathVariable Long usuarioId){
        if(!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.notFound().build();
        }
        List<Pedido> pedidos = pedidoService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(pedidos);
    }


    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody CrearPedidoRequest request){

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElse(null);
        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }

        Producto producto = productoRepository.findById(request.getProductoId()).orElse(null);
        if(producto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto no encontrado");
        }

        MetodoPago metodoPago = metodoPagoRepository
                    .findById(request.getMetodoPagoId())
                    .orElse(null);

        if(metodoPago == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Método de pago no encontrado");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setProducto(producto);
        pedido.setFecha(LocalDate.now());
        pedido.setEstado("Procesando");
        pedido.setMetodoPago(metodoPago);

        Pedido nuevoPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<?> actualizarPedido(@PathVariable Long pedidoId, @RequestBody ActualizazrPedidoRequest request) {
        Pedido pedido = pedidoService.findById(pedidoId).orElse(null);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }
        Producto producto = productoRepository.findById(request.getProductoId()).orElse(null);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto no encontrado");
        }
        MetodoPago metodoPago = metodoPagoRepository.findById(request.getMetodoPagoId()).orElse(null);
        if (metodoPago == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Método de pago no encontrado");
        }

        pedido.setUsuario(usuario);
        pedido.setProducto(producto);
        pedido.setMetodoPago(metodoPago);
        pedido.setEstado(request.getEstado());

        Pedido actualizado = pedidoService.save(pedido);
        return ResponseEntity.ok(actualizado);
    }
    @PatchMapping("/{pedidoId}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long pedidoId, @RequestBody CambiarEstadoRequest request) {
        Pedido pedido = pedidoService.findById(pedidoId).orElse(null);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
            }
        pedido.setEstado(request.getEstado());
        Pedido actualizado = pedidoService.save(pedido);
        return ResponseEntity.ok(actualizado);
        }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long pedidoId) {
        if (!pedidoService.existsById(pedidoId)) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.deleteById(pedidoId);
        return ResponseEntity.noContent().build();
    }

    public static class CrearPedidoRequest {
        private Long usuarioId;
        private Long productoId;
        private Long metodoPagoId;

        public Long getUsuarioId() {
            return usuarioId;
        }
        
        public void setUsuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
        }

        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public Long getMetodoPagoId() {
            return metodoPagoId;
        }
        public void setMetodoPagoId(Long metodoPagoId) {
            this.metodoPagoId = metodoPagoId;
        }
    }

        public static class CambiarEstadoRequest {
            private String estado;

            public String getEstado() {
                return estado;
            }

            public void setEstado(String estado) {
                this.estado = estado;
            }
        }

    public static class ActualizazrPedidoRequest {
        private Long usuarioId;
        private Long productoId;
        private Long metodoPagoId;
        private String estado;

        public Long getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
        }

        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public Long getMetodoPagoId() {
            return metodoPagoId;
        }

        public void setMetodoPagoId(Long metodoPagoId) {
            this.metodoPagoId = metodoPagoId;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }

}