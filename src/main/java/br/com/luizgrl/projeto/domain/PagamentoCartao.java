package br.com.luizgrl.projeto.domain;

import javax.persistence.Entity;

import br.com.luizgrl.projeto.domain.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento{
    private Integer installments;

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public PagamentoCartao() {
    }

    public PagamentoCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer installments) {
        super(id, estadoPagamento, pedido);
        this.installments = installments;
    }

    public PagamentoCartao(Integer installments) {
        this.installments = installments;
    }

}