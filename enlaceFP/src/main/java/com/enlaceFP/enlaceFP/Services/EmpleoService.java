package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Repositories.EmpleoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class EmpleoService {

    private final EmpleoRepository empleoRepository;


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

    public Empleo modificarEmpleo(Empleo empleo, Long idEmpleo){
        Empleo empleoModificado = empleoRepository.findById(idEmpleo).orElseThrow(() -> new NoSuchElementException("Empleo no encontrado"));
        if(empleo.getNombreEmpleo()!=null)empleoModificado.setNombreEmpleo(empleo.getNombreEmpleo());
        if(empleo.getDescripcion()!=null)empleoModificado.setDescripcion(empleo.getDescripcion());
        if(empleo.getAsociaciones()!=null)empleoModificado.setAsociaciones(empleo.getAsociaciones());
        if(empleo.getTitulacionesEmpleo()!=null)empleoModificado.setTitulacionesEmpleo(empleo.getTitulacionesEmpleo());
        if(empleo.getEmpresa()!=null)empleoModificado.setEmpresa(empleo.getEmpresa());
        return empleoRepository.save(empleoModificado);
    }
}
