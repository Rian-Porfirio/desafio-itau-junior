package br.com.rianporfirio.desafio_itau.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.OffsetDateTime;

public record TransacaoDto(@PositiveOrZero double valor, @NotNull @Past OffsetDateTime dataHora) {}
