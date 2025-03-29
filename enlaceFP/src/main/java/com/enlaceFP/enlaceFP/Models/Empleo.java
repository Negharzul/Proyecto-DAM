package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Empleo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "empleo")
    private List<AlumnoEmpleo> asociaciones;

    @ManyToOne
    @JoinColumn(name = "estudios_id")
    private Titulacion estudiosExigidos;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "empleo")
    private List<TitulacionEmpleo> titulacionesEmpleo;
}
