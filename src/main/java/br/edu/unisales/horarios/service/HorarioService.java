package br.edu.unisales.horarios.service;

import br.edu.unisales.horarios.dto.HorarioRequest;
import br.edu.unisales.horarios.dto.HorarioResponse;
import br.edu.unisales.horarios.model.Horario;
import br.edu.unisales.horarios.repository.HorarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
public class HorarioService {

    private final HorarioRepository repository;
    private final ProfessorService professorService;
    private final DisciplinaService disciplinaService;
    private final TurmaService turmaService;

    public HorarioService(HorarioRepository repository,
                          ProfessorService professorService,
                          DisciplinaService disciplinaService,
                          TurmaService turmaService) {
        this.repository = repository;
        this.professorService = professorService;
        this.disciplinaService = disciplinaService;
        this.turmaService = turmaService;
    }

    public List<HorarioResponse> listar() {
        return paraLista(repository.findAll());
    }

    public HorarioResponse buscar(Long id) {
        return paraResposta(obter(id));
    }

    public List<HorarioResponse> listarPorProfessor(Long professorId) {
        professorService.buscar(professorId);
        return paraLista(repository.findByProfessorId(professorId));
    }

    public List<HorarioResponse> listarPorTurma(Long turmaId) {
        turmaService.buscar(turmaId);
        return paraLista(repository.findByTurmaId(turmaId));
    }

    public HorarioResponse criar(HorarioRequest request) {
        Horario horario = new Horario();
        preencher(horario, request);
        return paraResposta(repository.save(horario));
    }

    public HorarioResponse atualizar(Long id, HorarioRequest request) {
        Horario horario = obter(id);
        preencher(horario, request);
        return paraResposta(repository.save(horario));
    }

    public void excluir(Long id) {
        obter(id);
        repository.deleteById(id);
    }

    private Horario obter(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Horario nao encontrado"));
    }

    private void preencher(Horario horario, HorarioRequest request) {
        if (!request.horaFim().isAfter(request.horaInicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A hora de fim deve ser depois da hora de inicio");
        }
        horario.setProfessor(professorService.buscar(request.professorId()));
        horario.setDisciplina(disciplinaService.buscar(request.disciplinaId()));
        horario.setTurma(turmaService.buscar(request.turmaId()));
        horario.setDiaSemana(request.diaSemana());
        horario.setHoraInicio(request.horaInicio());
        horario.setHoraFim(request.horaFim());
    }

    private List<HorarioResponse> paraLista(List<Horario> horarios) {
        return horarios.stream()
                .sorted(Comparator.comparing(Horario::getDiaSemana).thenComparing(Horario::getHoraInicio))
                .map(this::paraResposta)
                .toList();
    }

    private HorarioResponse paraResposta(Horario h) {
        return new HorarioResponse(
                h.getId(),
                h.getProfessor().getId(),
                h.getProfessor().getNome(),
                h.getDisciplina().getId(),
                h.getDisciplina().getNome(),
                h.getTurma().getId(),
                h.getTurma().getNome(),
                h.getDiaSemana(),
                h.getHoraInicio(),
                h.getHoraFim());
    }
}
