/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.openBarWebPI.controller;

import com.senac.openBarWebPI.model.Inventario;
import com.senac.openBarWebPI.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/inventario")
public class InventarioMVCController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/cadastro")
    public String exibirCadastro() {
        return "inventario"; 
    }

    @PostMapping("/adicionar")
    public String cadastrarInventario(@ModelAttribute Inventario inventario) {
        inventarioService.salvar(inventario);
        return "redirect:/inventario"; 
    }
}
