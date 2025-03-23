package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Profesor;

import java.util.List;

public interface ProfesorService {

    public Profesor obtenerPorId(Long id);
    public List<Profesor> obtenerTodos();
    public void eliminarPorId(Long id);
    public Profesor actualizar(Profesor profesor);
    public Profesor crear(Profesor profesor);
}
