package br.unitins.topicos1.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bateriacompleta")
public class BateriaCompleta extends DefaultEntity {
    private String nomeBateria;

    private String descricao;

    private Double preco;

    private Integer quantidadeTambor;

    private Integer quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    
    private String nomeImagem;

    public String getNomeBateria() {
        return nomeBateria;
    }

    public void setNomeBateria(String nomeBateria) {
        this.nomeBateria = nomeBateria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public Integer getQuantidadeTambor() {
        return quantidadeTambor;
    }

    public void setQuantidadeTambor(Integer quantidadeTambor) {
        this.quantidadeTambor = quantidadeTambor;
    }


}
