package br.edu.unisales.horarios.controller;

import br.edu.unisales.horarios.dto.HorarioResponse;
import br.edu.unisales.horarios.model.Turma;
import br.edu.unisales.horarios.service.HorarioService;
import br.edu.unisales.horarios.service.TurmaService;
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
@RequestMapping("/turmas")
@Tag(name = "Turmas")
public class TurmaController {

    private final TurmaService service;
    private final HorarioService horarioService;

    public TurmaController(TurmaService service, HorarioService horarioService) {
        this.service = service;
        this.horarioService = horarioService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as turmas")
    public List<Turma> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma turma pelo id")
    public Turma buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra uma turma")
    public Turma criar(@Valid @RequestBody Turma turma) {
        return service.salvar(turma);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma turma")
    public Turma atualizar(@PathVariable Long id, @Valid @RequestBody Turma turma) {
        return service.atualizar(id, turma);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui uma turma")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/{id}/horarios")
    @Operation(summary = "Horarios de uma turma: professores, disciplinas, dias e horas")
    public List<HorarioResponse> horarios(@PathVariable Long id) {
        return horarioService.listarPorTurma(id);
    }
}
