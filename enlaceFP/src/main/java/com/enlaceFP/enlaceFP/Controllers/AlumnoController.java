package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoInputDTO;
import com.enlaceFP.enlaceFP.DTOs.AlumnoOutputDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import com.enlaceFP.enlaceFP.mappers.AlumnoInputDTOMapper;
import com.enlaceFP.enlaceFP.mappers.AlumnoOutputDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/Alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final AlumnoOutputDTOMapper alumnoOutputDTOMapper;
    private final AlumnoInputDTOMapper alumnoInputDTOMapper;

    @GetMapping("/{idAlumno}")
    public ResponseEntity<AlumnoOutputDTO> getAlumno(@PathVariable Long idAlumno) {

        try {
            Alumno alumno = alumnoService.obtenerAlumnoPorId(idAlumno);
            AlumnoOutputDTO alumnoOutputDTO=alumnoOutputDTOMapper.apply(alumno);
            return ResponseEntity.ok(alumnoOutputDTO);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<AlumnoOutputDTO>> getAllAlumnos(){
        List<Alumno> alumnos=alumnoService.obtenerAlumnos();
        List<AlumnoOutputDTO> alumnosOutputDTO= alumnos.stream().map(alumnoOutputDTOMapper).toList();

        return ResponseEntity.ok(alumnosOutputDTO);
    }


    @PostMapping("/NuevoAlumno")
    public ResponseEntity<Long> crearAlumno(@RequestBody AlumnoInputDTO alumnoInputDTO){
        if(alumnoInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Alumno alumno =alumnoInputDTOMapper.apply(alumnoInputDTO);
        alumnoService.crearAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno.getId());

    }

    @PatchMapping("/Modificar/{AlumnoId}")
    public ResponseEntity<AlumnoOutputDTO> modificarAlumno(@RequestBody AlumnoInputDTO alumnoInputDTO,@PathVariable Long alumnoId){
        if(alumnoInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Alumno alumno=alumnoInputDTOMapper.apply(alumnoInputDTO);
        alumnoService.modificarAlumno(alumno,alumnoId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("Borrar/{idAlumno}")
    public ResponseEntity<AlumnoOutputDTO> borrarAlumno(@PathVariable Long idAlumno){
        try{
            alumnoService.eliminarAlumnoPorId(idAlumno);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }

    }


}
