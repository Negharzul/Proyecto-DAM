package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.EmpleoInputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpleoOutputDTO;
import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Services.EmpleoService;
import com.enlaceFP.enlaceFP.mappers.EmpleoInputDTOMapper;
import com.enlaceFP.enlaceFP.mappers.EmpleoOutputDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/Empleo")
public class EmpleoController {

    private final EmpleoService empleoService;
    private final EmpleoOutputDTOMapper empleoOutputDTOMapper;
    private final EmpleoInputDTOMapper empleoInputDTOMapper;

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


}
