package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alumno extends Usuario{

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "alumno")
    private List<AlumnoEmpleo> asociaciones;

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "alumno")
    private List<AlumnoTitulacion> estudios;

    @Builder

    public Alumno(Long id, String nombre, String apellidos, LocalDateTime fechaRegistro, String password, boolean enabled, boolean accountNonExpired, boolean credentialNonExpired, boolean accountNonLocked, String correoElectronico, Role role, List<AlumnoEmpleo> asociaciones, List<AlumnoTitulacion> estudios) {
        super(id, nombre, apellidos, fechaRegistro, password, enabled, accountNonExpired, credentialNonExpired, accountNonLocked, correoElectronico, role);
        this.asociaciones = asociaciones;
        this.estudios = estudios;
    }
}
