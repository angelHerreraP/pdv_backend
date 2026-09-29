package com.pdv.pdv_backend.producto.entity;

import com.pdv.pdv_backend.categoria.entity.Categoria;
import com.pdv.pdv_backend.marca.entity.Marca;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "precio_publico", nullable = false)
    private BigDecimal precioPublico;

    @Column(name = "requiere_serie", nullable = false)
    private Boolean requiereSerie = false;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;


}
