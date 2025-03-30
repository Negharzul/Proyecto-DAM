package com.enlaceFP.enlaceFP.DTOs;

import java.util.List;

public record EmpresaDTO(
        Long id,
        String nombre,
        String descripcion,
        String correoElectronico,
        List<String> empleosOfertados
) {

}
