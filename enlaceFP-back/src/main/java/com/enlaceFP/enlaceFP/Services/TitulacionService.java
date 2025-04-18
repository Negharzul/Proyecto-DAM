package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Empresa;
import com.enlaceFP.enlaceFP.Models.Titulacion;
import com.enlaceFP.enlaceFP.Repositories.TitulacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TitulacionService {

    private final TitulacionRepository titulacionRepository;

    public Titulacion obtenerTitulacionPorId(Long id) {
        return titulacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Titulacion no encontrada"));
    }

    public Titulacion obtenerTitulacionPorNombre(String nombre){
        return titulacionRepository.findByTitulo(nombre).orElseThrow(() -> new RuntimeException("Titulacion no encontrada"));
    }

    public List<Titulacion> obtenerTitulaciones() {
        return titulacionRepository.findAll();
    }

    public void eliminarTitulacionPorId(Long id) {
        if(titulacionRepository.existsById(id)) {
            titulacionRepository.deleteById(id);
        } else {
            throw new RuntimeException("No encontrada titulación con ID: " + id);
        }
    }

    public Titulacion actualizarTitulacion(Titulacion titulacion) {
        return titulacionRepository.save(titulacion);
    }

    public Titulacion crearTitulacion(Titulacion titulacion) {
        return titulacionRepository.save(titulacion);
    }

}
