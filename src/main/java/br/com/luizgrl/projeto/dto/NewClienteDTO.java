package br.com.luizgrl.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.luizgrl.projeto.service.validation.ClienteInsert;

@ClienteInsert
public class NewClienteDTO implements Serializable{
    public NewClienteDTO() {
    }
    private static final long serialVersionUID= 1L;
    @NotEmpty(message = "Preencimento  obrigatorio")
    @Length(min = 5,max = 128, message = "Minimo de 5 caracters e maximo de 128")
    private String name;
    @Email
    @NotEmpty(message = "Preencimento  obrigatorio")
    private String email;
    @NotEmpty(message = "Preencimento  obrigatorio")
    private String cpfOrCnpj;
    private Integer tipoCliente;
    @NotEmpty(message = "Preencimento  obrigatorio")
    private String telefone1;
    private String telefone2;
    private String telefone3;
    @NotEmpty(message = "Preencimento  obrigatorio")
    private String logradouro;
    @NotEmpty(message = "Preencimento  obrigatorio")
    private String numero;
    private String complemento;
    @NotEmpty(message = "Preencimento  obrigatorio")
    private String bairro;
    @NotEmpty(message = "Preencimento  obrigatorio")
    private String cep;
    private Integer cidadeId;

    @NotEmpty
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public Integer getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public String getTelefone1() {
        return telefone1;
    }
    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }
    public String getTelefone2() {
        return telefone2;
    }
    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
    public String getTelefone3() {
        return telefone3;
    }
    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public Integer getCidadeId() {
        return cidadeId;
    }
    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
