package com.senac.openBarWebPI.controller;

import com.senac.openBarWebPI.DTO.ItemMaisVendidoDTO;

import com.senac.openBarWebPI.model.Pedido;
import com.senac.openBarWebPI.repository.PedidoRepository;
import com.senac.openBarWebPI.service.PedidoService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listar")
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Pedido> adicionar(@Valid @RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(novoPedido);
    }

@PostMapping("/adicionar-item")
public ResponseEntity<Pedido> adicionarItemAoPedido(@RequestParam Integer itemId, @RequestParam int quantidade, @RequestParam String data) {
    try {
        LocalDate dataPedido = LocalDate.parse(data);
        Pedido pedidoAtualizado = pedidoService.adicionarItemAoPedido(itemId, quantidade, dataPedido);
        
        return ResponseEntity.ok(pedidoAtualizado);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}


    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Integer id) {
        pedidoRepository.deleteById(id);
    }

    @GetMapping("/dia/{data}")
    public ResponseEntity<?> buscarPedidosDoDia(@PathVariable String data) {
        try {
            LocalDate dia = LocalDate.parse(data);
            int pedidos = pedidoService.buscarPedidosDoDia(dia);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de data inválido. Use o formato AAAA-MM-DD.");
        }
    }

    @GetMapping("/itens-mais-vendidos")
    public ResponseEntity<List<ItemMaisVendidoDTO>> getItensMaisVendidos(
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now().getMonthValue()}") int mes,
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now().getYear()}") int ano) {
        List<ItemMaisVendidoDTO> itens = pedidoService.findItensMaisVendidosNoMes(mes, ano);
        return ResponseEntity.ok(itens);
    }
    @PutMapping("/concluir/{id}")
public ResponseEntity<Pedido> concluirPedido(@PathVariable Integer id) {
    try {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new Exception("Pedido não encontrado"));
        pedido.setStatus("Concluído");
        pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedido);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

    

}
