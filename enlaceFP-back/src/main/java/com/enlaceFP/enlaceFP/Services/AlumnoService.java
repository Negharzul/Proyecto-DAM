package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Repositories.AlumnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public void eliminarAlumnoPorId(Long id) {
        if(alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
            alumnoRepository.flush();
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
        if(alumno.getPassword()!=null) alumno.setPassword(passwordEncoder.encode(alumno.getPassword()));
        else alumno.setPassword(passwordEncoder.encode(alumno.getDni()));
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
        if(alumno.getTelefono()!=null)alumnoModificado.setTelefono(alumno.getTelefono());
        if(alumno.getDni()!=null)alumnoModificado.setDni(alumno.getDni());
        //if(alumno.getEstudios()!=null)alumnoModificado.setEstudios(new ArrayList<>(alumno.getEstudios()));

        return alumnoRepository.save(alumnoModificado);
    }

    public  void cambiarNotificaciones(boolean cambiar,Alumno alumno){
        alumno.setNotificaciones(cambiar);
        alumnoRepository.save(alumno);
    }


}
