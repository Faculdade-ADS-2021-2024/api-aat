package com.aatorganicos.aatorganicos.dto;

import java.time.LocalDate;

import com.aatorganicos.aatorganicos.enums.Sexo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaDto(
        Long Id,
        @NotNull @NotBlank String Nome,
        @NotNull @NotBlank String Email,
        @NotNull @NotBlank LocalDate DtNascimento,
        @NotNull @NotBlank Sexo sexo,
        @NotNull @NotBlank Number CPF) {

}
