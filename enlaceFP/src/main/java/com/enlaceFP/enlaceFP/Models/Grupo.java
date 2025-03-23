package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "grupo", fetch = FetchType.EAGER)
    private List<Alumno> alumno;

    @ManyToOne
    @JoinColumn(name = "estudios_id")
    private Estudios estudiosOfertados;
}
