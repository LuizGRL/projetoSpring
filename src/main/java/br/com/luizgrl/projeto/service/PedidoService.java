package br.com.luizgrl.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizgrl.projeto.domain.Pedido;
import br.com.luizgrl.projeto.repositories.PedidoRepository;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Id de objeto não encontrado. Id"+id));

    }
    
}
