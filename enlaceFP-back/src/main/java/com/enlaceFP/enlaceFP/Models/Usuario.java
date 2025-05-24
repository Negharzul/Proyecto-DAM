package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected  String nombre;

    protected  String apellidos;

    protected String dni;

    protected  LocalDateTime fechaRegistro=LocalDateTime.now();

    protected String password;

    protected String telefono;

    protected boolean enabled;

    protected boolean accountNonExpired;

    protected boolean credentialNonExpired;

    protected boolean accountNonLocked;

    @Column(unique = true)
    protected  String correoElectronico;

    @ManyToOne
    @JoinColumn(name = "role_id")
    protected Role role;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities();
    }


    private List<String> getPrivileges(){
        List<String> privileges = new ArrayList<>();
        privileges.add(role.getNombreRole());

        privileges.addAll(role.getRolPermisos()
                .stream()
                .map( rolePermiso -> rolePermiso.getPermiso().getNombre())
                .toList());

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(){
        List<String> privileges = getPrivileges();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
