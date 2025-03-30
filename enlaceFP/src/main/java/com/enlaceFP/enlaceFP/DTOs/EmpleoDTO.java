package com.enlaceFP.enlaceFP.DTOs;

import java.util.List;

public record EmpleoDTO(
        Long id,
        String nombreEmpleo,
        String descripcion,
        List<String> Alumnos,
        List<String> titulacionesExigidas,
        String Empresa
) {
}
