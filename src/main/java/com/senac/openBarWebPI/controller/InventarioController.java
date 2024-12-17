package com.senac.openBarWebPI.controller;

import com.senac.openBarWebPI.model.Inventario;
import com.senac.openBarWebPI.repository.InventarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping("/listar")
    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    @PostMapping("/adicionar")
    public Inventario adicionar(@Valid @RequestBody Inventario inventario) {
    return inventarioRepository.save(inventario);
}


    @PutMapping("/atualizar/{id}")
    public Inventario atualizar(@PathVariable Integer id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        return inventarioRepository.save(inventario);
    }

   @DeleteMapping("/deletar/{id}")
public ResponseEntity<Void> deletar(@PathVariable Integer id) {
    if (inventarioRepository.existsById(id)) {
        inventarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
}
