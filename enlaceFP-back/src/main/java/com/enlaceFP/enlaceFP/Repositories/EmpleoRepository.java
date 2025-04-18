package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleoRepository extends JpaRepository<Empleo,Long> {

    @Query("SELECT e FROM Empleo e WHERE e.empresa.nombre = :nombre")
    List<Empleo> findByNombreEmpresa(@Param("nombre") String nombreEmpresa);
}
