package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.EmpresaDTO;
import com.enlaceFP.enlaceFP.Models.Empresa;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmpresaDTOMapper implements Function<Empresa, EmpresaDTO>{

    @Override
    public EmpresaDTO apply(Empresa empresa) {
        return new EmpresaDTO(empresa.getId(),
                empresa.getNombre(),
                empresa.getDescripcion(),
                empresa.getCorreoElectronico(),
                empresa.getEmpleosOfertados()
                        .stream()
                        .map(empleo -> empleo.getNombreEmpleo())
                        .collect(Collectors.toList()));
    }
}
