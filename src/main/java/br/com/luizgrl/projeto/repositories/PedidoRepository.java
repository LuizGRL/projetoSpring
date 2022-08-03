package br.com.luizgrl.projeto.repositories;

import br.com.luizgrl.projeto.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizgrl.projeto.domain.Pedido;

import javax.transaction.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);

    
}
