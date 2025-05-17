package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoOutputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpleoInputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpleoOutputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpresaOutputDTO;
import com.enlaceFP.enlaceFP.Models.*;
import com.enlaceFP.enlaceFP.Services.AlumnoEmpleoService;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import com.enlaceFP.enlaceFP.Services.EmpleoService;
import com.enlaceFP.enlaceFP.Services.MailService;
import com.enlaceFP.enlaceFP.mappers.EmpleoInputDTOMapper;
import com.enlaceFP.enlaceFP.mappers.EmpleoOutputDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/empleo")
public class EmpleoController {

    private final EmpleoService empleoService;
    private final MailService mailService;
    private final EmpleoOutputDTOMapper empleoOutputDTOMapper;
    private final EmpleoInputDTOMapper empleoInputDTOMapper;
    private final AlumnoService alumnoService;
    private final AlumnoEmpleoService alumnoEmpleoService;

    @GetMapping("/{idEmpleo}")
    public ResponseEntity<EmpleoOutputDTO> getEmpleo(@PathVariable Long idEmpleo) {

        try {
            Empleo empleo = empleoService.obtenerEmpleoPorId(idEmpleo);
            EmpleoOutputDTO empleoOutputDTO=empleoOutputDTOMapper.apply(empleo);
            return ResponseEntity.ok(empleoOutputDTO);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/empleosAlumno/{idAlumno}")
    public ResponseEntity<List<EmpleoOutputDTO>> getEmpleosByAlumnoId(@PathVariable Long idAlumno) {

        Alumno alumno = alumnoService.obtenerAlumnoPorId(idAlumno);
        List<Empleo> empleos=alumno.getAsociaciones()
                .stream()
                .map(AlumnoEmpleo::getEmpleo)
                .toList();
        List<EmpleoOutputDTO> empleosOutputDTO= empleos.stream().map(empleoOutputDTOMapper).toList();

        return ResponseEntity.ok(empleosOutputDTO);
    }

    @GetMapping("/posiblesEmpleos")
    public ResponseEntity<List<EmpleoOutputDTO>> getPosiblesEmpleos(@AuthenticationPrincipal Alumno alumno) {

        List<Titulacion> titulosAlumno=alumno.getEstudios()
                .stream()
                .map(AlumnoTitulacion::getTitulacion)
                .toList();


        //TODO mover logica de filtrado a capa de servicio.
        List<Empleo> empleos=empleoService.obtenerEmpleos();
        List<Empleo> empleosAlumno=empleos
                .stream()
                .filter(empleo->empleo
                        .getAsociaciones()
                        .stream()
                        .noneMatch(asociacion->asociacion
                                .getAlumno()
                                .equals(alumno)))
                .filter(empleo->empleo
                        .getTitulacionesEmpleo()
                        .stream()
                        .anyMatch(asociacion->titulosAlumno.contains(asociacion.getTitulacion())))
                .toList();

        List<EmpleoOutputDTO> empleosOutputDTO= empleosAlumno.stream().map(empleoOutputDTOMapper).toList();
        return ResponseEntity.ok(empleosOutputDTO);
    }

    @GetMapping()
    public ResponseEntity<List<EmpleoOutputDTO>> getAllEmpleos(){
        List<Empleo> alumnos=empleoService.obtenerEmpleos();
        List<EmpleoOutputDTO> alumnosOutputDTO= alumnos.stream().map(empleoOutputDTOMapper).toList();

        return ResponseEntity.ok(alumnosOutputDTO);
    }


    @PostMapping("/NuevoEmpleo")
    public ResponseEntity<Map<String,Long>> crearEmpleo(@RequestBody EmpleoInputDTO empleoInputDTO){
        if(empleoInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Empleo empleo =empleoInputDTOMapper.apply(empleoInputDTO);
        Empleo empleoPersistido=empleoService.crearEmpleo(empleo);
        Map<String,Long> response= new HashMap<>();
        response.put("id",empleoPersistido.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/mail/{idEmpleo}")
    public ResponseEntity<AlumnoOutputDTO> avisarAlumnosInteresados(@RequestBody List<Alumno> alumnos, @PathVariable Long idEmpleo){
        mailService.correoRecomendacionEmpleo(alumnos,idEmpleo);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/enviarCandidatos/{idEmpleo}")
    public ResponseEntity<EmpresaOutputDTO> enviarCandidatos(@RequestBody List<Alumno> alumnos, @PathVariable Long idEmpleo){

        Empleo empleo= empleoService.obtenerEmpleoPorId(idEmpleo);
        mailService.enviarCandidatos(alumnos,empleo);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/Modificar/{idEmpleo}")
    public ResponseEntity<EmpleoOutputDTO> modificarEmpleo(@RequestBody EmpleoInputDTO empleoInputDTO,@PathVariable Long idEmpleo){
        if(empleoInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Empleo empleo=empleoInputDTOMapper.apply(empleoInputDTO);
        Empleo empleoPersistido=empleoService.modificarEmpleo(empleo,idEmpleo);
        EmpleoOutputDTO empleoOutputDTO=empleoOutputDTOMapper.apply(empleoPersistido);

        return ResponseEntity.ok(empleoOutputDTO);
    }

    @DeleteMapping("Borrar/{idEmpleo}")
    public ResponseEntity<EmpleoOutputDTO> borrarEmpleo(@PathVariable Long idEmpleo){
        try{
            empleoService.eliminarEmpleoPorId(idEmpleo);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }

    }


    //Seccion de Alumno-Empleo

    @PostMapping("/interesadoEmpleo/{empleoId}")
    public ResponseEntity<Map<String,Long>> crearAlumnoEmpleo(@PathVariable Long empleoId,@RequestParam Boolean interesado, @AuthenticationPrincipal Alumno alumno){

        AlumnoEmpleo relacion=AlumnoEmpleo.builder()
                .alumno(alumno)
                .empleo(Empleo.builder()
                        .id(empleoId)
                        .build())
                .interesado(interesado)
                .build();
        alumnoEmpleoService.crearRelacion(relacion);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    //Seccion de Empleo-titulacion
}
