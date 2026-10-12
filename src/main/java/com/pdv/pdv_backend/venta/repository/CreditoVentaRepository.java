package com.pdv.pdv_backend.venta.repository;

import com.pdv.pdv_backend.venta.entity.CreditoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoVentaRepository extends JpaRepository<CreditoVenta, Long> {
    Optional<CreditoVenta> findByVentaId(Long ventaId);
    List<CreditoVenta> findByFinancieraId(Long financieraId);
    Optional<CreditoVenta> findByCodigoPago(String codigoPago);
}
