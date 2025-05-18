package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.TitulacionEmpleo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitulacionEmpleoRepository extends JpaRepository<TitulacionEmpleo,Long> {
}
