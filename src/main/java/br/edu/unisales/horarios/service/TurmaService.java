package br.edu.unisales.horarios.service;

import br.edu.unisales.horarios.model.Turma;
import br.edu.unisales.horarios.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    public List<Turma> listar() {
        return repository.findAll();
    }

    public Turma buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma nao encontrada"));
    }

    public Turma salvar(Turma turma) {
        turma.setId(null);
        return repository.save(turma);
    }

    public Turma atualizar(Long id, Turma dados) {
        Turma turma = buscar(id);
        turma.setNome(dados.getNome());
        turma.setSemestre(dados.getSemestre());
        return repository.save(turma);
    }

    public void excluir(Long id) {
        buscar(id);
        repository.deleteById(id);
    }
}
