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

    public static final Long ROLE_ALUMNO=1L;
    public static final Long ROLE_PROFESOR=2L;
    public static final Long ROLE_ADMIN=3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreRole;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "role")
    private List<RolPermiso> rolPermisos;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "role")
    private List<Usuario> usuarios;
}
