package com.enlaceFP.enlaceFP.mappers;

import com.enlaceFP.enlaceFP.DTOs.EmpresaOutputDTO;
import com.enlaceFP.enlaceFP.Models.Empresa;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmpresaOutputDTOMapper implements Function<Empresa, EmpresaOutputDTO>{

    @Override
    public EmpresaOutputDTO apply(Empresa empresa) {
        return new EmpresaOutputDTO(empresa.getId(),
                empresa.getNombre(),
                empresa.getDescripcion(),
                empresa.getCorreoElectronico(),
                empresa.getEmpleosOfertados()
                        .stream()
                        .map(empleo -> empleo.getNombreEmpleo())
                        .collect(Collectors.toList()));
    }
}
