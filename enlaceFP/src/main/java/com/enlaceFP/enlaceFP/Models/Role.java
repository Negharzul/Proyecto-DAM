package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "role", fetch = FetchType.EAGER)
    private List<RolPermiso> rolPermisos;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "role", fetch = FetchType.EAGER)
    private List<Usuario> usuarios;
}
