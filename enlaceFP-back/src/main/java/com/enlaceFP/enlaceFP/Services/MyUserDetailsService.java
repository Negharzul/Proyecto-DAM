package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Models.Role;
import com.enlaceFP.enlaceFP.Models.Usuario;
import com.enlaceFP.enlaceFP.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Transactional
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;


    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException{

        Usuario usuario= usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado con correo :"+ correoElectronico));

        Hibernate.initialize(usuario.getRole().getRolPermisos());
        return switch (usuario){
            case Alumno alumno -> alumno;
            case Profesor profesor -> profesor;
            default -> throw new UsernameNotFoundException("Usuario no encontrado con correo :"+ correoElectronico);
        };
    }



}
