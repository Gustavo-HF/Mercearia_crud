package com.mercearia.produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercearia.produtos.model.Categoria;
import com.mercearia.produtos.model.Produto;
import com.mercearia.produtos.repository.CategoriaRepository;
import com.mercearia.produtos.repository.ProdutoRepository;

@Service
public class DashboardService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public void menu(Model model){
        model.addAttribute("totalCategorias", categoriaRepository.count());
        model.addAttribute("totalProdutos", produtoRepository.count());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("novoProduto", new Produto());
    }

    public void cadastrar(@ModelAttribute Produto novoProduto, @RequestParam Long categoriaId){
        Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
        novoProduto.setCategoria(categoria);
        produtoRepository.save(novoProduto);
    }
}
