package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Asociacion;
import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Repositories.AlumnoRepository;
import com.enlaceFP.enlaceFP.Repositories.AsociacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final AsociacionRepository asociacionRepository;

    @Override
    public Alumno obtenerPorId(Long id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if(alumno.isPresent()) return alumno.get();
        return new Alumno();
    }

    @Override
    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }

    @Override
    public void eliminarPorId(Long id) {
        if(alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado alumno con ID: " + id);
        }
    }

    @Override
    public Alumno actualizar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno crear(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void seleccionarOferta(Alumno alumno, Empleo empleo){

        if (alumno == null || alumno.getId() == null) {
            throw new RuntimeException("Alumno no valido.");
        }
        if (empleo == null || empleo.getId() == null) {
            throw new RuntimeException("Empleo no válido.");
        }

        Asociacion asociacion = new Asociacion();
        asociacion.setAlumno(alumno);
        asociacion.setEmpleo(empleo);
        asociacionRepository.save(asociacion);

        if (alumno.getAsociaciones() == null) {
            alumno.setAsociaciones(new ArrayList<>());
        }
        alumno.getAsociaciones().add(asociacion);

        actualizar(alumno);
    }
}
