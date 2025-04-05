package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Titulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "titulacion")
    private List<AlumnoTitulacion> estudios;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "titulacion")
    private List<TitulacionEmpleo> titulacionesEmpleo;

}
