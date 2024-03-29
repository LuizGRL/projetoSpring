package br.com.luizgrl.projeto.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import br.com.luizgrl.projeto.config.SecurityConfig;
import br.com.luizgrl.projeto.domain.enums.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.domain.Cidade;
import br.com.luizgrl.projeto.domain.Cliente;
import br.com.luizgrl.projeto.domain.Endereco;
import br.com.luizgrl.projeto.domain.Estado;
import br.com.luizgrl.projeto.domain.ItemPedido;
import br.com.luizgrl.projeto.domain.Pagamento;
import br.com.luizgrl.projeto.domain.PagamentoBoleto;
import br.com.luizgrl.projeto.domain.PagamentoCartao;
import br.com.luizgrl.projeto.domain.Pedido;
import br.com.luizgrl.projeto.domain.Produto;
import br.com.luizgrl.projeto.domain.enums.EstadoPagamento;
import br.com.luizgrl.projeto.domain.enums.TipoCliente;
import br.com.luizgrl.projeto.repositories.CategoriaRepository;
import br.com.luizgrl.projeto.repositories.CidadeRepository;
import br.com.luizgrl.projeto.repositories.ClienteRepository;
import br.com.luizgrl.projeto.repositories.EnderecoRepository;
import br.com.luizgrl.projeto.repositories.EstadoRepository;
import br.com.luizgrl.projeto.repositories.ItemPedidoRepository;
import br.com.luizgrl.projeto.repositories.PagamentoRepository;
import br.com.luizgrl.projeto.repositories.PedidoRepository;
import br.com.luizgrl.projeto.repositories.ProdutoRepository;
@Service
public class DBService {
    
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

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private SecurityConfig securityConfig;

    public void instantiateTestDatabase()throws ParseException{// vai server para iniciaalizar os dados criados 

        Categoria categoria1 = new Categoria(null,"escritorio");
		Categoria categoria2 = new Categoria(null,"Computador");
		Categoria categoria3 = new Categoria(null,"Livros");
		Categoria categoria4 = new Categoria(null,"Coleção");
		Categoria categoria5 = new Categoria(null,"DVD");
		Categoria categoria6 = new Categoria(null,"Jardinagem");
		Categoria categoria7 = new Categoria(null,"Construção");

		
		Produto produto1 = new Produto(null, "computador",2000.00);
		Produto produto2 = new Produto(null, "caneta",2.00);	
		Produto produto3 = new Produto(null, "mouse",20.00);
		Produto produto4 = new Produto(null, "plastic love",20.00);
		Produto produto5 = new Produto(null, "funko",20.00);
		Produto produto6 = new Produto(null, "planta carnivora",20.00);
		Produto produto7 = new Produto(null, "arame",20.00);
		Produto produto8 = new Produto(null, "pa",20.00);
		Produto produto9 = new Produto(null, "semente",20.00);
		Produto produto10 = new Produto(null, "Senhor dos aneis",20.00);
		Produto produto11 = new Produto(null, "kafka a beira mar",20.00);


		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto1,produto2));
		categoria3.getProdutos().addAll(Arrays.asList(produto10,produto11));
		categoria4.getProdutos().addAll(Arrays.asList(produto5));
		categoria5.getProdutos().addAll(Arrays.asList(produto4));
		categoria6.getProdutos().addAll(Arrays.asList(produto6,produto9,produto8));
		categoria7.getProdutos().addAll(Arrays.asList(produto7,produto8));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto2.getCategorias().addAll(Arrays.asList(categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto4.getCategorias().addAll(Arrays.asList(categoria5));
		produto5.getCategorias().addAll(Arrays.asList(categoria4));
		produto6.getCategorias().addAll(Arrays.asList(categoria6));
		produto7.getCategorias().addAll(Arrays.asList(categoria7));
		produto8.getCategorias().addAll(Arrays.asList(categoria6,categoria7));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria3));
		produto11.getCategorias().addAll(Arrays.asList(categoria3));


		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6,categoria7));
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4,produto5,produto6,produto7,produto8,produto9,produto10,produto11));


		Estado estado1 = new Estado(null,"Goais");
		Estado estado2 = new Estado(null, "Rio de Janeiro");
		Cidade cidade1 = new Cidade(null, "Goiania",estado1);
		Cidade cidade2 = new Cidade(null, "Rio de janeiro", estado2);
		Cidade cidade3 = new Cidade(null, "Hidrolandia",estado1);
		Cidade cidade4 = new Cidade(null, "niteroi", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1, cidade3));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade4));

		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3,cidade4));

		Cliente cliente1 = new Cliente(null,"Luiz","testespringlgrl@gmail.com","0000000",TipoCliente.PESSOAFISICA,securityConfig.bCryptPasswordEncoder().encode("123"));
		cliente1.addPerfil(Perfil.CLIENTE);
		Cliente cliente2 = new Cliente(null,"Paloma","paloma@gmail.com","0000000",TipoCliente.PESSOAFISICA,securityConfig.bCryptPasswordEncoder().encode("123"));
		cliente2.addPerfil(Perfil.ADMIN);
		Cliente cliente3 = new Cliente(null,"Loja","loja@email.com","0000000",TipoCliente.PESSOAJURIDICA,securityConfig.bCryptPasswordEncoder().encode("123"));
		cliente3.addPerfil(Perfil.CLIENTE);
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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        

		Pedido pedido1 = new Pedido(null,sdf.parse("15/09/2021 12:43") ,endereco1,cliente1);
		Pedido pedido2 = new Pedido(null,sdf.parse("16/06/2020 10:20"),endereco1,cliente2);
		Pedido pedido3 = new Pedido(null,sdf.parse("08/06/2020 22:40"),endereco2,cliente1);

		Pagamento pagamento1 = new PagamentoCartao(null, EstadoPagamento.PENDENTE,pedido1, 2);
		pedido1.setPagamento(pagamento1);
		Pagamento pagamento2 = new PagamentoCartao(null, EstadoPagamento.CANCELADO,pedido2, 1);
		pedido2.setPagamento(pagamento2);
		Pagamento pagamento3 = new PagamentoBoleto(null, EstadoPagamento.QUITADO, pedido3, sdf.parse("08/06/2020 22:40"), null);
		pedido3.setPagamento(pagamento3);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1,pedido3));
		cliente2.getPedidos().addAll(Arrays.asList(pedido2));
		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2,pedido3));

		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.20, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto2, 0.00, 2, 1500.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido3, produto3, 0.00, 1, 600.00);
		ItemPedido itemPedido4 = new ItemPedido(pedido2, produto1, 0.20, 1, 2000.00);

		pedido1.getItemPedidos().addAll(Arrays.asList(itemPedido1,itemPedido2));
		pedido2.getItemPedidos().addAll(Arrays.asList(itemPedido4));
		pedido3.getItemPedidos().addAll(Arrays.asList(itemPedido3));

		produto1.getItemPedidos().addAll(Arrays.asList(itemPedido1,itemPedido4));
		produto2.getItemPedidos().addAll(Arrays.asList(itemPedido2));
		produto3.getItemPedidos().addAll(Arrays.asList(itemPedido3));

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1,itemPedido2,itemPedido3,itemPedido4));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1,pagamento2,pagamento3));
	
		
        }
    }
    

