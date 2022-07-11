package br.com.luizgrl.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.luizgrl.projeto.domain.Categoria;


public class CategoriaDTO implements Serializable{
    public static final long serialVersionUID = 1L;

    private Integer id;
    
    @NotEmpty(message = "Campo não pode ser vazio ")
    @Length(min = 5,max = 80, message = "Quantidade de cacteres muito baixa ou muito alta")
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
