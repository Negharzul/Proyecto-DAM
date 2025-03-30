package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.ProfesorDTO;
import com.enlaceFP.enlaceFP.Models.Profesor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfesorDTOMapper implements Function<Profesor, ProfesorDTO> {

    @Override
    public ProfesorDTO apply(Profesor profesor) {
        return new ProfesorDTO(profesor.getId(),
                profesor.getNombre(),
                profesor.getApellidos(),
                profesor.getRol() != null ? profesor.getRol().getRol() : null,
                profesor.getCorreoElectronico());
    }
}
