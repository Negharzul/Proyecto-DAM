package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.AlumnoTitulacion;
import com.enlaceFP.enlaceFP.Models.Empleo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface AlumnoTitulacionRepository extends JpaRepository<AlumnoTitulacion,Long> {

    List<AlumnoTitulacion> findByAlumno(Alumno alumno);

    @Modifying
    @Query("DELETE FROM AlumnoTitulacion at WHERE at.alumno.id = :idAlumno")
    void deleteByAlumnoId(@Param("idAlumno") Long idAlumno);
}
