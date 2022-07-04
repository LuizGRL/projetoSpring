package br.com.luizgrl.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired // É automaticamente iniciada pelo Spring 
    private CategoriaRepository catRepo;
    public Categoria find(Integer id){ // Criando uma função para buscar o dado dentro do repositorio
        Optional<Categoria> obj  = catRepo.findById(id); // Buscando pelo id
        return obj.orElse(null);


    }
    
}
