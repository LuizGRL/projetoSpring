// package br.com.luizgrl.projeto.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import br.com.luizgrl.projeto.domain.Cliente;
// import br.com.luizgrl.projeto.repositories.ClienteRepository;
// import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;

// @Service
// public class ClienteService {

//     @Autowired
//     private ClienteRepository clienteRepository;

//     public Cliente find(Integer id){
//         Optional<Cliente> obj = clienteRepository.findById(id);
//         return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente id: "+id+" Não foi encontrado. Tipo: "+Cliente.class.getName()));
//     }
    
// }
