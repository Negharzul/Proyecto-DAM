package com.enlaceFP.enlaceFP.DTOs;

import java.util.List;

public record EmpleoInputDTO (
        String nombreEmpleo,
        String descripcion,
        List<Long> titulacionesExigidas,
        Long empresaId
) {
}
