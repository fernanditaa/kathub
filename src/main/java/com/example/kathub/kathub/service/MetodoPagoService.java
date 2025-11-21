package com.example.kathub.kathub.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.kathub.kathub.repository.MetodoPagoRepository;
import com.example.kathub.kathub.model.MetodoPago;
import java.util.List;


@Service
public class MetodoPagoService {
    
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> obtenerMetodos() {
        return metodoPagoRepository.findAll();
    }

    public MetodoPago agreagarMetodoPago(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }
}
