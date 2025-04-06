package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Empresa;
import com.enlaceFP.enlaceFP.Repositories.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;


    public Empresa obtenerEmpleoPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }

    public List<Empresa> obtenerEmpleos() {
        return empresaRepository.findAll();
    }

    public void eliminarEmpresaPorId(Long id) {
        if(empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrado empleo con ID: " + id);
        }
    }

    public Empresa actualizarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa crearEmpresa(Empresa empleo) {
        return empresaRepository.save(empleo);
    }

    public Empresa modificarEmpresa(Empresa empresa, Long idEmpleo){
        Empresa empresaModificada = empresaRepository.findById(idEmpleo).orElseThrow(() -> new NoSuchElementException("Empresa no encontrada"));
        if(empresa.getNombre()!=null)empresaModificada.setNombre(empresa.getNombre());
        if(empresa.getDescripcion()!=null)empresaModificada.setDescripcion(empresa.getDescripcion());
        if(empresa.getCorreoElectronico()!=null)empresaModificada.setCorreoElectronico(empresa.getCorreoElectronico());
        if(empresa.getEmpleosOfertados()!=null)empresaModificada.setEmpleosOfertados(empresa.getEmpleosOfertados());

        return empresaRepository.save(empresaModificada);
    }
}
