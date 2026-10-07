package com.pdv.pdv_backend.producto.repository;

import com.pdv.pdv_backend.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //List<Producto> findByCategoriaIdAndMarcaId(Long categoriaId, Long marcaId);
    boolean existsByMarcaId(Long marcaId);
    boolean existsByCategoriaId(Long categoriaId);
}
