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
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String descripcion;

    private String telefono;

    private String correoElectronico;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "empresa", fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Empleo> empleosOfertados;
}
