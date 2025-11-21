package com.example.kathub.kathub.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.kathub.kathub.repository.ProductoRepository;
import com.example.kathub.kathub.model.Producto;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //Listamos el catalogo
    public List<Producto> obtenerCatalogo() {
        return productoRepository.findAll();
    }

    //obtenemos todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    //obtienemos un producto por id(opcional)
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    //obtenemos un producto por id. lo vuelve null si no existe
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    //guardamos un producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    //saber si un producto existe por id
    public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }

    //eliminamos un producto por id
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public void descontarStock(Long id, int cantidad) {
        Producto producto = obtenerPorId(id);
        if (producto != null && producto.getStock() >= cantidad) {
            producto.setStock(producto.getStock() - cantidad);
            productoRepository.save(producto);
        }
    }
}

