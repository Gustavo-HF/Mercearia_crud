package com.mercearia.produtos.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercearia.produtos.model.Produto;
import com.mercearia.produtos.service.DashboardService;


@Controller
public class DashboardController {

   @Autowired
   private DashboardService dashboardService;

    @GetMapping("/")
    public String redirectToDashboard() {
    return "redirect:/dashboard";
}

    @GetMapping("/dashboard")
    public String dashBoard(Model model) {
        dashboardService.menu(model);
        return "index";
    }

    @PostMapping("/dashboard/adicionarProduto")
    public String adicionarProduto(@ModelAttribute Produto novoProduto, @RequestParam Long categoriaId) {
        dashboardService.cadastrar(novoProduto, categoriaId);
        return "redirect:/dashboard";
    }
}
