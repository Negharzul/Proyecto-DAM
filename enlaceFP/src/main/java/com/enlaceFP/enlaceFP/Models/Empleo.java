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
public class Empleo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "alumno_seleccionado_id")
    private Alumno alumnoSeleccionado;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "empleo", fetch = FetchType.EAGER)
    private ArrayList<Asociacion> asociaciones;

    @ManyToOne
    @JoinColumn(name = "estudios_id")
    private Estudios estudiosExigidos;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
