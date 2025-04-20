package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoInputDTO;
import com.enlaceFP.enlaceFP.Models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class AlumnoInputDTOMapper implements Function<AlumnoInputDTO, Alumno> {
    @Override
    public Alumno apply(AlumnoInputDTO alumnoInputDTO) {
        Alumno alumno = Alumno.builder()
                .id(alumnoInputDTO.id())
                .nombre(alumnoInputDTO.nombre())
                .apellidos(alumnoInputDTO.apellidos())
                .correoElectronico(alumnoInputDTO.email())
                .build();


        List<AlumnoEmpleo> empleos = alumnoInputDTO.empleos() != null ? alumnoInputDTO.empleos()
                .stream()
                .map(id -> AlumnoEmpleo.builder()
                        .alumno(alumno)
                        .empleo(Empleo.builder().id(id).build())
                        .build())
                .toList()
                : null;

        List<AlumnoTitulacion> estudios = alumnoInputDTO.titulos() != null ? alumnoInputDTO.titulos()
                .stream()
                .map(id -> AlumnoTitulacion.builder()
                        .alumno(alumno)
                        .titulacion(Titulacion.builder().id(id).build())
                        .build())
                .toList()
                : null;

                /*
               String telefono,
        String direccion,
        String dni,
         */
        return Alumno.builder()
                .id(alumnoInputDTO.id())
                .nombre(alumnoInputDTO.nombre())
                .apellidos(alumnoInputDTO.apellidos())
                .correoElectronico(alumnoInputDTO.email())
                .telefono(alumnoInputDTO.telefono())
                .direccion(alumnoInputDTO.direccion())
                .dni(alumnoInputDTO.dni())
                .asociaciones(empleos)
                .estudios(estudios)
                .build();
    }
}
