package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoInputDTO;
import com.enlaceFP.enlaceFP.DTOs.AlumnoOutputDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.AlumnoTitulacion;
import com.enlaceFP.enlaceFP.Models.Titulacion;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import com.enlaceFP.enlaceFP.Services.AlumnoTitulacionService;
import com.enlaceFP.enlaceFP.Services.TitulacionService;
import com.enlaceFP.enlaceFP.mappers.AlumnoInputDTOMapper;
import com.enlaceFP.enlaceFP.mappers.AlumnoOutputDTOMapper;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final AlumnoTitulacionService alumnoTitulacionService;
    private final AlumnoOutputDTOMapper alumnoOutputDTOMapper;
    private final AlumnoInputDTOMapper alumnoInputDTOMapper;
    private final TitulacionService titulacionService;
    private EntityManager entityManager;

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


    @PostMapping()
    public ResponseEntity<Long> crearAlumno(@RequestBody AlumnoInputDTO alumnoInputDTO){
        if(alumnoInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Alumno alumno =alumnoInputDTOMapper.apply(alumnoInputDTO);
        alumnoService.crearAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno.getId());

    }

@Transactional
    @PatchMapping("/Modificar/{alumnoId}")
    public ResponseEntity<AlumnoOutputDTO> modificarAlumno(@RequestBody AlumnoInputDTO alumnoInputDTO,@PathVariable Long alumnoId){
        if(alumnoInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
    if(alumnoInputDTO.titulos()!=null) {
        alumnoTitulacionService.eliminarRelacionesIdAlumno(alumnoId);
        entityManager.flush();
        entityManager.clear();
    }

        Alumno alumno=alumnoInputDTOMapper.apply(alumnoInputDTO);
        alumnoService.modificarAlumno(alumno,alumnoId);


        for(Long id:alumnoInputDTO.titulos()){
            System.out.println(id);
            alumnoTitulacionService.crearRelacion(AlumnoTitulacion.builder()
                .titulacion(Titulacion.builder().id(id).build())
                .alumno(Alumno.builder().id(alumnoId).build())
                .build());
        }

        return ResponseEntity.ok().build();
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

    /*
     *
     *  Metodos de la relacion con Titulacion
     *
     *
     */


    @PostMapping("/titulo/{idAlumno}")
    @Transactional
    public ResponseEntity<?> crearAsociacionesConTitulaciones(@PathVariable Long idAlumno,@RequestBody List<Long> idsTitulaciones){


        for(Long id:idsTitulaciones){
            var relacion= AlumnoTitulacion.builder()
                    .titulacion(Titulacion.builder()
                            .id(id)
                            .build())
                    .alumno(Alumno.builder()
                            .id(idAlumno)
                            .build())
                    .build();

            alumnoTitulacionService.crearRelacion(relacion);
        }


        return ResponseEntity.ok().build();
    }
}
