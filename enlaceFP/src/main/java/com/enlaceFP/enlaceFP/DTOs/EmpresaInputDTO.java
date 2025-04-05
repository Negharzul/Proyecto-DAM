package com.enlaceFP.enlaceFP.DTOs;

import lombok.Builder;

import java.util.List;

@Builder
public record EmpresaInputDTO(String nombre,
                              String descripcion,
                              String correoElectronico,
                              List<Long> empleosOfertados) {
}
