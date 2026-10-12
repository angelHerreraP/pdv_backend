package com.pdv.pdv_backend.financiera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "financiera")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Financiera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "activa", nullable = false)
    private boolean activa = true;
}
