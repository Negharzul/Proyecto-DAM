package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Empleo;

import java.util.List;

public interface AlumnoService {

    public Alumno obtenerPorId(Long id);
    public List<Alumno> obtenerTodos();
    public void eliminarPorId(Long id);
    public Alumno actualizar(Alumno alumno);
    public Alumno crear(Alumno alumno);

    /**
     *
     *
     */
    public void seleccionarOferta(Alumno alumno,Empleo empleo);

}
