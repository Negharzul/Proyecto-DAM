package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Alumno extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @OneToOne
    @JoinColumn(name = "empleo_conseguido_id")
    private Empleo empleoConseguido;

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "alumno", fetch = FetchType.EAGER)
    private ArrayList<Asociacion> asociaciones;
}
