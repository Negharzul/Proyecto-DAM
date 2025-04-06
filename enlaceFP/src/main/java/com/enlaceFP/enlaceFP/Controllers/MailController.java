package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.AlumnoInputDTO;
import com.enlaceFP.enlaceFP.DTOs.EmpresaInputDTO;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import com.enlaceFP.enlaceFP.Services.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("Correo")
public class MailController {

    private final MailService mailService;
    private final AlumnoService alumnoService

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarCorreo(@RequestParam String destinatario,
                                       @RequestParam String tema,
                                       @RequestParam String contenido){

        mailService.enviarCorreo(destinatario,tema,contenido);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/contactarEmpresa/{idEmpresa}")
    public ResponseEntity<?> contactarEmpresa(@PathVariable Long idEmpresa,
                                              @RequestBody List<String> alumnos,
                                              @RequestBody EmpresaInputDTO empresa){
        StringBuilder mensaje=new StringBuilder("Los siguientes alumnos estan interesados en realizar la FCT en su empresa:");
        for(String nombre: alumnos){
            mensaje.append("/n"+nombre);

        }
        mailService.enviarCorreo(empresa.nombre(),"Lista alumnos interesados FCT",mensaje.toString());
        return ResponseEntity.noContent().build();
    }
}
