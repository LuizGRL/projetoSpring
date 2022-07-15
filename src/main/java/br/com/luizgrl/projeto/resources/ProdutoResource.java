package br.com.luizgrl.projeto.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizgrl.projeto.domain.Produto;
import br.com.luizgrl.projeto.dto.ProdutoDTO;
import br.com.luizgrl.projeto.resources.utils.URL;
import br.com.luizgrl.projeto.service.ProdutoService;

@RestController
@RequestMapping( value= "/produtos")
public class ProdutoResource {
    @Autowired
    private ProdutoService produtoService;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Produto obj = produtoService.find(id);
        return ResponseEntity.ok().body(obj);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categorias", defaultValue = "") String categorias, // ficar atento ao value pois ele é usado na pesquisa 
            @RequestParam(value = "page", defaultValue = "0") Integer pages,
            @RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = produtoService.search(nameDecoded,ids,pages,linePerPage,direction,orderBy);
        Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDTO);

    }

}

