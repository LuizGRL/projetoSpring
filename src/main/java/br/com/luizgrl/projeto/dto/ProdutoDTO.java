package br.com.luizgrl.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.luizgrl.projeto.domain.Produto;


public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotEmpty
    @Length(min = 5,max = 128,message = "Minimo de 5 caracteres maximo de 128")
    private String name;
    @NotEmpty
    private Double price;


    
    public ProdutoDTO() {
    }
    public ProdutoDTO(Produto obj){
        id = obj.getId();
        name = obj.getName();
        price = obj.getPrice();
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
}
