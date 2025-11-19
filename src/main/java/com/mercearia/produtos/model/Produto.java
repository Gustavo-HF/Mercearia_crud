package com.mercearia.produtos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long id;

    @NotBlank (message= "O nome do Produto não pode estar vazio")
    private String nome;

    @PositiveOrZero(message= "O preço do Produto deve ser maior ou igual a zero")
    private double preco;

    @NotBlank(message= "O campo referência não pode estar vazio")
    private int ref;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Produto(){

    }

    public Produto(Long id, String nome, double preco, int ref) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.ref = ref;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

}
