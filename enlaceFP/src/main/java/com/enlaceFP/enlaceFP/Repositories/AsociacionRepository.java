package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.Asociacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepository extends JpaRepository<Asociacion,Long> {
}
