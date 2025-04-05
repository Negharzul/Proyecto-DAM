package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.EmpleoInputDTO;
import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Models.Empresa;
import com.enlaceFP.enlaceFP.Models.Titulacion;
import com.enlaceFP.enlaceFP.Models.TitulacionEmpleo;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmpleoInputDTOMapper implements Function<EmpleoInputDTO, Empleo> {
    @Override
    public Empleo apply(EmpleoInputDTO empleoInputDTO) {
        return Empleo.builder()
                .nombreEmpleo(empleoInputDTO.nombreEmpleo())
                .descripcion(empleoInputDTO.descripcion())
                .empresa(Empresa.builder().id(empleoInputDTO.empresaId()).build())
                .titulacionesEmpleo(empleoInputDTO.titulacionesExigidas()
                        .stream()
                        .map( id->TitulacionEmpleo
                                .builder()
                                .titulacion(Titulacion
                                        .builder()
                                        .id(id)
                                        .build())
                                .build())
                        .toList())
                .build();
    }
}
