package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoInputDTO;
import com.enlaceFP.enlaceFP.Models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class AlumnoInputDTOMapper implements Function<AlumnoInputDTO, Alumno> {
    @Override
    public Alumno apply(AlumnoInputDTO alumnoInputDTO) {
        Alumno alumno = Alumno.builder()
                .nombre(alumnoInputDTO.nombre())
                .apellidos(alumnoInputDTO.apellidos())
                .correoElectronico(alumnoInputDTO.email())
                .dni(alumnoInputDTO.dni())
                .build();
/*

        List<AlumnoEmpleo> empleos = alumnoInputDTO.empleos() != null ? alumnoInputDTO.empleos()
                .stream()
                .map(id -> AlumnoEmpleo.builder()
                        .alumno(alumno)
                        .empleo(Empleo.builder().id(id).build())
                        .build())
                .toList()
                : null;
*/
        List<AlumnoTitulacion> estudios = alumnoInputDTO.titulos() != null ?
                 new ArrayList<>(alumnoInputDTO.titulos()
                .stream()
                .map(id -> AlumnoTitulacion.builder()
                        .alumno(Alumno.builder().id(alumno.getId()).build())
                        .titulacion(Titulacion.builder().id(id).build())
                        .build())
                .toList())
                : null;



        return Alumno.builder()
                .nombre(alumnoInputDTO.nombre())
                .apellidos(alumnoInputDTO.apellidos())
                .correoElectronico(alumnoInputDTO.email())
                .telefono(alumnoInputDTO.telefono())
                .direccion(alumnoInputDTO.direccion())
                .dni(alumnoInputDTO.dni())
                .estudios(estudios)
                .build();
    }
}
