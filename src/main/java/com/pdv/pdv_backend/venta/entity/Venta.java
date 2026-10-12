package com.pdv.pdv_backend.venta.entity;

import com.pdv.pdv_backend.autorizations.entity.CodigoAutorizacion;
import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import com.pdv.pdv_backend.vendedores.entity.Vendedor;
import com.pdv.pdv_backend.venta.constants.EstadoVenta;
import com.pdv.pdv_backend.venta.constants.MetodoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    private LocalDateTime fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "codigo_autorizacion_id")
    private CodigoAutorizacion codigoAutorizacion;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVenta estado = EstadoVenta.CERRADA;
}
