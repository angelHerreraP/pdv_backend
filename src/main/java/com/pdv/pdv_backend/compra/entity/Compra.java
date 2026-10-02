package com.pdv.pdv_backend.compra.entity;

import com.pdv.pdv_backend.producto.entity.Producto;
import com.pdv.pdv_backend.proveedor.entity.Proveedor;
import com.pdv.pdv_backend.sucursal.entity.Sucursal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    private LocalDateTime fecha;

}
