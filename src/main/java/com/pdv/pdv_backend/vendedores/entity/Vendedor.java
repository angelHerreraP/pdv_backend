package com.pdv.pdv_backend.vendedores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "vendedor")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "salario_semanal")
    private Double salarioSemanal;

    @Column(name = "dia_descanso")
    private String diaDescanso;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(nullable = false)
    private Boolean activo = true;
}
