package com.aatorganicos.aatorganicos.dto.mapper;

import org.springframework.stereotype.Component;

import com.aatorganicos.aatorganicos.dto.PessoaDto;
import com.aatorganicos.aatorganicos.enums.Sexo;
import com.aatorganicos.aatorganicos.model.Pessoa;

@Component
public class PessoaMapper {
    
    public PessoaDto toDTO(Pessoa pessoa) {
        if(pessoa == null) {
            return null;
        }
        
        return new PessoaDto(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getDtNascimento(), pessoa.getSexo().getValue(), pessoa.getCPF());
    }

    public Pessoa toEntity(PessoaDto pessoaDto) {
        if(pessoaDto == null) {
            return null;
        }
        
        Pessoa pessoa = new Pessoa();

        if(pessoaDto.Id() != null) {
            pessoa.setId(pessoaDto.Id());
        }

        pessoa.setNome(pessoaDto.Nome());
        pessoa.setEmail(pessoaDto.Email());
        pessoa.setDtNascimento(pessoaDto.DtNascimento());
        pessoa.setSexo(convertSexoValue(pessoaDto.Sexo()));
        pessoa.setCPF(pessoaDto.CPF());

        return pessoa;
    }

    public Sexo convertSexoValue(String value) {
        if(value == null) {
            return null;
        }
        
        return switch (value) {
            case "M" -> Sexo.MASCULINO;
            case "F" -> Sexo.FEMININO;
            case "O" -> Sexo.OUTROS;
            default  -> throw new IllegalArgumentException("Valor de sexo inválido");
        };
    }
    
}
