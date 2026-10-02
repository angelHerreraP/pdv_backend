package com.pdv.pdv_backend.compra.repository;

import com.pdv.pdv_backend.compra.entity.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
    List<DetalleCompra> findByProductoId(Long productoId);
    List<DetalleCompra> findByCompraId(Long compraId);
}
