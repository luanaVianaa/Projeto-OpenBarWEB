package com.senac.openBarWebPI.controller;


import com.senac.openBarWebPI.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/")
    public String index(Model model) {
        LocalDate data = LocalDate.now();
        Integer pedidosHoje = pedidoRepository.countByDataPedido(data);
        model.addAttribute("pedidosHoje", pedidosHoje);
        return "index"; // Redireciona para o template da página inicial
    }
}

