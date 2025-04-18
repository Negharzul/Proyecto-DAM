package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,Long> {
}
