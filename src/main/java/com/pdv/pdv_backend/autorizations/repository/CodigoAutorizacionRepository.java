package com.pdv.pdv_backend.autorizations.repository;

import com.pdv.pdv_backend.autorizations.entity.CodigoAutorizacion;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CodigoAutorizacionRepository extends JpaRepository<CodigoAutorizacion, Long> {
    Optional<CodigoAutorizacion> findByCodigo(String codigo);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from CodigoAutorizacion c where c.codigo = :codigo")
    Optional<CodigoAutorizacion> findByCodigoForUpdate(@Param("codigo") String codigo);
}
