package br.edu.unisales.horarios.controller;

import br.edu.unisales.horarios.dto.HorarioResponse;
import br.edu.unisales.horarios.model.Professor;
import br.edu.unisales.horarios.service.HorarioService;
import br.edu.unisales.horarios.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/professores")
@Tag(name = "Professores")
public class ProfessorController {

    private final ProfessorService service;
    private final HorarioService horarioService;

    public ProfessorController(ProfessorService service, HorarioService horarioService) {
        this.service = service;
        this.horarioService = horarioService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os professores")
    public List<Professor> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um professor pelo id")
    public Professor buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um professor")
    public Professor criar(@Valid @RequestBody Professor professor) {
        return service.salvar(professor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um professor")
    public Professor atualizar(@PathVariable Long id, @Valid @RequestBody Professor professor) {
        return service.atualizar(id, professor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui um professor")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/{id}/horarios")
    @Operation(summary = "Horarios de um professor: disciplinas, turmas, dias e horas")
    public List<HorarioResponse> horarios(@PathVariable Long id) {
        return horarioService.listarPorProfessor(id);
    }
}
