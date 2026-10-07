package com.pdv.pdv_backend.venta.repository;

import com.pdv.pdv_backend.venta.constants.MetodoPago;
import com.pdv.pdv_backend.venta.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findBySucursalId(Long sucursalId);
    List<Venta> findByVendedorId(Long vendedorId);
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Venta> findBySucursalIdAndFechaBetween(Long sucursalId, LocalDateTime inicio, LocalDateTime fin);
    List<Venta> findBySucursalIdAndMetodoPago(Long sucursalId, MetodoPago metodoPago);



}
