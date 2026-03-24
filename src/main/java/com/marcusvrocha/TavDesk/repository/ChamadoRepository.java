package com.marcusvrocha.TavDesk.repository;

import com.marcusvrocha.TavDesk.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
