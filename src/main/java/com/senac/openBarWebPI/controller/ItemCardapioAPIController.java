package com.senac.openBarWebPI.controller;

import com.senac.openBarWebPI.model.ItemCardapio;
import com.senac.openBarWebPI.service.ItemCardapioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/itens")
public class ItemCardapioAPIController {

    private static final Logger logger = LoggerFactory.getLogger(ItemCardapioAPIController.class);

    private final ItemCardapioService itemCardapioService;

    @Autowired
    public ItemCardapioAPIController(ItemCardapioService itemCardapioService) {
        this.itemCardapioService = itemCardapioService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionarItem(@RequestBody ItemCardapio itemCardapio) {
        try {
            ItemCardapio novoItem = itemCardapioService.adicionarItem(itemCardapio);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
        } catch (Exception e) {
            logger.error("Erro ao adicionar item no cardápio", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao adicionar item: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ItemCardapio>> listarItensCardapio() {
        try {
            List<ItemCardapio> itens = itemCardapioService.listarTodos();
            return ResponseEntity.ok(itens);
        } catch (Exception e) {
            logger.error("Erro ao listar itens do cardápio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Object> removerItem(@PathVariable Integer id) {
        try {
            itemCardapioService.removerItem(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Erro ao remover item com id " + id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover item: " + e.getMessage());
        }
    }
}
