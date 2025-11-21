package com.example.kathub.kathub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kathub.kathub.model.MetodoPago;
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
    
}
