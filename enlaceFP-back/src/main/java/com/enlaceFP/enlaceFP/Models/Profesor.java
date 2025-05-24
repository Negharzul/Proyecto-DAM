package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Profesor extends Usuario{

    @Builder
    public Profesor(Long id, String nombre, String apellidos, String dni, LocalDateTime fechaRegistro, String password, String telefono, String direccion, boolean enabled, boolean accountNonExpired, boolean credentialNonExpired, boolean accountNonLocked, String correoElectronico, Role role) {
        super(id, nombre, apellidos, dni, fechaRegistro, password, telefono, enabled, accountNonExpired, credentialNonExpired, accountNonLocked, correoElectronico, role);
    }

    @PrePersist
    protected void alCrear(){
        enabled=true;
        accountNonExpired=true;
        credentialNonExpired=true;
        accountNonLocked=true;
        role=Role.builder().id(Role.ROLE_PROFESOR).build();
        fechaRegistro=LocalDateTime.now();
    }
}
