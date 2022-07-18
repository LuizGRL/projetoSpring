package br.com.luizgrl.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.luizgrl.projeto.domain.Categoria;

import br.com.luizgrl.projeto.domain.Produto;
import br.com.luizgrl.projeto.repositories.CategoriaRepository;
import br.com.luizgrl.projeto.repositories.ProdutoRepository;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;


@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    public Produto find(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Id não encontrado: "+id));

    }

    public Page<Produto> search(String name, List<Integer> ids,Integer page , Integer linesPerPage,String direction,String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.search(name,categorias,pageRequest);
    }
}
