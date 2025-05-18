package com.enlaceFP.enlaceFP.DTOs;

import java.util.List;

public record EmpleoInputDTO (
        String nombreEmpleo,
        String descripcion,
        Long empresaId,
        List<Long> titulacionesExigidas

) {
}
