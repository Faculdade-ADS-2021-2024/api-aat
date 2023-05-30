package com.aatorganicos.aatorganicos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aatorganicos.aatorganicos.dto.EnderecoDto;
import com.aatorganicos.aatorganicos.dto.mapper.EnderecoMapper;
import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;
import com.aatorganicos.aatorganicos.repository.IEnderecoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class EnderecoService {
    
    private final IEnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public EnderecoService(IEnderecoRepository enderecoRepository, EnderecoMapper enderecoMapper) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
    }

    public List<EnderecoDto> endereco() {
        return enderecoRepository.findAll()
                .stream()
                .map(enderecoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDto enderecoPorId(@NotNull @Positive Long id) {
        return enderecoRepository.findById(id).map(enderecoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public EnderecoDto criarEndereco(@Valid EnderecoDto endereco) {
        return enderecoMapper.toDTO(enderecoRepository.save(enderecoMapper.toEntity(endereco)));
    }

    public EnderecoDto atualizaEndereco(@NotNull @Positive Long id,@Valid @NotNull EnderecoDto endereco) {
        return enderecoRepository.findById(id)
                .map(data -> {
                    data.setBairro(endereco.Bairro());
                    data.setCep(endereco.Cep());
                    data.setCidade(endereco.Cidade());
                    data.setComplemento(endereco.Complemento());
                    data.setLogradouro(endereco.Logradouro());
                    return enderecoMapper.toDTO(enderecoRepository.save(data));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteEndereco( @NotNull @Positive Long id) {
        
        enderecoRepository.delete(enderecoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    
}