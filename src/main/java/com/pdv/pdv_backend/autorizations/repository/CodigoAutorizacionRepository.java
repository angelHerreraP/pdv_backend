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

    //@Query(
    //        "SELECT COUNT(*) > 0 FROM codigo_autorizacion WHERE action = :action AND usado = false AND expira_en > :ahora AND(parametros ->> 'ventaId')::bigint = :ventaId"
    //)
    //boolean existsVigentePara(@Param("action") String action, @Param("ventaId") Long ventaId, @Param("ahora") LocalDateTime ahora);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CodigoAutorizacion c WHERE c.codigo = :codigo")
    Optional<CodigoAutorizacion> findByCodigoForUpdate(@Param("codigo") String codigo);
}
