package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Role;
import com.enlaceFP.enlaceFP.Models.Usuario;
import com.enlaceFP.enlaceFP.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreoElectronico(),
                usuario.getPassword(),
                usuario.isEnabled(),
                usuario.isAccountNonExpired(),
                usuario.isCredentialNonExpired(),
                usuario.isAccountNonLocked(),
                getAuthorities(usuario.getRole()));
    }

    private List<? extends GrantedAuthority> getAuthorities(Role role){
        return getGrantedAuthorities(getPrivileges(role));
    }

    private List<String> getPrivileges(Role role){
        List<String> privileges = new ArrayList<>();
        privileges.add(role.getRole());

        privileges.addAll(role.getRolPermisos()
                .stream()
                .map( rolePermiso -> rolePermiso.getPermiso().getPermiso())
                .toList());

                return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


}
