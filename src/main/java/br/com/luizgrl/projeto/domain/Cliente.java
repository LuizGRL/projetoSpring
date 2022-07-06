package br.com.luizgrl.projeto.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.luizgrl.projeto.domain.enums.TipoCliente;
public class Cliente implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer tipoCliente;  // Eh necessario alterar tipoCliente para um integer pois eh mais vantajoso trabalhar com tipos inteiros
    private Set<String> telefones = new HashSet<>(); // e usa Set pois nele não permite que haja repetições assim caso seja adicionado mais de um numero eles nao irao ser repetidos 
    private List<Endereco> enderecos = new ArrayList<>();
    
    public Cliente() {
    }

    public Cliente(Integer id, String name, String email, String cpfOrCnpj, TipoCliente tipoCliente,
            Set<String> telefones, List<Endereco> enderecos) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.tipoCliente = tipoCliente.getCod(); // para pegar somento o codigo do tipo de cliente 
        this.telefones = telefones;
        this.enderecos = enderecos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente); // chamando a função toEnum dentro do "TipoCliente"
        
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    
    





}
