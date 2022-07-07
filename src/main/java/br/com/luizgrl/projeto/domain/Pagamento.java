package br.com.luizgrl.projeto.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import br.com.luizgrl.projeto.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)// Como Pagamento é uma herança, na hora de criar o banco de dados é necessario especificar a fomar que vai ser criado para que tenha Pagamento com boleto e cartao nesse caso vai ser criada uma tabela para ambos usando SINGLE vai criar somente uma tabela onde os valores do tipo de pagamneto não corresnpondente vao ser nulo
public abstract class Pagamento implements Serializable{ // a classe eh asbtrata pois assim não permite que ela estanciada sendo necessario estanciar alguma das subclasses 
    private static final long serialVersionUID = 1L; 
    @Id// como o id de pagamento é o msm de pedido so precisa anotar que é um id e não é necessario gerar outro
    private Integer id;
    
    private Integer estadoPagamento;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId // vai garantir que o id de pagamento seja o mesmo do pedido 
    private Pedido pedido;
    
    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
        this.id = id;
        this.estadoPagamento = estadoPagamento.getCod();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstadoPagamento() {
        return EstadoPagamento.toEnum(estadoPagamento);
    }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
        this.estadoPagamento = estadoPagamento.getCod();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        Pagamento other = (Pagamento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}
