package com.example.kathub.kathub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kathub.kathub.model.Pedido;
import com.example.kathub.kathub.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:5173")// para abrir despues con el front
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List <Pedido> verPedidos() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        pedido.setFecha(java.time.LocalDate.now());
        pedido.setEstado("PENDIENTE");
        return pedidoRepository.save(pedido);
    }
    
}
