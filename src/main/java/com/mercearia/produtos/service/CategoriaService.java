package com.mercearia.produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercearia.produtos.model.Categoria;
import com.mercearia.produtos.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public String adicionarCategoria(Categoria categoria, Model model, @RequestParam(defaultValue = "0") int page) {
        boolean valido = validarAdicaoCategoria(categoria, model, page);
        
        if(valido == false){
            return "redirect:/categoria";
        }
        categoriaRepository.save(categoria);
        return "";
       
    }

    private boolean  validarAdicaoCategoria(@ModelAttribute Categoria categoria, Model model, @RequestParam(defaultValue = "0") int page) {
        boolean erro = false;

        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            model.addAttribute("mensagemErro", "Nome da categoria não pode estar vazio");
            erro = true;
        }

        if (categoria.getTipo() == null || categoria.getTipo().trim().isEmpty()) {
            model.addAttribute("mensagemErro", "Tipo da categoria não pode estar vazio");
            erro = true;
        }

        if (erro) {
            model.addAttribute("paginaCategoria", categoriaRepository.findAll(PageRequest.of(page, 3)));
            model.addAttribute("categoria", categoria);
        }
        
        return !erro;
    }

    public void deletar(@RequestParam Long id) {
        categoriaRepository.deleteById(id);
    }

     public Categoria editar(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void alterarCategoria(@ModelAttribute("categoria") Categoria categoria){
        categoriaRepository.save(categoria);
    }

}
