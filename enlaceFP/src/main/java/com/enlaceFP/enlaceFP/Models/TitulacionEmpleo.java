package com.enlaceFP.enlaceFP.Models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TitulacionEmpleo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "titulacion_id")
    private Titulacion titulacion;

    @ManyToOne
    @JoinColumn(name = "empleo_id")
    private Empleo empleo;
}


