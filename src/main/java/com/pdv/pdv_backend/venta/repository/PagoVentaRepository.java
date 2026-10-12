package com.pdv.pdv_backend.venta.repository;

import com.pdv.pdv_backend.venta.constants.MetodoPago;
import com.pdv.pdv_backend.venta.entity.PagoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoVentaRepository extends JpaRepository<PagoVenta, Long> {
    List<PagoVenta> findByVentaId(Long ventaId);
    List<PagoVenta> findByMetodo(MetodoPago metodo);

}
