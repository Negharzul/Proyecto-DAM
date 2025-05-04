package com.enlaceFP.enlaceFP.DTOs;

import java.util.List;

public record EmpleoOutputDTO(
        Long id,
        String nombreEmpleo,
        String descripcion,
        List<String> Alumnos,
        List<String> titulacionesExigidas,
        String empresa
) {
}
