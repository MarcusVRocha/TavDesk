package com.marcusvrocha.TavDesk.repository;

import com.marcusvrocha.TavDesk.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
