package com.pdv.pdv_backend.sucursal.repository;

import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
