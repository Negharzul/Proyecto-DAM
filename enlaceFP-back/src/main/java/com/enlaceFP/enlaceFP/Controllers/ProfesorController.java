package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoOutputDTO;
import com.enlaceFP.enlaceFP.DTOs.ProfesorInputDTO;
import com.enlaceFP.enlaceFP.DTOs.ProfesorOutputDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Services.MailService;
import com.enlaceFP.enlaceFP.Services.ProfesorService;
import com.enlaceFP.enlaceFP.mappers.ProfesorInputDTOMapper;
import com.enlaceFP.enlaceFP.mappers.ProfesorOutputDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@PreAuthorize("hasRole('Admin')")
@AllArgsConstructor
@RestController
@RequestMapping("/Profesor")
public class ProfesorController {


    private final ProfesorService profesorService;
    private final ProfesorOutputDTOMapper profesorOutputDTOMapper;
    private final ProfesorInputDTOMapper profesorInputDTOMapper;
    private final MailService mailService;


    @GetMapping("/{idProfesor}")
    public ResponseEntity<ProfesorOutputDTO> getProfesor (@PathVariable Long idProfesor) {

        try {
            Profesor profesor = profesorService.obtenerProfesorPorId(idProfesor);
            ProfesorOutputDTO profesorOutputDTO=profesorOutputDTOMapper.apply(profesor);
            return ResponseEntity.ok(profesorOutputDTO);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profesorPropio")
    public ResponseEntity<ProfesorOutputDTO> getProfesorPropio(@AuthenticationPrincipal Profesor profesor) {

        Profesor profesorBd=profesorService.obtenerProfesorPorId(profesor.getId());
        ProfesorOutputDTO profesorOutputDTO= profesorOutputDTOMapper.apply(profesorBd);
        return ResponseEntity.ok(profesorOutputDTO);
    }

    @GetMapping()
    public ResponseEntity<List<ProfesorOutputDTO>> getAllProfesores(){
        List<Profesor> profesores=profesorService.obtenerProfesores();
        List<ProfesorOutputDTO> profesoresOutputDTO= profesores.stream().map(profesorOutputDTOMapper).toList();

        return ResponseEntity.ok(profesoresOutputDTO);
    }


    @PostMapping("/NuevoProfesor")
    public ResponseEntity<Map<String,Long>> crearProfesor(@RequestBody ProfesorInputDTO profesorInputDTO){
        if(profesorInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Profesor profesor =profesorInputDTOMapper.apply(profesorInputDTO);
        Profesor profesorPersistido=profesorService.crearProfesor(profesor);
        Map<String,Long> response= new HashMap<>();
        response.put("id",profesorPersistido.getId());
        mailService.correoRegistro(profesorPersistido);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PatchMapping("/Modificar/{idProfesor}")
    public ResponseEntity<ProfesorOutputDTO> modificarProfesor(@RequestBody ProfesorInputDTO profesorInputDTO,@PathVariable Long idProfesor){
        if(profesorInputDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Profesor profesor=profesorInputDTOMapper.apply(profesorInputDTO);
        Profesor empresaPersistida=profesorService.modificarProfesor(profesor,idProfesor);
        ProfesorOutputDTO profesorOutputDTO=profesorOutputDTOMapper.apply(empresaPersistida);

        return ResponseEntity.ok(profesorOutputDTO);
    }

    @DeleteMapping("Borrar/{idProfesor}")
    public ResponseEntity<ProfesorOutputDTO> borrarProfesor(@PathVariable Long idProfesor){
        try{
            profesorService.eliminarProfesorPorId(idProfesor);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }

    }
}
