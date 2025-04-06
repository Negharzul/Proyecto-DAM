package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Repositories.ProfesorRepository;
import com.enlaceFP.enlaceFP.mappers.ProfesorOutputDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ProfesorOutputDTOMapper profesorOutputDTOMapper;


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

    public Profesor modificarProfesor(Profesor profesor, Long idProfesor){
        Profesor profesorModificado = profesorRepository.findById(idProfesor).orElseThrow(() -> new NoSuchElementException("Profesor no encontrado"));
        if(profesor.getNombre()!=null)profesorModificado.setNombre(profesor.getNombre());
        if(profesor.getApellidos()!=null)profesorModificado.setApellidos(profesor.getApellidos());
        if(profesor.getCorreoElectronico()!=null)profesorModificado.setCorreoElectronico(profesor.getCorreoElectronico());

        return profesorRepository.save(profesorModificado);
    }
}
