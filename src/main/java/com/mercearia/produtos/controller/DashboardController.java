package com.mercearia.produtos.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mercearia.produtos.repository.CategoriaRepository;
import com.mercearia.produtos.repository.ProdutoRepository;


@Controller
public class DashboardController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/")
    public String dashBoard(Model model) {
        long totalCategorias = categoriaRepository.count();
        long totalProdutos = produtoRepository.count();

        model.addAttribute("totalCategorias", totalCategorias);
        model.addAttribute("totalProdutos", totalProdutos);

        return "index";
    }
    
}
