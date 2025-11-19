package com.mercearia.produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercearia.produtos.model.Produto;
import com.mercearia.produtos.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void adicionarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public void deletarProduto(@RequestParam Long id){
        produtoRepository.deleteById(id);
    }

    public Produto editarProduto(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public void alterarProduto(Produto produto){
        produtoRepository.save(produto);
    }
}
