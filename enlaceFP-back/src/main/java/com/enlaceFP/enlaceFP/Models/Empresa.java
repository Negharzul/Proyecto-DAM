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

    private String correoElectronico;

    private String descripcion;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "empresa", fetch = FetchType.EAGER)
    private List<Empleo> empleosOfertados;
}
