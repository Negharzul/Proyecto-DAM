package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
