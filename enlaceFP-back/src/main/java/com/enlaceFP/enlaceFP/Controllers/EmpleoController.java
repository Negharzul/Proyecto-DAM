package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoOutputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpleoInputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpleoOutputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpresaOutputDTO;
import com.enlaceFP.enlaceFP.Models.*;
import com.enlaceFP.enlaceFP.Services.*;
import com.enlaceFP.enlaceFP.mappers.EmpleoInputDTOMapper;
import com.enlaceFP.enlaceFP.mappers.EmpleoOutputDTOMapper;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@PreAuthorize("hasRole('Profesor') || hasRole('Admin')")
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
    private final TitulacionEmpleoService titulacionEmpleoService;
    private final EmpresaService empresaService;
    private EntityManager entityManager;

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

    @PreAuthorize("hasRole('Alumno')")
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


        //TODO Refactorizar
        List<Alumno> alumnos =alumnoService.obtenerAlumnos();
        List<TitulacionEmpleo> asociaciones=empleoPersistido.getTitulacionesEmpleo();
        List<Titulacion> titulos = asociaciones
                .stream()
                .map(TitulacionEmpleo::getTitulacion)
                .toList();

        alumnos= alumnos
                .stream()
                .filter(Alumno::isNotificaciones)
                .filter(alumno->alumno
                        .getEstudios()
                        .stream()
                        .anyMatch(asociacion->titulos.contains(asociacion.getTitulacion())))
                .toList();

        mailService.correoRecomendacionEmpleo(alumnos,empleoPersistido.getNombreEmpleo());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/enviarCandidatos/{idEmpleo}")
    public ResponseEntity<EmpresaOutputDTO> enviarCandidatos(@RequestBody List<Alumno> alumnos, @PathVariable Long idEmpleo){

        Empleo empleo= empleoService.obtenerEmpleoPorId(idEmpleo);
        mailService.enviarCandidatos(alumnos,empleo);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @PatchMapping("/Modificar/{idEmpleo}")
    public ResponseEntity<EmpleoOutputDTO> modificarEmpleo(@RequestBody EmpleoInputDTO empleoInputDTO,@PathVariable Long idEmpleo){
        if(empleoInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
//        Empleo empleo=empleoInputDTOMapper.apply(empleoInputDTO);
//        Empleo empleoPersistido=empleoService.modificarEmpleo(empleo,idEmpleo);
//        EmpleoOutputDTO empleoOutputDTO=empleoOutputDTOMapper.apply(empleoPersistido);


        if(empleoInputDTO.titulacionesExigidas()!=null) {
            titulacionEmpleoService.eliminarRelacionesIdEmpleo(idEmpleo);
            entityManager.flush();
            entityManager.clear();
        }

        Empleo empleo=empleoInputDTOMapper.apply(empleoInputDTO);


        //Prueba a pasarle el numero uno a ver si es que no recibe bien la id por algun motivo
        Empresa empresa=empresaService.obtenerEmpresaPorId(empleoInputDTO.empresaId());
        System.out.println("id de empresa:"+empleoInputDTO.empresaId());
        empleoService.modificarEmpleo(empleo,idEmpleo,empresa);

        for(Long id:empleoInputDTO.titulacionesExigidas()){
            System.out.println(id);
            titulacionEmpleoService.crearRelacion(TitulacionEmpleo.builder()
                    .titulacion(Titulacion.builder().id(id).build())
                    .empleo(Empleo.builder().id(idEmpleo).build())
                    .build());
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idEmpleo}")
    public ResponseEntity<EmpleoOutputDTO> borrarEmpleo(@PathVariable Long idEmpleo){
        try{
            empleoService.eliminarEmpleoPorId(idEmpleo);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }

    }


    //Seccion de Alumno-Empleo


    @PreAuthorize("hasRole('Alumno')")
    @PostMapping("/interesadoEmpleo/{empleoId}")
    public ResponseEntity<Void> crearAlumnoEmpleo(@PathVariable Long empleoId,@RequestParam Boolean interesado, @AuthenticationPrincipal Alumno alumno){

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

    @Transactional
    @PostMapping("/titulosEmpleo/{idEmpleo}")
    public ResponseEntity<Void> crearTitulacionesEmpleo(@PathVariable Long idEmpleo,@RequestBody List<Long> idsTitulaciones){

        for(Long id:idsTitulaciones){
            titulacionEmpleoService.crearRelacion(TitulacionEmpleo
                    .builder()
                    .empleo(Empleo.builder().id(idEmpleo).build())
                    .titulacion(Titulacion.builder().id(id).build())
                    .build());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
