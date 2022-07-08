package br.com.luizgrl.projeto.dto;

import java.io.Serializable;

import br.com.luizgrl.projeto.domain.Categoria;

public class CategoriaDTO implements Serializable{
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    
    public CategoriaDTO() {
    }
    
    public CategoriaDTO(Categoria obj) {
        id = obj.getId();
        name = obj.getName();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    
    
}
