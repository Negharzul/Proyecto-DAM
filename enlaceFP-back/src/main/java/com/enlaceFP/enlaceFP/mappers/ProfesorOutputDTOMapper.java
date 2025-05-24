package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.ProfesorOutputDTO;
import com.enlaceFP.enlaceFP.Models.Profesor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfesorOutputDTOMapper implements Function<Profesor, ProfesorOutputDTO> {

    @Override
    public ProfesorOutputDTO apply(Profesor profesor) {
        return new ProfesorOutputDTO(profesor.getId(),
                profesor.getNombre(),
                profesor.getApellidos(),
                profesor.getRole() != null ? profesor.getRole().getNombreRole() : null,
                profesor.getCorreoElectronico(),
                profesor.getTelefono(),
                profesor.getDni());
    }
}
