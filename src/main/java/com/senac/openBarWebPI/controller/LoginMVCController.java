package com.senac.openBarWebPI.controller;

import com.senac.openBarWebPI.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginMVCController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String exibirLogin() {
        return "login"; 
    }

    @PostMapping("/autenticar")
    public String autenticar(@RequestParam String nome, @RequestParam String senha, Model model) {
        if (usuarioService.autenticar(nome, senha)) {
            return "redirect:/";  // Redireciona para a página inicial
        } else {
            model.addAttribute("erro", "Credenciais inválidas. Tente novamente.");
            return "login";  // Retorna para a página de login com mensagem de erro
        }
    }
}


