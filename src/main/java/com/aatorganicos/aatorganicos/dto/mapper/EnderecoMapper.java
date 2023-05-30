package com.aatorganicos.aatorganicos.dto.mapper;

import org.springframework.stereotype.Component;

import com.aatorganicos.aatorganicos.dto.EnderecoDto;
import com.aatorganicos.aatorganicos.model.Endereco;

@Component
public class EnderecoMapper {
    
    public EnderecoDto toDTO(Endereco endereco) {
        if(endereco == null) {
            return null;
        }

        return new EnderecoDto(endereco.getId(), endereco.getCep(), endereco.getLogradouro(), endereco.getCidade(), endereco.getBairro(), endereco.getComplemento());
    }

    public Endereco toEntity(EnderecoDto enderecoDto) {
        if(enderecoDto == null) {
            return null;
        }
        
        Endereco endereco = new Endereco();
        
        if(enderecoDto.Id() != null){
            endereco.setId(enderecoDto.Id());
        }

        endereco.setBairro(enderecoDto.Bairro());
        endereco.setCep(enderecoDto.Cep());
        endereco.setCidade(enderecoDto.Cidade());
        endereco.setComplemento(enderecoDto.Complemento());

        return endereco;
    }
}
