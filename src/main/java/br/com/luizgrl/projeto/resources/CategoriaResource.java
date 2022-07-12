package br.com.luizgrl.projeto.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.dto.CategoriaDTO;
import br.com.luizgrl.projeto.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias") // end padrão rest
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}" /* definindo end point */, method = RequestMethod.GET) // Como é uma operação para
                                                                                           // se obter dados usa o verbo
                                                                                           // GET
    public ResponseEntity<?> find(
            @PathVariable /* usado para dizer que o id da url deve ir para a variavel */Integer id) {
        Categoria obj = categoriaService.find(id);
        return ResponseEntity.ok().body(obj); // vai dar a reposta ok passando no corpo do return o objeto obj
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(
            @Valid @RequestBody /* Vai converter o json em objeto java */ CategoriaDTO objDto) {
        Categoria obj = categoriaService.fromDTO(objDto);
        obj = categoriaService.insert(obj); // a intenção é o banco de dados gerar um novo id e inserir ele na URi
                                            // (categorias/id <- uri)
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        // vai definir o caminho da requisição e adicionar no corpo o id do objeto
        // desejado convertendo ele para id
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO objDTO) {
        Categoria obj = categoriaService.fromDTO(objDTO);
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build(); // retorna uma resposta vazia

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)/* Para cada obj de lista */)
                .collect(Collectors.toList());// convertendo para lista
        return ResponseEntity.ok().body(listDTO);

    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer pages,
            @RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {// como sao parametros
                                                                                        // adicionais é necesserario
                                                                                        // essa anotação
        Page<Categoria> list = categoriaService.findPage(pages,linePerPage,direction,orderBy);
        Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listDTO);

    }

}
