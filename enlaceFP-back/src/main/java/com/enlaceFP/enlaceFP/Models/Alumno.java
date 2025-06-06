package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alumno extends Usuario{

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "alumno",orphanRemoval = true)
    private List<AlumnoEmpleo> asociaciones;

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "alumno",orphanRemoval = true,fetch = FetchType.EAGER)
    private List<AlumnoTitulacion> estudios;

    @Builder
    public Alumno(Long id, String nombre, String apellidos, String dni, LocalDateTime fechaRegistro, String password, String telefono, String direccion, boolean enabled, boolean accountNonExpired, boolean credentialNonExpired, boolean accountNonLocked, String correoElectronico, Role role, List<AlumnoEmpleo> asociaciones, List<AlumnoTitulacion> estudios) {
        super(id, nombre, apellidos, dni, fechaRegistro, password, telefono,  enabled, accountNonExpired, credentialNonExpired, accountNonLocked, correoElectronico, role);
        this.asociaciones = asociaciones;
        this.estudios = estudios;
    }

    private boolean notificaciones;

    @PrePersist
    protected void alCrear(){
        enabled=true;
        accountNonExpired=true;
        credentialNonExpired=true;
        accountNonLocked=true;
        role=Role.builder().id(Role.ROLE_ALUMNO).build();
        fechaRegistro=LocalDateTime.now();
        notificaciones=false;
    }


}
