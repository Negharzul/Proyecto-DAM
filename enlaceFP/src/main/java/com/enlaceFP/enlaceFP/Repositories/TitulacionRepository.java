package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.Titulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitulacionRepository extends JpaRepository<Titulacion,Long> {
    Optional<Titulacion> findByTitulo(String titulo);
}
