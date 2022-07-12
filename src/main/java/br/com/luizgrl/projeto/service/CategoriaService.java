package br.com.luizgrl.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.dto.CategoriaDTO;
import br.com.luizgrl.projeto.repositories.CategoriaRepository;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
    @Autowired // É automaticamente iniciada pelo Spring
    private CategoriaRepository catRepo;

    public Categoria find(Integer id) { // Criando uma função para buscar o dado dentro do repositorio
        Optional<Categoria> obj = catRepo.findById(id); // Buscando pelo id
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Id: " + id + " Não encontrado em categorias. Tipo" + Categoria.class.getName())); // capturando e
                                                                                                   // tratando um erro
                                                                                                   // de id não
                                                                                                   // encontrado
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null); // o objeto deve ter id null caso n seja vai ser considerado uma atualizao
        return catRepo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());// vai pegar o id pra ver se ele existe
        return catRepo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            catRepo.deleteById(id);

        } catch (DataIntegrityViolationException event) {// caso tente apagar uma cateagoria que tem produtos associados
                                                         // ira bloquear a aação
            throw new DataIntegrityViolationException(
                    "Não é possivel excluir essa categoria pois ela possui produtos associdos ", event);
        }
    }

    public List<Categoria> findAll() { // Existe um problema na implementação que alem de mostrar as categorias tambem
                                       // mostra os produtos para corrigir isso se usa o padrao DTO para regular os
                                       // dados a serem mostrados
        return catRepo.findAll();
    }

    public Categoria fromDTO(CategoriaDTO objDto) { // Usado para converter de DTO para categoria
        return new Categoria(objDto.getId(), objDto.getName());
    }

    public Page<Categoria> findPage(Integer page /* quantidade de paginas */, Integer linesPerPage,
            /* quantidade de linhas por pagina */String direction/* a forma que vai ser feita a oredenação */,
            String orderBy/* direção que é feita a paginação */) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
        
        return catRepo.findAll(pageRequest);
    } // serve para limitar e padronizar a quantidade de elementos a serem mostrados no get

}
