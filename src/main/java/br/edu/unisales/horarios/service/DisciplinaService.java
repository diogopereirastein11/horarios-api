package br.edu.unisales.horarios.service;

import br.edu.unisales.horarios.model.Disciplina;
import br.edu.unisales.horarios.repository.DisciplinaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public List<Disciplina> listar() {
        return repository.findAll();
    }

    public Disciplina buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada"));
    }

    public Disciplina salvar(Disciplina disciplina) {
        disciplina.setId(null);
        return repository.save(disciplina);
    }

    public Disciplina atualizar(Long id, Disciplina dados) {
        Disciplina disciplina = buscar(id);
        disciplina.setNome(dados.getNome());
        disciplina.setCargaHoraria(dados.getCargaHoraria());
        return repository.save(disciplina);
    }

    public void excluir(Long id) {
        buscar(id);
        repository.deleteById(id);
    }
}
