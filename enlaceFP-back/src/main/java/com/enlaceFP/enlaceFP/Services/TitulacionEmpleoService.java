package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.AlumnoTitulacion;
import com.enlaceFP.enlaceFP.Models.TitulacionEmpleo;
import com.enlaceFP.enlaceFP.Repositories.TitulacionEmpleoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TitulacionEmpleoService {

    private final TitulacionEmpleoRepository titulacionEmpleoRepository;


    public TitulacionEmpleo obtenerRelacionPorId(Long id) {
        return titulacionEmpleoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("relacion no encontrada"));
    }

    public void eliminarRelacionPorId(Long id) {
        if(titulacionEmpleoRepository.existsById(id)) {
            titulacionEmpleoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrada relacion con ID: " + id);
        }
    }

    public TitulacionEmpleo crearRelacion(TitulacionEmpleo relacion) {
        return titulacionEmpleoRepository.save(relacion);
    }
}
