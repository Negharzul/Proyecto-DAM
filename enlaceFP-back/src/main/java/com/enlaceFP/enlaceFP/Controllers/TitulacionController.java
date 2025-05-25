package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.TitulacionDTO;
import com.enlaceFP.enlaceFP.Models.Titulacion;
import com.enlaceFP.enlaceFP.Services.TitulacionService;
import com.enlaceFP.enlaceFP.mappers.TitulacionInputDTOMapper;
import com.enlaceFP.enlaceFP.mappers.TitulacionOutputDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@PreAuthorize("hasRole('Profesor') || hasRole('Admin')")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/titulo")
public class TitulacionController {

    private final TitulacionService titulacionService;
    private final TitulacionOutputDTOMapper titulacionOutputDTOMapper;
    private final TitulacionInputDTOMapper titulacionInputDTOMapper;

    @GetMapping("/{idTitulacion}")
    public ResponseEntity<TitulacionDTO> getTitulacion(@PathVariable Long idTitulacion) {

        try {
            Titulacion titulacion = titulacionService.obtenerTitulacionPorId(idTitulacion);
            TitulacionDTO titulacionDTO = titulacionOutputDTOMapper.apply(titulacion);
            return ResponseEntity.ok(titulacionDTO);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TitulacionDTO>> getAllTitulaciones(){
        List<Titulacion> titulaciones=titulacionService.obtenerTitulaciones();
        List<TitulacionDTO> titulacionDTOS= titulaciones.stream().map(titulacionOutputDTOMapper).toList();

        return ResponseEntity.ok(titulacionDTOS);
    }


    @PostMapping
    public ResponseEntity<Long> crearTitulacion (@RequestBody TitulacionDTO titulacionDTO){
        if(titulacionDTO ==null){
            return ResponseEntity.badRequest().build();
        }
        Titulacion titulacion = titulacionInputDTOMapper.apply(titulacionDTO);
        titulacionService.crearTitulacion(titulacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(titulacion.getId());

    }

    /*
    @PatchMapping("/Modificar/{titulacionId}")
    public ResponseEntity<TitulacionDTO> modificarTitulacion(@RequestBody TitulacionDTO titulacionDTO,@PathVariable Long titulacionId){
        if(titulacionDTO==null){
            return ResponseEntity.badRequest().build();
        }
        Titulacion titulacion=titulacionInputDTOMapper.apply(titulacionDTO);
        titulacionService.modificarTitulacion(titulacion,titulacionId);

        return ResponseEntity.noContent().build();
    }

     */

    @DeleteMapping("{idTitulacion}")
    public ResponseEntity<TitulacionDTO> borrarTitulacion(@PathVariable Long idTitulacion){
        try{
            titulacionService.eliminarTitulacionPorId(idTitulacion);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }

    }


}
