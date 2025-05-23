package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.EmpleoOutputDTO;
import com.enlaceFP.enlaceFP.Models.Empleo;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmpleoOutputDTOMapper implements Function<Empleo, EmpleoOutputDTO> {
    @Override
    public EmpleoOutputDTO apply(Empleo empleo) {
        return new EmpleoOutputDTO(empleo.getId(),
                empleo.getNombreEmpleo(),
                empleo.getDescripcion(),
//                empleo.getAsociaciones()
//                        .stream()
//                        .map(asociacion-> asociacion.getAlumno().getNombre()+" "+asociacion.getAlumno().getApellidos())
//                        .collect(Collectors.toList()),
                empleo.getTitulacionesEmpleo()
                        .stream()
                        .map(titulacion ->titulacion.getTitulacion().getTitulo())
                        .collect(Collectors.toList()),
                empleo.getEmpresa() != null ? empleo.getEmpresa().getId() : null,
                empleo.getEmpresa() != null ? empleo.getEmpresa().getNombre() : null);
    }
}
