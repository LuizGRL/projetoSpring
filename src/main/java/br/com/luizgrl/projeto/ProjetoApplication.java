package br.com.luizgrl.projeto;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.domain.Produto;
import br.com.luizgrl.projeto.repositories.CategoriaRepository;
import br.com.luizgrl.projeto.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null,"escritorio");
		Categoria categoria2 = new Categoria(null,"Computador");	
		Produto produto1 = new Produto(null, "computador",2000.00);
		Produto produto2 = new Produto(null, "caneta",2.00);	
		Produto produto3 = new Produto(null, "mouse",20.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto1,produto2));
		produto1.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto2.getCategorias().addAll(Arrays.asList(categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1,categoria2));



		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3));
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2));

	}

}
