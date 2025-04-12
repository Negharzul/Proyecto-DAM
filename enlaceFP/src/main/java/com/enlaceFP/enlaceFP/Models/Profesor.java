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

    public Profesor(Long id, String nombre, String apellidos, LocalDateTime fechaRegistro, String password, boolean enabled, boolean accountNonExpired, boolean credentialNonExpired, boolean accountNonLocked, String correoElectronico, Role role) {
        super(id, nombre, apellidos, fechaRegistro, password, enabled, accountNonExpired, credentialNonExpired, accountNonLocked, correoElectronico, role);
    }
}
