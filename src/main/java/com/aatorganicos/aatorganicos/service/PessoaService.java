package com.aatorganicos.aatorganicos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;
import com.aatorganicos.aatorganicos.model.Pessoa;
import com.aatorganicos.aatorganicos.repository.IPessoaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class PessoaService {

    private final IPessoaRepository pessoaRepository;

    public PessoaService(IPessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> pessoa() {
        return pessoaRepository.findAll();
    }

    public Pessoa pessoaPorId(@NotNull @Positive Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Pessoa criarPessoa(@Valid Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizaPessoa(@NotNull @Positive Long id, Pessoa pessoa) {
        return pessoaRepository.findById(id)
                .map(data -> {
                    data.setCpf(pessoa.getCpf());
                    data.setDtNascimento(pessoa.getDtNascimento());
                    data.setEmail(pessoa.getEmail());
                    data.setNome(pessoa.getNome());
                    data.setSexo(pessoa.getSexo());
                    return pessoaRepository.save(data);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletePessoa(@NotNull @Positive Long id) {
         pessoaRepository.findById(id)
                .map(data -> {
                    pessoaRepository.deleteById(id);
                    return true;
                }).orElseThrow(() -> new RecordNotFoundException(id));   
    }
    
}
