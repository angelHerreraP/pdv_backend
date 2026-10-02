package com.pdv.pdv_backend.vendedores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "horario_vendedor")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class HorarioVendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Column(name = "hora_entrada_esperada", nullable = false)
    private LocalTime horaEntradaEsperada;

    @Column(name = "hora_salida_esperada", nullable = false)
    private LocalTime horaSalidaEsperada;
}
