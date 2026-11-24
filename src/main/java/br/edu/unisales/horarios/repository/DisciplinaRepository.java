package br.edu.unisales.horarios.repository;

import br.edu.unisales.horarios.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
