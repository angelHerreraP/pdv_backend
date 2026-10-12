package com.pdv.pdv_backend.venta.entity;

import com.pdv.pdv_backend.financiera.entity.Financiera;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "credito_venta")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreditoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "financiera_id", nullable = false)
    private Financiera financiera;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal enganche;

    @Column(name = "pago_semanal", nullable = false, precision = 10, scale = 2)
    private BigDecimal pagoSemanal;

    @Column(name = "codigo_pago", nullable = false, length = 100)
    private String codigoPago;

}
