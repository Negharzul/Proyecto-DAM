package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.DTOs.AlumnoDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Repositories.AlumnoRepository;
import com.enlaceFP.enlaceFP.mappers.AlumnoDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final AlumnoDTOMapper alumnoDTOMapper;

    public AlumnoDTO obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id)
                .map(alumnoDTOMapper)
                .orElseThrow(() -> new NoSuchElementException("Alumno no encontrado"));
    }

    public List<AlumnoDTO> obtenerAlumnos() {
        return alumnoRepository.findAll()
                .stream()
                .map(alumnoDTOMapper)
                .collect(Collectors.toList());
    }

    public void eliminarAlumnoPorId(Long id) {
        if(alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado alumno con ID: " + id);
        }
    }

    public AlumnoDTO actualizarAlumno(Alumno alumno) {
        if(alumnoRepository.findById(alumno.getId()).isEmpty()){
            throw new NoSuchElementException("Alumno no encontrado");
        }
        return alumnoDTOMapper.apply(alumnoRepository.save(alumno));
    }

    public AlumnoDTO  crearAlumno(Alumno alumno) {
        return alumnoDTOMapper.apply(alumnoRepository.save(alumno));
    }

    public AlumnoDTO modificarAlumno(Alumno alumno){
        Alumno alumnoModificado = alumnoRepository.findById(alumno.getId()).orElseThrow(() -> new NoSuchElementException("Alumno no encontrado"));
        if(alumno.getNombre()!=null)alumnoModificado.setNombre(alumno.getNombre());
        if(alumno.getApellidos()!=null)alumnoModificado.setApellidos(alumno.getApellidos());
        if(alumno.getCorreoElectronico()!=null)alumnoModificado.setCorreoElectronico(alumno.getCorreoElectronico());
        return alumnoDTOMapper.apply(alumnoRepository.save(alumnoModificado));
    }



}
