package com.ctp.projetofinalsnapp;

/**
 * Created by JeanCarlos on 20/09/2016.
 */

public class Anuncio {
    private int id;
    private String produto;
    private String tamanho;
    private Double preco;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getTamanho() {
        return tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
