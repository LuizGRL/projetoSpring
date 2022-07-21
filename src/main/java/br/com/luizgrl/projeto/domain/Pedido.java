package br.com.luizgrl.projeto.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date moment;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;
    
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itemPedidos = new HashSet<>(); // usando set para nao ter item repetido no pedido

    
    public Pedido() {
    }

    public Pedido(Integer id, Date moment,  Endereco endereco, Cliente cliente) {
        this.id = id;
        this.moment = moment;
        this.endereco = endereco;
        this.cliente = cliente;
    }

    public double getValorTotal(){
        double sum=0.0;
        for(ItemPedido ip: itemPedidos){
            sum = sum +  ip.getSubTotal();
        }
        return sum;
    }
    
    public Set<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(Set<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

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
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        final StringBuffer sb = new StringBuffer("Pedido{");
        sb.append("Pedido número: ");
        sb.append(getId());
        sb.append(", Instante: ");
        sb.append(sdf.format(getMoment()));
        sb.append(", Cliente: ");
        sb.append(getCliente().getName());
        sb.append(", CPF/CNPJ: ");
        sb.append(getCliente().getCpfOrCnpj());
        sb.append(", Situação do pagamento: ");
        sb.append(getPagamento().getEstadoPagamento().getMensage());
        sb.append("\nDetalhes:\n");
        for(ItemPedido ip: getItemPedidos()){
            sb.append(ip.toString());
        }
        sb.append(", Valor total: ");
        sb.append(numberFormat.format(getValorTotal()));
        sb.append('}');
        return sb.toString();
    }
}
