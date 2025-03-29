package com.enlaceFP.enlaceFP.DTOs;

import java.util.List;

public record AlumnoDTO(
        Long id,
        String nombre,
        String apellidos,
        String rol,
        String email,
        List<String> empleos
){
}
