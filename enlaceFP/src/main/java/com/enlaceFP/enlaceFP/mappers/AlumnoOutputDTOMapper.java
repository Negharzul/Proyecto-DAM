package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoOutputDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AlumnoOutputDTOMapper implements Function<Alumno, AlumnoOutputDTO> {

    @Override
    public AlumnoOutputDTO apply(Alumno alumno) {
        return new AlumnoOutputDTO(alumno.getId(),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getCorreoElectronico(),
                alumno.getAsociaciones()
                        .stream()
                        .collect(Collectors.toMap(
                                        asociacion->asociacion.getAlumno().getId(),
                                        asociacion->asociacion.getAlumno().getNombre()
                                )),
                alumno.getEstudios()
                        .stream()
                        .collect(Collectors.toMap(
                                asociacion->asociacion.getTitulacion().getId(),
                                asociacion->asociacion.getTitulacion().getTitulo()
                        )));
    }
}
