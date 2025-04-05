package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profesor extends Usuario{

@Builder
    public Profesor(Long id, String nombre, String apellidos, LocalDateTime fechaRegistro, String correoElectronico, Rol rol) {
        super(id, nombre, apellidos, fechaRegistro, correoElectronico, rol);
    }
}
