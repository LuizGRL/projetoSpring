package br.com.luizgrl.projeto.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.luizgrl.projeto.domain.Categoria;
import br.com.luizgrl.projeto.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.name LIKE %:name% AND cat IN :categorias")
    Page<Produto> search(@Param("name") String name, @Param("categorias") List<Categoria> categorias, Pageable pageRequest); // param vai jogar a variavel nome para dentro da querry da mesma forma que faz o mesmo com categorias
    
}
