package com.aatorganicos.aatorganicos.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.aatorganicos.aatorganicos.enums.Status;
import com.aatorganicos.aatorganicos.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Usuario SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 20)
    @Column(length = 20, nullable = false)
    private String Login;

    @NotBlank
    @NotNull
    @Length(min = 6, max = 16)
    @Column(length = 16, nullable = false)
    private String Senha;

    private Long PessoaId;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

}
