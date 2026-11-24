package br.edu.unisales.horarios.repository;

import br.edu.unisales.horarios.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByProfessorId(Long professorId);

    List<Horario> findByTurmaId(Long turmaId);
}
