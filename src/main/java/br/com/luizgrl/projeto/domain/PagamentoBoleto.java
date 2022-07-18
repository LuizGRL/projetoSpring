package br.com.luizgrl.projeto.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.luizgrl.projeto.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("PagamentoBoleto")
public class PagamentoBoleto extends Pagamento {
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date paymentDate;
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public PagamentoBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dueDate, Date paymentDate) {
        super(id, estadoPagamento, pedido);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
    public PagamentoBoleto(){
        
    }
    
   
}
