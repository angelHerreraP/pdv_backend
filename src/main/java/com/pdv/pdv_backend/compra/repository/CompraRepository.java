package com.pdv.pdv_backend.compra.repository;

import com.pdv.pdv_backend.compra.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    //Optional<Compra> findByProveedorIdAndFecha(Long proveedorId, LocalDateTime fecha);
    List<Compra> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Compra> findByProveedorId(Long proveedorId);
}
