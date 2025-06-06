package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Models.Empresa;
import com.enlaceFP.enlaceFP.Repositories.EmpleoRepository;
import jakarta.transaction.Transactional;
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

    public List<Empleo> obtenerEmpleosPorNombreEmpresa(String nombreEmpresa){
        return empleoRepository.findByNombreEmpresa(nombreEmpresa);
    }

    public List<Empleo> obtenerEmpleos() {
        return empleoRepository.findAll();
    }

    @Transactional
    public void eliminarEmpleoPorId(Long id) {
        Empleo empleo = empleoRepository.findById(id).orElseThrow();


        Empresa empresa = empleo.getEmpresa();
        if (empresa != null) {
            empresa.getEmpleosOfertados().remove(empleo);
            empleo.setEmpresa(null);
        }


            empleoRepository.deleteById(id);
    }

    public Empleo actualizarEmpleo(Empleo empleo) {
        return empleoRepository.save(empleo);
    }

    public Empleo crearEmpleo(Empleo empleo) {
        return empleoRepository.save(empleo);
    }

    @Transactional
    public void crearEmpleosEnBloque(List<Empleo> empleos){
        empleoRepository.saveAll(empleos);
    }


    public Empleo modificarEmpleo(Empleo empleo, Long idEmpleo,Empresa empresa){
        Empleo empleoModificado = empleoRepository.findById(idEmpleo).orElseThrow(() -> new NoSuchElementException("Empleo no encontrado"));
        if(empleo.getNombreEmpleo()!=null)empleoModificado.setNombreEmpleo(empleo.getNombreEmpleo());
        if(empleo.getDescripcion()!=null)empleoModificado.setDescripcion(empleo.getDescripcion());
        //if(empleo.getAsociaciones()!=null)empleoModificado.setAsociaciones(empleo.getAsociaciones());
        //if(empleo.getTitulacionesEmpleo()!=null)empleoModificado.setTitulacionesEmpleo(empleo.getTitulacionesEmpleo());
        if(empleo.getEmpresa()!=null){
            empleoModificado.setEmpresa(empresa);
        }
        return empleoRepository.save(empleoModificado);
    }
}
