package com.aatorganicos.aatorganicos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aatorganicos.aatorganicos.dto.PessoaDto;
import com.aatorganicos.aatorganicos.dto.mapper.PessoaMapper;
import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;
import com.aatorganicos.aatorganicos.repository.IPessoaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class PessoaService {

    private final IPessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaService(IPessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    } 

    public List<PessoaDto> pessoa() {
        return pessoaRepository.findAll()
                .stream()
                .map(pessoaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PessoaDto pessoaPorId(@NotNull @Positive Long id) {
        return pessoaRepository.findById(id).map(pessoaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public PessoaDto criarPessoa(@Valid PessoaDto pessoa) {
        return pessoaMapper.toDTO(pessoaRepository.save(pessoaMapper.toEntity(pessoa)));
    }

    public PessoaDto atualizaPessoa(@NotNull @Positive Long id, PessoaDto pessoa) {
        return pessoaRepository.findById(id)
                .map(data -> {
                    data.setCPF(pessoa.CPF());
                    data.setDtNascimento(pessoa.DtNascimento());
                    data.setEmail(pessoa.Email());
                    data.setNome(pessoa.Nome());
                    data.setSexo(pessoa.sexo());
                    return pessoaMapper.toDTO(pessoaRepository.save(data));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletePessoa(@NotNull @Positive Long id) {
        
        pessoaRepository.delete(pessoaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
    
}
