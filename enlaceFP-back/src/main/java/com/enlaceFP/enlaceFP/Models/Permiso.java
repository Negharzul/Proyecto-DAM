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
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "permiso")
    private List<RolPermiso> rolPermisos;
}
