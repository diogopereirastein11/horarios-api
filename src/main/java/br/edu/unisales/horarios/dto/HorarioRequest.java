package br.edu.unisales.horarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record HorarioRequest(
        @NotNull(message = "O professor e obrigatorio") Long professorId,
        @NotNull(message = "A disciplina e obrigatoria") Long disciplinaId,
        @NotNull(message = "A turma e obrigatoria") Long turmaId,
        @NotNull(message = "O dia da semana e obrigatorio") DayOfWeek diaSemana,
        @NotNull(message = "A hora de inicio e obrigatoria") @Schema(type = "string", example = "19:00:00") LocalTime horaInicio,
        @NotNull(message = "A hora de fim e obrigatoria") @Schema(type = "string", example = "20:40:00") LocalTime horaFim
) {
}
