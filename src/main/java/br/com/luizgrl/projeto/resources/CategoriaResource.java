package br.com.luizgrl.projeto.resources;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizgrl.projeto.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias") //end padrão rest 
public class CategoriaResource {
    @RequestMapping(method = RequestMethod.GET) // Como é uma operação para se obter dados usa o verbo GET
    public List<Categoria> listing(){
        Categoria cat = new Categoria();
        Categoria cat2 = new Categoria();
        List<Categoria> lista = new ArrayList<>();
        lista.add(cat);
        lista.add(cat2);
        return lista;
    }
    
}
