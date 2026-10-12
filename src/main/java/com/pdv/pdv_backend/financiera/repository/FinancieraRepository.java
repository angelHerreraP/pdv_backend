package com.pdv.pdv_backend.financiera.repository;

import com.pdv.pdv_backend.financiera.entity.Financiera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancieraRepository extends JpaRepository<Financiera, Long> {
    Optional<Financiera> findByNombre(String nombre);
    List<Financiera> findByActiva(boolean active);
}
