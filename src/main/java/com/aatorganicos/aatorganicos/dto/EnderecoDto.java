package com.aatorganicos.aatorganicos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDto(
        Long Id,
        @NotNull @NotBlank String Cep,
        @NotNull @NotBlank String Logradouro,
        @NotNull @NotBlank String Cidade,
        @NotNull @NotBlank String Bairro,
        @NotNull @NotBlank String Complemento) {

}
