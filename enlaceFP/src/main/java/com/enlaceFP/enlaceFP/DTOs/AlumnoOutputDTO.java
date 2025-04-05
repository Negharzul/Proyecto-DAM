package com.enlaceFP.enlaceFP.DTOs;

import lombok.Builder;

import java.util.Map;

@Builder
public record AlumnoOutputDTO(
        Long id,
        String nombre,
        String apellidos,
        String email,
        Map<Long,String> empleos,
        Map<Long,String> titulos
){
}
