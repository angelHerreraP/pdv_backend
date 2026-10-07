package com.pdv.pdv_backend.venta.repository;

import com.pdv.pdv_backend.venta.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long>{
    List<DetalleVenta> findByVentaId(Long ventaId);
    List<DetalleVenta> findByVentaIdIn(Collection<Long> ventaIds);
}
