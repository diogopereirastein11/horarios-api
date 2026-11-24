package br.edu.unisales.horarios.repository;

import br.edu.unisales.horarios.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
