package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AlumnoDTOMapper implements Function<Alumno, AlumnoDTO> {

    @Override
    public AlumnoDTO apply(Alumno alumno) {
        return new AlumnoDTO(alumno.getId(),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getRol() != null ? alumno.getRol().getRol() : null,
                alumno.getCorreoElectronico(),
                alumno.getAsociaciones()
                        .stream()
                        .map(asociacion -> asociacion.getEmpleo().getDescripcion())
                        .collect(Collectors.toList()),
                alumno.getEstudios()
                        .stream()
                        .map(estudios -> estudios.getTitulacion().getTitulo())
                        .collect(Collectors.toList()));
    }
}
