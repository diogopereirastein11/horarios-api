package br.edu.unisales.horarios.service;

import br.edu.unisales.horarios.model.Professor;
import br.edu.unisales.horarios.repository.ProfessorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public List<Professor> listar() {
        return repository.findAll();
    }

    public Professor buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor nao encontrado"));
    }

    public Professor salvar(Professor professor) {
        professor.setId(null);
        return repository.save(professor);
    }

    public Professor atualizar(Long id, Professor dados) {
        Professor professor = buscar(id);
        professor.setNome(dados.getNome());
        professor.setEmail(dados.getEmail());
        return repository.save(professor);
    }

    public void excluir(Long id) {
        buscar(id);
        repository.deleteById(id);
    }
}
