package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Repositories.AlumnoRepository;
import com.enlaceFP.enlaceFP.mappers.AlumnoDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final AlumnoDTOMapper alumnoDTOMapper;

    public Alumno obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    public List<Alumno> obtenerAlumnos() {
        return alumnoRepository.findAll();
    }

    public void eliminarAlumnoPorId(Long id) {
        if(alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado alumno con ID: " + id);
        }
    }

    public Alumno actualizarAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno crearAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }


}
