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
public class Alumno extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade= CascadeType.ALL, mappedBy = "alumno")
    private List<AlumnoEmpleo> asociaciones;

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "alumno")
    private List<AlumnoTitulacion> estudios;
}
