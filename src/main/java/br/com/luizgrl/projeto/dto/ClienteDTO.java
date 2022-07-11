package br.com.luizgrl.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.luizgrl.projeto.domain.Cliente;

public class ClienteDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotEmpty
    @Length(min = 3, max = 26, message = "Nome não pode ultrapassar 26 caracteres e é necessario ao menos 3 caracteres ")
    private String name;
    @NotEmpty
    @Email(message = "email invalido")
    private String email;
    
    public ClienteDTO() {
    }


    public ClienteDTO(Cliente obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();

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
    
    
}
