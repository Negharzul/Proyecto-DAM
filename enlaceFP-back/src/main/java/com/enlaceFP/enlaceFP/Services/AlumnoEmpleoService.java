package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.AlumnoEmpleo;
import com.enlaceFP.enlaceFP.Models.AlumnoTitulacion;
import com.enlaceFP.enlaceFP.Repositories.AlumnoEmpleoRepository;
import com.enlaceFP.enlaceFP.Repositories.AlumnoTitulacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AlumnoEmpleoService {

    private final AlumnoEmpleoRepository alumnoEmpleoRepository;


    public AlumnoEmpleo obtenerRelacionPorId(Long id) {
        return alumnoEmpleoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AlumnoEmpleo no encontrado"));
    }

    public void eliminarRelacionPorId(Long id) {
        if(alumnoEmpleoRepository.existsById(id)) {
            alumnoEmpleoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrada relacion con ID: " + id);
        }
    }

    public AlumnoEmpleo crearRelacion(AlumnoEmpleo relacion) {
        return alumnoEmpleoRepository.save(relacion);
    }
}
