package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.ProfesorInputDTO;
import com.enlaceFP.enlaceFP.Models.Profesor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfesorInputDTOMapper implements Function<ProfesorInputDTO, Profesor> {

    @Override
    public Profesor apply(ProfesorInputDTO profesorInputDTO) {
        return Profesor.builder()
                .nombre(profesorInputDTO.nombre())
                .apellidos(profesorInputDTO.apellidos())
                .correoElectronico(profesorInputDTO.email())
                .build();
    }
}
