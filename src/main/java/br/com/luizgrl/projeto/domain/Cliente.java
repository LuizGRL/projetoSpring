package br.com.luizgrl.projeto.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.luizgrl.projeto.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String cpfOrCnpj;
    private Integer tipoCliente;  // Eh necessario alterar tipoCliente para um integer pois eh mais vantajoso trabalhar com tipos inteiros
    @JsonIgnore //Para não mostrar hora que cliente for recuperado
    private String password;
    @ElementCollection
    @CollectionTable(name="telefone") // como telefone é uma entidade fraca que não foi criada classe é necessario criar uma coleção de elementos para que ela seja adicionada na tabela do banco de dados
    private Set<String> telefones = new HashSet<>(); // e usa Set pois nele não permite que haja repetições assim caso seja adicionado mais de um numero eles nao irao ser repetidos 
    
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)// se cliente for deletado todos os endereços vao ser deletados tambem
    private List<Endereco> enderecos = new ArrayList<>();
    
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id, String name, String email, String cpfOrCnpj, TipoCliente tipoCliente,String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.tipoCliente =(tipoCliente==null) ?/*se o tipo cliente for nullo vai 
        ser atribuido valor nulo caso contrartio é atirbuido o valor */ null : tipoCliente.getCod(); // para pegar somento o codigo do tipo de cliente 
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    

}
