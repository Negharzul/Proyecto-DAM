package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Repositories.ProfesorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;


    @Override
    public Profesor obtenerPorId(Long id) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if(profesor.isPresent()) return profesor.get();
        return new Profesor();
    }

    @Override
    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    @Override
    public void eliminarPorId(Long id) {
        if(profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado alumno con ID: " + id);
        }
    }

    @Override
    public Profesor actualizar(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor crear(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
}
