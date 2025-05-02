package com.enlaceFP.enlaceFP.Controllers;

import com.enlaceFP.enlaceFP.DTOs.LoginDTO;
import com.enlaceFP.enlaceFP.Models.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    /*
    @PostMapping
    public ResponseEntity<Map<String,String>> login (@RequestBody LoginDTO loginDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String role=authentication
                .getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_Incorrecto");

        Map<String,String> response = new HashMap<>();
        response.put("nombreRole",role);
        return ResponseEntity.ok(response);
    }


     */
    @GetMapping
    public ResponseEntity<Map<String,String>> login (@AuthenticationPrincipal Usuario usuario, HttpServletRequest request){

        Map<String,String> response = new HashMap<>();
        response.put("nombreRole",usuario.getRole().getNombreRole());
        return ResponseEntity.ok(response);
    }

}
