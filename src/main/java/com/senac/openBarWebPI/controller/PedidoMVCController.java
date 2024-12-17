/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.openBarWebPI.controller;


import com.senac.openBarWebPI.model.ItemCardapio;
import com.senac.openBarWebPI.model.Pedido;
import com.senac.openBarWebPI.service.ItemCardapioService;
import com.senac.openBarWebPI.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/pedido")
public class PedidoMVCController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItemCardapioService itemCardapioService;

    @GetMapping("/cadastro")
    public String exibirCadastro(Model model) {
        List<ItemCardapio> cardapio = itemCardapioService.listarTodos();
        model.addAttribute("cardapio", cardapio);
        return "cadastroPedido"; 
    }

    @PostMapping("/adicionar")
    public String cadastrarPedido(@ModelAttribute Pedido pedido) {
        pedidoService.salvar(pedido);
        return "redirect:/listarPedidos"; 
    }
      @GetMapping("/lista")
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.listarTodos();
        model.addAttribute("pedidos", pedidos);
        return "listar-pedidos"; 
    }
}