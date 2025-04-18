package com.enlaceFP.enlaceFP.Repositories;

import com.enlaceFP.enlaceFP.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}
