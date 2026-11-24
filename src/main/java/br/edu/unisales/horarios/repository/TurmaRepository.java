package br.edu.unisales.horarios.repository;

import br.edu.unisales.horarios.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
