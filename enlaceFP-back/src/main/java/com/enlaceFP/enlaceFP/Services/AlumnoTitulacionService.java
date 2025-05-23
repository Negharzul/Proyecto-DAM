package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.AlumnoTitulacion;
import com.enlaceFP.enlaceFP.Repositories.AlumnoRepository;
import com.enlaceFP.enlaceFP.Repositories.AlumnoTitulacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AlumnoTitulacionService {

    private final AlumnoTitulacionRepository alumnoTitulacionRepository;


    public AlumnoTitulacion obtenerRelacionPorId(Long id) {
        return alumnoTitulacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Alumno no encontrado"));
    }

    public List<AlumnoTitulacion> obtenerRelacionesPorAlumno(Alumno alumno){
        return alumnoTitulacionRepository.findByAlumno(alumno);
    }

    @Transactional
    public void eliminarRelacionesIdAlumno(Long idAlumno){
        alumnoTitulacionRepository.deleteByAlumnoId(idAlumno);

    }

    public void eliminarRelacionPorId(Long id) {
        if(alumnoTitulacionRepository.existsById(id)) {
            alumnoTitulacionRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrada relacion con ID: " + id);
        }
    }

    public AlumnoTitulacion crearRelacion(AlumnoTitulacion relacion) {
        return alumnoTitulacionRepository.save(relacion);
    }
}
