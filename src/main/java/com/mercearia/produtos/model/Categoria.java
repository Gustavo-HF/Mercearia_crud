package com.mercearia.produtos.model;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Long id;

    @NotBlank(message= "O nome da categoria não pode estar vazio")
    private String nome;

    @NotBlank(message= "O tipo da categoria não pode estar vazio")
    private String tipo;

    @OneToMany(mappedBy="categoria")
    private List<Produto> produtos;

    
    public Categoria(){

    }

    public Categoria(Long id, String nome, String tipo, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
        this.tipo = tipo;
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
