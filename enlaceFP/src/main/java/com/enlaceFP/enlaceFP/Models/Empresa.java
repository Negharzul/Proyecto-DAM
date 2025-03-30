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
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String correoElectronico;

    private String descripcion;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "empresa", fetch = FetchType.EAGER)
    private ArrayList<Empleo> empleosOfertados;
}
