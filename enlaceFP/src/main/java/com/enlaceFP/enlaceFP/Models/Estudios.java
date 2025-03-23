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
public class Estudios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "estudiosOfertados", fetch = FetchType.EAGER)
    private ArrayList<Grupo> grupos;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "estudiosExigidos", fetch = FetchType.EAGER)
    private ArrayList<Empleo> empleos;
}
