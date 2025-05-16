package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.AlumnoEmpleo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoEmpleoRepository extends JpaRepository<AlumnoEmpleo,Long> {
}
