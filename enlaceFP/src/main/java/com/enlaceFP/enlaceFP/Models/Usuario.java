package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected  String nombre;

    protected  String apellidos;

    protected String dni;

    protected  LocalDateTime fechaRegistro;

    protected String password;

    protected String telefono;

    protected String direccion;

    protected boolean enabled;

    protected boolean accountNonExpired;

    protected boolean credentialNonExpired;

    protected boolean accountNonLocked;

    @Column(unique = true)
    protected  String correoElectronico;

    @ManyToOne
    @JoinColumn(name = "rol_id")
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


}
