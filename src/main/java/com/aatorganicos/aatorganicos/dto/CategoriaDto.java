package com.aatorganicos.aatorganicos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaDto(
        Long Id,
        @NotNull @NotBlank String Nome,
        @NotNull @NotBlank String Descricao) {
}


