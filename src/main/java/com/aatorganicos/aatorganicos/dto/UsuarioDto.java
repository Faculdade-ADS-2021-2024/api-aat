package com.aatorganicos.aatorganicos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(
        Long Id,
        @NotNull @NotBlank String Login,
        @NotNull @NotBlank String Senha,
        @NotNull @NotBlank String Status) {

}
