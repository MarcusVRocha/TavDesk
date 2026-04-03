package com.marcusvrocha.TavDesk.repository;

import com.marcusvrocha.TavDesk.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findbyEmail(String email);

    boolean existsByEmail(String email);

}
