package br.edu.unisales.horarios.config;

import br.edu.unisales.horarios.model.Disciplina;
import br.edu.unisales.horarios.model.Horario;
import br.edu.unisales.horarios.model.Professor;
import br.edu.unisales.horarios.model.Turma;
import br.edu.unisales.horarios.repository.DisciplinaRepository;
import br.edu.unisales.horarios.repository.HorarioRepository;
import br.edu.unisales.horarios.repository.ProfessorRepository;
import br.edu.unisales.horarios.repository.TurmaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final HorarioRepository horarioRepository;

    public DataLoader(ProfessorRepository professorRepository,
                      DisciplinaRepository disciplinaRepository,
                      TurmaRepository turmaRepository,
                      HorarioRepository horarioRepository) {
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.turmaRepository = turmaRepository;
        this.horarioRepository = horarioRepository;
    }

    @Override
    public void run(String... args) {
        if (professorRepository.count() > 0) {
            return;
        }

        Professor carlos = professorRepository.save(new Professor("Carlos Silva", "carlos@unisales.br"));
        Professor mariana = professorRepository.save(new Professor("Mariana Souza", "mariana@unisales.br"));

        Disciplina web = disciplinaRepository.save(new Disciplina("Projeto Web Site - Back End", 80));
        Disciplina algoritmos = disciplinaRepository.save(new Disciplina("Projeto e Analise de Algoritmos", 60));
        Disciplina poo = disciplinaRepository.save(new Disciplina("Programacao Orientada a Objetos", 80));

        Turma turmaA = turmaRepository.save(new Turma("ADS-3A", "2026/2"));
        Turma turmaB = turmaRepository.save(new Turma("ES-3B", "2026/2"));

        horarioRepository.save(new Horario(carlos, web, turmaA, DayOfWeek.MONDAY, LocalTime.of(19, 0), LocalTime.of(20, 40)));
        horarioRepository.save(new Horario(carlos, web, turmaB, DayOfWeek.WEDNESDAY, LocalTime.of(19, 0), LocalTime.of(20, 40)));
        horarioRepository.save(new Horario(mariana, algoritmos, turmaA, DayOfWeek.TUESDAY, LocalTime.of(19, 0), LocalTime.of(20, 40)));
        horarioRepository.save(new Horario(mariana, poo, turmaB, DayOfWeek.THURSDAY, LocalTime.of(19, 0), LocalTime.of(20, 40)));
    }
}
