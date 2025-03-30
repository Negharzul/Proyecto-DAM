package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.EmpleoDTO;
import com.enlaceFP.enlaceFP.Models.Empleo;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmpleoDTOMapper implements Function<Empleo, EmpleoDTO> {
    @Override
    public EmpleoDTO apply(Empleo empleo) {
        return new EmpleoDTO(empleo.getId(),
                empleo.getNombreEmpleo(),
                empleo.getDescripcion(),
                empleo.getAsociaciones()
                        .stream()
                        .map(asociacion-> asociacion.getAlumno().getNombre()+" "+asociacion.getAlumno().getApellidos())
                        .collect(Collectors.toList()),
                empleo.getTitulacionesEmpleo()
                        .stream()
                        .map(titulacion ->titulacion.getTitulacion().getTitulo())
                        .collect(Collectors.toList()),
                empleo.getEmpresa() != null ? empleo.getEmpresa().getNombre() : null);
    }
}
