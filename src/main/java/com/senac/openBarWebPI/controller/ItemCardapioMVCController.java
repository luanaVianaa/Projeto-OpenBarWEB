package com.senac.openBarWebPI.controller;

import com.senac.openBarWebPI.model.ItemCardapio;
import com.senac.openBarWebPI.service.ItemCardapioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/api/item-cardapio")
public class ItemCardapioMVCController {

    @Autowired
    private ItemCardapioService itemCardapioService;

    @GetMapping("/listar")
    public String listarItens(Model model) {
        List<ItemCardapio> itens = itemCardapioService.listarTodos();
        model.addAttribute("itensCardapio", itens);
        return "listaCardapio";  
    }

 
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("itemCardapio", new ItemCardapio());
        return "PaginaCadastrarCardapio";  
    }

 
    @PostMapping("/adicionar")
    public String adicionarItem(@ModelAttribute ItemCardapio itemCardapio, Model model) {
        try {
            itemCardapioService.adicionarItem(itemCardapio);
            return "redirect:/item-cardapio/listar"; 
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao adicionar item ao cardápio.");
            return "listaCardapio";  
        }
    }

    @DeleteMapping("/remover/{id}")
    public String removerItem(@PathVariable("id") Integer id) {
        try {
            itemCardapioService.removerItem(id);
            return "redirect:/item-cardapio/listar";  
        } catch (Exception e) {
            return "redirect:/item-cardapio/listar?erro=Erro ao remover item";  
        }
    }
}
