package br.com.luizgrl.projeto.resources;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias") //end padrão rest 
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;
    @RequestMapping(value ="/{id}" /*definindo end point */, method = RequestMethod.GET) // Como é uma operação para se obter dados usa o verbo GET
    public ResponseEntity<?> find(@PathVariable /* usado para dizer que o id da url deve ir para a variavel */Integer id){
        Categoria obj = categoriaService.find(id);
        return ResponseEntity.ok().body(obj); // vai dar a reposta ok passando no corpo do return o objeto obj
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody /*Vai converter o json em objeto java */ Categoria obj){
        obj = categoriaService.insert(obj); // a intenção é o banco de dados gerar um novo id e inserir ele na URi (categorias/id <- uri)
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //vai definir o caminho da requisição e adicionar no corpo o id do objeto desejado convertendo ele para id
        return ResponseEntity.created(uri).build();
    }
    
}
