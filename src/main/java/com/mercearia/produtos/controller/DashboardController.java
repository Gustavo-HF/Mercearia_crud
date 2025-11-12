package com.mercearia.produtos.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercearia.produtos.model.Categoria;
import com.mercearia.produtos.model.Produto;
import com.mercearia.produtos.repository.CategoriaRepository;
import com.mercearia.produtos.repository.ProdutoRepository;


@Controller
public class DashboardController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/")
    public String redirectToDashboard() {
    return "redirect:/dashboard";
}

    @GetMapping("/dashboard")
    public String dashBoard(Model model) {
        model.addAttribute("totalCategorias", categoriaRepository.count());
        model.addAttribute("totalProdutos", produtoRepository.count());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("novoProduto", new Produto());
        return "index";
    }

    @PostMapping("/dashboard/adicionarProduto")
    public String adicionarProduto(@ModelAttribute Produto novoProduto, @RequestParam Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
        novoProduto.setCategoria(categoria);
        produtoRepository.save(novoProduto);
        return "redirect:/dashboard";
    }
}
