package br.com.luizgrl.projeto;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.domain.Cidade;
import br.com.luizgrl.projeto.domain.Cliente;
import br.com.luizgrl.projeto.domain.Endereco;
import br.com.luizgrl.projeto.domain.Estado;
import br.com.luizgrl.projeto.domain.Produto;
import br.com.luizgrl.projeto.domain.enums.TipoCliente;
import br.com.luizgrl.projeto.repositories.CategoriaRepository;
import br.com.luizgrl.projeto.repositories.CidadeRepository;
import br.com.luizgrl.projeto.repositories.ClienteRepository;
import br.com.luizgrl.projeto.repositories.EnderecoRepository;
import br.com.luizgrl.projeto.repositories.EstadoRepository;
import br.com.luizgrl.projeto.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

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

		Estado estado1 = new Estado(null,"Goais");
		Estado estado2 = new Estado(null, "Rio de Janeiro");
		Cidade cidade1 = new Cidade(null, "Goiania",estado1);
		Cidade cidade2 = new Cidade(null, "Rio de janeiro", estado2);
		Cidade cidade3 = new Cidade(null, "Hidrolandia",estado1);
		Cidade cidade4 = new Cidade(null, "niteroi", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1, cidade3));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade4));

		Cliente cliente1 = new Cliente(null,"Luiz","luiz@email.com","0000000",TipoCliente.PESSOAFISICA);
		Cliente cliente2 = new Cliente(null,"Paloma","paloma@email.com","0000000",TipoCliente.PESSOAFISICA);
		Cliente cliente3 = new Cliente(null,"Loja","loja@email.com","0000000",TipoCliente.PESSOAJURIDICA);

		cliente1.getTelefones().addAll(Arrays.asList("888888" ,"8855588","9999999"));
		cliente2.getTelefones().addAll(Arrays.asList("777777" ,"6666666","3344553"));
		cliente3.getTelefones().addAll(Arrays.asList("123331" ,"444545","7758823"));

		Endereco endereco1 = new Endereco(null, "Avenida Abri","270","Apto 809", "Ypê","7000-00",cliente1,cidade1);
		Endereco endereco2 = new Endereco(null, "Rua America","172","Lote 14", "Conjunto Abril","6000-10",cliente3,cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1));
		cliente2.getEnderecos().addAll(Arrays.asList(endereco1));
		cliente3.getEnderecos().addAll(Arrays.asList(endereco2));

		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2,cliente3));
		enderecoRepository.saveAll((Arrays.asList(endereco1,endereco2)));
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3,cidade4));
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3));
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2));

	}

}
