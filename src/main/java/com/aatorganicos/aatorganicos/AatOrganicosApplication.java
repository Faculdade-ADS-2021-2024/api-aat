package com.aatorganicos.aatorganicos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aatorganicos.aatorganicos.model.Categoria;
import com.aatorganicos.aatorganicos.model.Produto;
import com.aatorganicos.aatorganicos.model.Usuario;
import com.aatorganicos.aatorganicos.repository.ICategoriaRepository;
import com.aatorganicos.aatorganicos.repository.IUsuarioRepository;

@SpringBootApplication
public class AatOrganicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AatOrganicosApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ICategoriaRepository categoriaRepository, IUsuarioRepository usuarioRepository) {
		return args -> {
			categoriaRepository.deleteAll();

			Categoria c = new Categoria();
			
			c.setNome("Teste");
			c.setDescricao("Teste descrição");

			Produto p = new Produto();
			p.setNome("Produto Teste");
			p.setDescricao("Produto Teste descrição");
			p.setCategoria(c);
			c.getProdutos().add(p);

			categoriaRepository.save(c);

			// usuarioRepository.deleteAll();

			// Usuario u = new Usuario();
			// u.setNome("Arilson");
			// u.setLogin("Ari");
			// u.setSenha(new BCryptPasswordEncoder().encode("123456"));

			// usuarioRepository.save(u);

		};
	}

}
