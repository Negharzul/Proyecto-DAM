package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.LoginDTO;
import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Models.Usuario;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import com.enlaceFP.enlaceFP.Services.ProfesorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final AlumnoService alumnoService;
    private final ProfesorService profesorService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Map<String,String>> login (@AuthenticationPrincipal Usuario usuario, HttpServletRequest request){

        Map<String,String> response = new HashMap<>();
        response.put("nombreRole",usuario.getRole().getNombreRole());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cambiarPassword")
    public ResponseEntity<Void> cambioPassword (@AuthenticationPrincipal Usuario usuario,@RequestParam String password, HttpServletRequest request){


        if(usuario instanceof Profesor profesor){
            profesor.setPassword(passwordEncoder.encode(password));
            profesorService.actualizarProfesor(profesor);
        }else{
            Alumno alumno=(Alumno)usuario;
            alumno.setPassword(passwordEncoder.encode(password));
            alumnoService.actualizarAlumno(alumno);
        }

        return ResponseEntity.ok().build();
    }

}
