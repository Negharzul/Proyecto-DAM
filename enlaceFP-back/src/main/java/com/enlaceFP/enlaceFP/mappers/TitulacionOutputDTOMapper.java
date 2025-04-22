package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.TitulacionDTO;
import com.enlaceFP.enlaceFP.Models.Titulacion;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TitulacionOutputDTOMapper implements Function<Titulacion, TitulacionDTO> {

    @Override
    public TitulacionDTO apply(Titulacion titulacion) {
        return TitulacionDTO.builder()
                .id(titulacion.getId())
                .nombre(titulacion.getTitulo())
                .build();
    }
}
