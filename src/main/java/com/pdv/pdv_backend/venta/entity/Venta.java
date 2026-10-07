package com.pdv.pdv_backend.venta.entity;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "folio_credito")
    private String folioCredito;

    private LocalDateTime fecha;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVenta estado = EstadoVenta.CERRADA;
}
