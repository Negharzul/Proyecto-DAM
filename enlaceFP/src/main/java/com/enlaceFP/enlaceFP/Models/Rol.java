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
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rolName;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "rol", fetch = FetchType.EAGER)
    private List<RolPermiso> rolPermisos;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "rol", fetch = FetchType.EAGER)
    private List<Usuario> usuarios;
}
