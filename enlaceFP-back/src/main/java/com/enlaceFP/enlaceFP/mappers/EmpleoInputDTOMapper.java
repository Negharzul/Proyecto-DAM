package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.EmpleoInputDTO;
import com.enlaceFP.enlaceFP.Models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class EmpleoInputDTOMapper implements Function<EmpleoInputDTO, Empleo> {
    @Override
    public Empleo apply(EmpleoInputDTO empleoInputDTO) {
        List<TitulacionEmpleo> titulacionesExigidas = empleoInputDTO.titulacionesExigidas() != null ?
                new ArrayList<>(empleoInputDTO.titulacionesExigidas()
                        .stream()
                        .map(id -> TitulacionEmpleo.builder()
                                .titulacion(Titulacion.builder().id(id).build())
                                .build())
                        .toList())
                : null;

        return Empleo.builder()
                .nombreEmpleo(empleoInputDTO.nombreEmpleo())
                .descripcion(empleoInputDTO.descripcion())
                .empresa(Empresa.builder().id(empleoInputDTO.empresaId()).build())
//               .titulacionesEmpleo(empleoInputDTO.titulacionesExigidas()!=null ? empleoInputDTO.titulacionesExigidas()
//                        .stream()
//                        .map( id->TitulacionEmpleo
//                                .builder()
//                                .titulacion(Titulacion
//                                        .builder()
//                                        .id(id)
//                                        .build())
//                                .build())
//                        .toList()
//                        :null)
                .titulacionesEmpleo(titulacionesExigidas)
                .build();


    }


}
