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
        
        return new PessoaDto(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getDtNascimento(), pessoa.getSexo(), pessoa.getCPF());
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
        pessoa.setSexo(Sexo.MASCULINO);
        pessoa.setCPF(pessoaDto.CPF());

        return pessoa;
    }
    
}
