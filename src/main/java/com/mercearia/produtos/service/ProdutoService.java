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

    public void adicionarProduto(@ModelAttribute Produto produto, Model model, @RequestParam(defaultValue = "0") int page) {
        validarAdicaoProduto(produto, model, page);
        produtoRepository.save(produto);
    }

    private void validarAdicaoProduto(@ModelAttribute Produto produto, Model model, @RequestParam(defaultValue = "0") int page) {
        boolean erro = false;
        StringBuilder mensagens = new StringBuilder();

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            mensagens.append("Nome do produto não pode estar vazio. \n");
            erro = true;
        }

        if (produto.getPreco() <= 0 || produto.getPreco() > 100000) {
            mensagens.append("Preço inválido: deve ser maior que zero e até 100.000. \n");
            erro = true;
        }

        if (produto.getRef() == 0) {
            mensagens.append("Referência inválida: não pode ser zero. \n");
            erro = true;
        }

        if (erro) {
            Pageable pageable = PageRequest.of(page, 3);
            Page<Produto> paginaProduto = produtoRepository.findAll(pageable);
            model.addAttribute("paginaProduto", paginaProduto);
            model.addAttribute("produto", produto);
            model.addAttribute("mensagemErro", mensagens.toString());
        }
    }

    public void deletarProduto(@RequestParam Long id){
        produtoRepository.deleteById(id);
    }

    public Produto editarProduto(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public void alterarProduto(@ModelAttribute("produto") Produto produto){
        produtoRepository.save(produto);
    }
}
