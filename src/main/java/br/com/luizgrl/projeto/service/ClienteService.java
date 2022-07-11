package br.com.luizgrl.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.luizgrl.projeto.domain.Cliente;
import br.com.luizgrl.projeto.dto.ClienteDTO;
import br.com.luizgrl.projeto.repositories.ClienteRepository;
import br.com.luizgrl.projeto.service.exceptions.DataIntegrityException;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente id: " + id + " Não foi encontrado. Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente obj){
        find(obj.getId());
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public void delete(Integer id){
        find(id);
        try{
            clienteRepository.deleteById(id);
        }catch(DataIntegrityViolationException event){
            throw new DataIntegrityException("Erro ao deletar cliente", event);

        }
    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

}
