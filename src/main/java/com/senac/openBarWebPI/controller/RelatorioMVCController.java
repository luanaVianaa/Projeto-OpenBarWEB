
package com.senac.openBarWebPI.controller;

import com.senac.openBarWebPI.DTO.ItemMaisVendidoDTO;
import com.senac.openBarWebPI.service.PedidoService;
import com.senac.openBarWebPI.service.RelatorioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioMVCController {

    @Autowired
    private RelatorioService relatorioService;
 
   @Autowired
    private PedidoService pedidoService;

    @GetMapping("/vendas")
    public String gerarRelatorioVendas(Model model, int mes, int ano) {
        List<ItemMaisVendidoDTO> pedidos = pedidoService.findItensMaisVendidosNoMes(mes, ano);
        model.addAttribute("pedidos", pedidos);
        return "relatoriosVendas";
    }

}
