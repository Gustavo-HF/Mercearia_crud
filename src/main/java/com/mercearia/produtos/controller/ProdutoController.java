package com.mercearia.produtos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercearia.produtos.model.Produto;
import com.mercearia.produtos.repository.ProdutoRepository;



@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public String listaDeProdutos(Model model, @RequestParam(defaultValue="0")int page ) {
        int pageSize = 3;
        Pageable configuracaoPageable = PageRequest.of(page, pageSize);
        Page<Produto> paginaProduto = produtoRepository.findAll(configuracaoPageable);
                                                                         
        model.addAttribute("paginaProduto", paginaProduto);
        model.addAttribute("produto", new Produto());

        return "produto";
    }

    @PostMapping("/adicionarProduto")
    public String adicionarProduto(@ModelAttribute Produto produto){ 
        produtoRepository.save(produto);
        return "redirect:/";
    }
    @PostMapping("/excluirProduto")
    public String excluirProduto(@RequestParam Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/editarProduto/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto != null) {
            model.addAttribute("produto", produto);
            return "atualizarProduto";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping("/alterarProduto/{id}")
    public String alterarCategoria(@ModelAttribute("produto") Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/";
    }
    
    
    
}
