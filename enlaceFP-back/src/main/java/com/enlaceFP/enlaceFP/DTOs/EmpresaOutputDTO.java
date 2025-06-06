package com.enlaceFP.enlaceFP.DTOs;

import lombok.Builder;

import java.util.List;

@Builder
public record EmpresaOutputDTO(
        Long id,
        String nombre,
        String descripcion,
        String email,
        String telefono,
        List<String> empleosOfertados
) {

}
