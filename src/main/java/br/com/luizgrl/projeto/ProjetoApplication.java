package br.com.luizgrl.projeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.repositories.CategoriaRepository;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null,"escritorio");
		Categoria categoria2 = new Categoria(null,"Computador");	

		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2));

	}

}
