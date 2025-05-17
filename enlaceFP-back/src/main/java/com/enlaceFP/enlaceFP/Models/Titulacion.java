package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Titulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "titulacion")
    private List<AlumnoTitulacion> estudios;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "titulacion")
    private List<TitulacionEmpleo> titulacionesEmpleo;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Titulacion that = (Titulacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
