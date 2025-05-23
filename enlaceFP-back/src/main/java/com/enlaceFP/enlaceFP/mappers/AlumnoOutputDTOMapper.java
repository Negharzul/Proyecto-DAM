package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoOutputDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AlumnoOutputDTOMapper implements Function<Alumno, AlumnoOutputDTO> {

    @Override
    public AlumnoOutputDTO apply(Alumno alumno) {
        return new AlumnoOutputDTO(alumno.getId(),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getCorreoElectronico(),
                alumno.getTelefono(),
                alumno.getDireccion(),
                alumno.getAsociaciones()
                        .stream()
                        .map(asociacion->asociacion.getEmpleo().getNombreEmpleo())
                        .toList(),
                alumno.getEstudios()
                        .stream()
                        .map(asociacion->asociacion.getTitulacion().getTitulo())
                        .toList(),
                alumno.isNotificaciones());
    }
}
