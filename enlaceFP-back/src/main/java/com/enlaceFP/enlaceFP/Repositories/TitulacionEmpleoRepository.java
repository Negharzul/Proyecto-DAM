package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.TitulacionEmpleo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TitulacionEmpleoRepository extends JpaRepository<TitulacionEmpleo,Long> {

    @Modifying
    @Query("DELETE FROM TitulacionEmpleo te WHERE te.empleo.id = :idEmpleo")
    void deleteByEmpleoId(@Param("idEmpleo") Long idAlumno);
}
