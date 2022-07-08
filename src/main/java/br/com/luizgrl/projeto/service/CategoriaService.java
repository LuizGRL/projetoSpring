package br.com.luizgrl.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.repositories.CategoriaRepository;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
    @Autowired // É automaticamente iniciada pelo Spring 
    private CategoriaRepository catRepo;
    public Categoria find(Integer id){ // Criando uma função para buscar o dado dentro do repositorio
        Optional<Categoria> obj  = catRepo.findById(id); // Buscando pelo id
        return obj.orElseThrow(()-> new ObjectNotFoundException(
            "Id: "+id+" Não encontrado em categorias. Tipo"+Categoria.class.getName())); // capturando e tratando um erro de id não encontrado 
    }

    public Categoria insert(Categoria obj){
        obj.setId(null); // o objeto deve ter id null caso n seja vai ser considerado uma atualizao 
        return catRepo.save(obj);

    }
    
}
