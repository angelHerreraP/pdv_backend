package com.pdv.pdv_backend.vendedores.repository;

import com.pdv.pdv_backend.vendedores.entity.HorarioVendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface HorarioVendedorRepository extends JpaRepository<HorarioVendedor, Long> {
    Optional<HorarioVendedor> findByVendedorId(Long vendedorId);
}
