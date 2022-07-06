package br.com.luizgrl.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizgrl.projeto.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {
    
}
