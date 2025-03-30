package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Repositories.EmpleoRepository;
import com.enlaceFP.enlaceFP.Repositories.ProfesorRepository;
import com.enlaceFP.enlaceFP.mappers.EmpleoDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmpleoService {

    private final EmpleoRepository empleoRepository;
    private final EmpleoDTOMapper empleoDTOMapper;


    public Empleo obtenerEmpleoPorId(Long id) {
        return empleoRepository.findById(id).orElseThrow(() -> new RuntimeException("Empleo no encontrado"));
    }

    public List<Empleo> obtenerEmpleos() {
        return empleoRepository.findAll();
    }

    public void eliminarEmpleoPorId(Long id) {
        if(empleoRepository.existsById(id)) {
            empleoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado empleo con ID: " + id);
        }
    }

    public Empleo actualizarEmpleo(Empleo empleo) {
        return empleoRepository.save(empleo);
    }

    public Empleo crearEmpleo(Empleo empleo) {
        return empleoRepository.save(empleo);
    }
}
