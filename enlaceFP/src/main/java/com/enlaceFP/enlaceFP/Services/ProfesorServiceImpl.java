package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Repositories.ProfesorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfesorServiceImpl{

    private final ProfesorRepository profesorRepository;


    public Profesor obtenerPorId(Long id) {
        return profesorRepository.findById(id).orElseThrow(() -> new RuntimeException("profesor no encontrado"));
    }

    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    public void eliminarPorId(Long id) {
        if(profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado profesor con ID: " + id);
        }
    }

    public Profesor actualizar(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public Profesor crear(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
}
