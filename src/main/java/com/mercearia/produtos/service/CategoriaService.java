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

    public void adicionarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }


    public void deletar(@RequestParam Long id) {
        categoriaRepository.deleteById(id);
    }

     public Categoria editar(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void alterarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }

}
