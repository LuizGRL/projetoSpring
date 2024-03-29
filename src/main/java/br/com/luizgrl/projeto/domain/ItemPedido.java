package br.com.luizgrl.projeto.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable{
    public static final long serialVersionUID = 1L;
    @JsonIgnore
    @EmbeddedId // é um id embutido em uma classe auxiliar 
    private ItemPedidoPK id = new ItemPedidoPK(); // atributo composto precisa anotar a outra classe para que ela se reconheça como um subtipo
    private Double discount;
    private Integer quantity;
    private Double price;
    
    public ItemPedido() {
    }
    
    public ItemPedido( Pedido pedido,Produto produto, Double discount, Integer quantity, Double price) { // item pedido pk é trocado por pedido e produto pois item pedido pk é uma carcteristica do jpa
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal(){
        return(price-discount) * quantity;
    }
    @JsonIgnore // impede que serialize impedindo acesso ciclico
    public Pedido getPedido(){ // para ter acesso a pedido e produto sem ter que acessar id 
        return id.getPedido();
    }

    public void setPedido(Pedido pedido){
        id.setPedido(pedido);

    }
    public Produto getProduto(){
        return id.getProduto();
    }

    public void setProduto(Produto produto){
        id.setProduto(produto);

    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        ItemPedido other = (ItemPedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemPedido{");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")); //vai formatar numero para padrao de dinheiro
        sb.append(getProduto().getName());
        sb.append(", Qted: ");
        sb.append(getQuantity());
        sb.append(", Preço unitario: ");
        sb.append(numberFormat.format(getPrice()));
        sb.append(", Subtotal: ");
        sb.append(numberFormat.format(getSubTotal()));
        sb.append("}");
        sb.append("\n");

        return sb.toString();
    }
}
