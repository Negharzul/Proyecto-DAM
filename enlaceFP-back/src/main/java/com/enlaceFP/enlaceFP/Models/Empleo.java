package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Empleo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreEmpleo;

    private String descripcion;

    private LocalDate fechaPublicacion=LocalDate.now();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "empleo",orphanRemoval = true)
    private List<AlumnoEmpleo> asociaciones;

    @ManyToOne
    @JoinColumn(name = "empresa_id",nullable = false)
    private Empresa empresa;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "empleo",orphanRemoval = true)
    private List<TitulacionEmpleo> titulacionesEmpleo;
}
