package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.EmpresaInputDTO;
import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Models.Empresa;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmpresaInputDTOMapper implements Function<EmpresaInputDTO,Empresa> {

    @Override
    public Empresa apply(EmpresaInputDTO empresaInputDTO) {
        return Empresa.builder()
                .nombre(empresaInputDTO.nombre())
                .descripcion(empresaInputDTO.descripcion())
                .correoElectronico(empresaInputDTO.correoElectronico())
                .empleosOfertados(empresaInputDTO.empleosOfertados() != null ? empresaInputDTO.empleosOfertados()
                .stream()
                .map(id -> Empleo.builder().id(id).build())
                .toList()
                : null)
                .build();
    }
}
