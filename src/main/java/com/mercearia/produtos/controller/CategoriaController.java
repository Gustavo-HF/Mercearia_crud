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

import com.mercearia.produtos.model.Categoria;
import com.mercearia.produtos.repository.CategoriaRepository;




@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categoria")
    public String listaDeCategorias(Model model, @RequestParam(defaultValue="0") int page){

        int pageSize = 3;
        Pageable configuracaoPagina = PageRequest.of(page, pageSize);
        Page<Categoria> paginaCategoria = categoriaRepository.findAll(configuracaoPagina);

        model.addAttribute("paginaCategoria", paginaCategoria);
        model.addAttribute("categoria", new Categoria());

        return "categoria";
    }

    @PostMapping("/adicionarCategoria")
    public String adicionarCategoria(@ModelAttribute Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/";
    }

    @PostMapping("/excluirCategoria")
    public String excluirCategoria(@RequestParam Long id) {
        categoriaRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/editarCategoria/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "atualizarCategoria";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping("/alterarCategoria/{id}")
    public String alterarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/";
    }
    

}
