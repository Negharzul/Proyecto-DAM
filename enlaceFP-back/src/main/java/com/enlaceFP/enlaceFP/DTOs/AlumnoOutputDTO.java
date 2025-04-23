package com.enlaceFP.enlaceFP.DTOs;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record AlumnoOutputDTO(
        Long id,
        String nombre,
        String apellidos,
        String email,
        String telefono,
        String direccion,
        List<String>empleos,
        List<String> titulos
){
}
