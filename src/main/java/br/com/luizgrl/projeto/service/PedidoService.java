package br.com.luizgrl.projeto.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.luizgrl.projeto.domain.Cliente;
import br.com.luizgrl.projeto.domain.enums.Perfil;
import br.com.luizgrl.projeto.repositories.ClienteRepository;
import br.com.luizgrl.projeto.security.UserSS;
import br.com.luizgrl.projeto.service.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.luizgrl.projeto.domain.ItemPedido;
import br.com.luizgrl.projeto.domain.PagamentoBoleto;
import br.com.luizgrl.projeto.domain.Pedido;
import br.com.luizgrl.projeto.domain.enums.EstadoPagamento;
import br.com.luizgrl.projeto.repositories.ItemPedidoRepository;
import br.com.luizgrl.projeto.repositories.PagamentoRepository;
import br.com.luizgrl.projeto.repositories.PedidoRepository;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;
    
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setMoment(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

        if(obj.getPagamento() instanceof PagamentoBoleto){// vai verficar se o pagamento é com boleto e difinir a data de vencimento do boleto
            PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento(); // garatindo que seja uma variavel do tipo pagamento com boleto
            boletoService.preencherPagamentoBoleto(pagto, obj.getMoment());
        }
        obj =  pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItemPedidos()){
            ip.setDiscount(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPrice(produtoService.find(ip.getProduto().getId()).getPrice());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItemPedidos());
        System.out.println(obj); // quando é feito um sys out é automaticamente chamado o toString desse objeto caso o mesmo esteja definido
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;

    }

    public Pedido find(Integer id) {
        UserSS user = UserService.authenticated();

        Optional<Pedido> obj = pedidoRepository.findById(id);
        if (user == null || !user.hasRole(Perfil.ADMIN) && !obj.get().getCliente().getId().equals(user.getId())) {

            throw new AuthorizationException("Acesso Negado!");
        }

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

    }
    
}
