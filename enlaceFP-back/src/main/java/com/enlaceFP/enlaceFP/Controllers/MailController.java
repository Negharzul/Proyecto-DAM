package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.EmpresaInputDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.AlumnoEmpleo;
import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import com.enlaceFP.enlaceFP.Services.EmpleoService;
import com.enlaceFP.enlaceFP.Services.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('Profesor') || hasRole('Admin')")
@AllArgsConstructor
@RestController
@RequestMapping("Correo")
public class MailController {

    private final MailService mailService;
    private final EmpleoService empleoService;

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarCorreo(@RequestParam String destinatario,
                                       @RequestParam String tema,
                                       @RequestParam String contenido){

        mailService.enviarCorreo(destinatario,tema,contenido);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/contactarEmpresa/{idEmpleo}")
    public ResponseEntity<?> contactarEmpresa(@PathVariable Long idEmpleo){

        Empleo empleo = empleoService.obtenerEmpleoPorId(idEmpleo);
        List<Alumno> alumnos=empleo.getAsociaciones()
                .stream()
                .filter(asociacion->asociacion.getInteresado()==true)
                .map(AlumnoEmpleo::getAlumno)
                .toList();

        StringBuilder mensaje=new StringBuilder("Los siguientes alumnos estan interesados en el empleo de" + empleo.getNombreEmpleo()+" :");
        for(Alumno alumno: alumnos){
            mensaje.append("\n Nombre: "+alumno.getNombre()+" Email: " + alumno.getCorreoElectronico()+ " Teléfono : " +alumno.getTelefono());

        }
        mailService.enviarCorreo(empleo.getEmpresa().getCorreoElectronico(),"Lista alumnos interesados",mensaje.toString());
        return ResponseEntity.noContent().build();
    }
}
