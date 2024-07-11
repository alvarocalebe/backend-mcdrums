package br.unitins.topicos1.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "categoria")
public class Categoria extends DefaultEntity {

    private String nomeCategoria;

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

}
