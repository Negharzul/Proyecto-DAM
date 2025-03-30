package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/Alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("/{idAlumno}")
    public ResponseEntity<AlumnoDTO> getAlumno(@PathVariable Long idAlumno) {

        try {
            AlumnoDTO alumno = alumnoService.obtenerAlumnoPorId(idAlumno);
            return ResponseEntity.ok(alumno);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/NuevoAlumno")
    public ResponseEntity<AlumnoDTO> crearAlumno(@RequestBody Alumno alumno){
        if(alumno==null){
            return ResponseEntity.badRequest().build();
        }

        AlumnoDTO alumnoDTO=alumnoService.crearAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDTO);

    }

    @PatchMapping("/Modificar")
    public ResponseEntity<AlumnoDTO> modificarAlumno(@RequestBody Alumno alumno){
        if(alumno==null){
            return ResponseEntity.badRequest().build();
        }
        alumnoService.modificarAlumno(alumno);


        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("Borrar/{idAlumno}")
    public ResponseEntity<AlumnoDTO> borrarAlumno(@PathVariable Long idAlumno){
        try{
            alumnoService.eliminarAlumnoPorId(idAlumno);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }

    }


}
