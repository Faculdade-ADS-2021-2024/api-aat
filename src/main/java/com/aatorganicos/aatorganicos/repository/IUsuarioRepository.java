package com.aatorganicos.aatorganicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatorganicos.aatorganicos.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Usuario findByLogin(String login);

}
