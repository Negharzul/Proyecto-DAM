package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.AlumnoTitulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AlumnoTitulacionRepository extends JpaRepository<AlumnoTitulacion,Long> {
}
