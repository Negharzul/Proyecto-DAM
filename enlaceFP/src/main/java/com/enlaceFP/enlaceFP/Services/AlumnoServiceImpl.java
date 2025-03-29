package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Repositories.AlumnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoServiceImpl {

    private final AlumnoRepository alumnoRepository;

    public Alumno obtenerPorId(Long id) {
        return alumnoRepository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }

    public void eliminarPorId(Long id) {
        if(alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado alumno con ID: " + id);
        }
    }

    public Alumno actualizar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno crear(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }


}
