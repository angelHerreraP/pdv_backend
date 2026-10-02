package com.pdv.pdv_backend.inventario.repository;

import com.pdv.pdv_backend.inventario.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findBySucursalId(Long sucursalId);
    Optional<Inventario> findByProductoIdAndSucursalId(Long productoId, Long sucursalId);

    @Query("SELECT i FROM Inventario i JOIN FETCH i.producto JOIN FETCH i.sucursal WHERE i.sucursal.id = :sucursalId")
    List<Inventario> findBySucursalIdConDetalles(@Param("sucursalId") Long sucursalId);
}
