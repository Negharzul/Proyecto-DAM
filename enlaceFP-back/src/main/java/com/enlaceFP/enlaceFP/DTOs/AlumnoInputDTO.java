package com.enlaceFP.enlaceFP.DTOs;

import java.util.List;

public record AlumnoInputDTO(
        Long id,
        String nombre,
        String apellidos,
        String email,
        String telefono,
        String direccion,
        String dni
) {
}
