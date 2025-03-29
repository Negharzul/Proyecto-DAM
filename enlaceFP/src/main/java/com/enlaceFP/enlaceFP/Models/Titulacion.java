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
public class Titulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "titulacion")
    private List<AlumnoTitulacion> estudios;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "titulacion")
    private List<TitulacionEmpleo> titulacionesEmpleo;

}
