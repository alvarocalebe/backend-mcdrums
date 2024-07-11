package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_carrinho")
public class ItemCarrinho extends DefaultEntity{
    
    private Integer quantidade;
    private Double preco;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bateriaCompleta")
    private BateriaCompleta bateriaCompleta;

    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Order getPedido() {
        return order;
    }

    public BateriaCompleta getBateriaCompleta() {
        return bateriaCompleta;
    }

    public void setBateriaCompleta(BateriaCompleta bateriaCompleta) {
        this.bateriaCompleta = bateriaCompleta;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPedido(Order pedido) {
        this.order = pedido;
    }

}
