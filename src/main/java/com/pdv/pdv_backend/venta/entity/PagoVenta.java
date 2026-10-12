package com.pdv.pdv_backend.venta.entity;

import com.pdv.pdv_backend.venta.constants.MetodoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pago_venta")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PagoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo", nullable = false, length = 15)
    private MetodoPago metodo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(length = 50)
    private String referencia;

}
