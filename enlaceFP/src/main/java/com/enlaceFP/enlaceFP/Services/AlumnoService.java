package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Repositories.AlumnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final PasswordEncoder passwordEncoder;

    public Alumno obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Alumno no encontrado"));
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
        if(alumnoRepository.findById(alumno.getId()).isEmpty()){
            throw new NoSuchElementException("Alumno no encontrado");
        }
        return alumnoRepository.save(alumno);
    }

    public Alumno crearAlumno(Alumno alumno) {
        alumno.setPassword(passwordEncoder.encode(alumno.getPassword()));
        return alumnoRepository.save(alumno);
    }

    public void crearAlumnosEnBloque(List<Alumno> alumnos){
        alumnoRepository.saveAll(alumnos);
    }

    public Alumno modificarAlumno(Alumno alumno,Long alumnoId){
        Alumno alumnoModificado = alumnoRepository.findById(alumnoId).orElseThrow(() -> new NoSuchElementException("Alumno no encontrado"));
        if(alumno.getNombre()!=null)alumnoModificado.setNombre(alumno.getNombre());
        if(alumno.getApellidos()!=null)alumnoModificado.setApellidos(alumno.getApellidos());
        if(alumno.getCorreoElectronico()!=null)alumnoModificado.setCorreoElectronico(alumno.getCorreoElectronico());
        return alumnoRepository.save(alumnoModificado);
    }



}
