package br.com.luizgrl.projeto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity //INDICA QUE É UMA ENTIDADE DA JPA
public class Categoria implements Serializable { // SERIALIZABLE PERMITE QUE OS DADOS SEJAM CONVERTIDOS EM bytes PARA BANCO DE DADOS
    private static final long serialVersionUID = 1L; // É PRECISO ADICIONAR UMA VERSAO AO SERIALIZABLE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DEFININDO A GERAÇÂO DE ID
    private Integer id;
    private String name;
    
    @ManyToMany(mappedBy = "categorias") // como o mapeamento ja foi feito em produtos não é necessario refaze-lo somente precisa especificar onde foi feito no caso é em Produto- categorias
    private List<Produto> produtos = new ArrayList<>();

    public Categoria(){

    }

    public Categoria(Integer id, String name) {
        this.id = id;
        this.name = name;
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

        public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    // O HASCODE E EQUALS  SERVE PARA COMPARAR OS DADOS QUANDO FOR NECESSARIA 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categoria other = (Categoria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
}


