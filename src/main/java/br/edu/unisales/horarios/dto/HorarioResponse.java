package br.edu.unisales.horarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record HorarioResponse(
        Long id,
        Long professorId,
        String professor,
        Long disciplinaId,
        String disciplina,
        Long turmaId,
        String turma,
        DayOfWeek diaSemana,
        @Schema(type = "string", example = "19:00:00") LocalTime horaInicio,
        @Schema(type = "string", example = "20:40:00") LocalTime horaFim
) {
}
