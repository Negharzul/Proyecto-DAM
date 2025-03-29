package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Repositories.ProfesorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;


    public Profesor obtenerProfesorPorId(Long id) {
        return profesorRepository.findById(id).orElseThrow(() -> new RuntimeException("profesor no encontrado"));
    }

    public List<Profesor> obtenerProfesores() {
        return profesorRepository.findAll();
    }

    public void eliminarProfesorPorId(Long id) {
        if(profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado profesor con ID: " + id);
        }
    }

    public Profesor actualizarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public Profesor crearProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
}
