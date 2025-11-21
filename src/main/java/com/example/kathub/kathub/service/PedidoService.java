package com.example.kathub.kathub.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.kathub.kathub.repository.PedidoRepository;
import com.example.kathub.kathub.model.Pedido;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> findByUsuarioId(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public Pedido save(Pedido pedido) {
        if (pedido.getFecha() == null){
            pedido.setFecha(LocalDate.now());
        }

        if (pedido.getEstado() == null) {
            pedido.setEstado("PENDIENTE");
        }
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return pedidoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}
