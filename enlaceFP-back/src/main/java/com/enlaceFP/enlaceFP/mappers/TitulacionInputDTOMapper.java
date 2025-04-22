package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.TitulacionDTO;
import com.enlaceFP.enlaceFP.Models.Titulacion;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TitulacionInputDTOMapper implements Function<TitulacionDTO, Titulacion> {

    @Override
    public Titulacion apply(TitulacionDTO titulacionDTO) {
        return Titulacion.builder()
                .id(titulacionDTO.id())
                .titulo(titulacionDTO.nombre())
                .build();
    }
}
