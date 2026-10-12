package com.pdv.pdv_backend.vendedores.repository;

import com.pdv.pdv_backend.vendedores.entity.TurnoVendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoVendedorRepository extends JpaRepository<TurnoVendedor, Long> {
    List<TurnoVendedor> findByHoraSalidaIsNullAndHoraEntradaBefore(LocalDateTime limite);
    Optional<TurnoVendedor> findByVendedorIdAndHoraSalidaIsNull(Long vendedorId);
    List<TurnoVendedor> findBySucursalIdAndHoraSalidaIsNull(Long sucursalId);
    List<TurnoVendedor> findByHoraSalidaIsNull();

}



