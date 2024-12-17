
package com.senac.openBarWebPI.service;

import com.senac.openBarWebPI.model.Inventario;
import com.senac.openBarWebPI.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> listarTodos() {
        return inventarioRepository.findAll();
    }

    public Inventario salvar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void atualizarQuantidade(Integer id, int novaQuantidade) {
        Inventario item = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado."));
        item.setQuantidade(novaQuantidade);
        inventarioRepository.save(item);
    }

    public void deletar(Integer id) {
        inventarioRepository.deleteById(id);
    }
}
