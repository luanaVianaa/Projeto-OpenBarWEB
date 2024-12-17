

package com.senac.openBarWebPI.service;

import com.senac.openBarWebPI.model.ItemCardapio;
import com.senac.openBarWebPI.model.ItemCardapio.TipoItem;
import com.senac.openBarWebPI.repository.ItemCardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCardapioService {

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    private void validarItem(ItemCardapio item) {
        if (item.getPreco() == null || item.getPreco() <= 0) {
            throw new IllegalArgumentException("O valor do item deve ser maior que zero.");
        }
    }

  
    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public ItemCardapio adicionarItem(ItemCardapio itemCardapio) {
        if (itemCardapio.getNome() == null || itemCardapio.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do item é obrigatório.");
        }
        if (itemCardapio.getPreco() == null || itemCardapio.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço do item deve ser maior que zero.");
        }
        if (itemCardapio.getTipo() == null) {
            throw new IllegalArgumentException("Tipo do item é obrigatório.");
        }
        return itemCardapioRepository.save(itemCardapio);
    }


    public ItemCardapio atualizarItem(Integer id, ItemCardapio itemAtualizado) {
        Optional<ItemCardapio> itemExistente = itemCardapioRepository.findById(id);
        if (itemExistente.isPresent()) {
            ItemCardapio item = itemExistente.get();
            item.setNome(itemAtualizado.getNome());
            item.setDescricao(itemAtualizado.getDescricao());
            item.setPreco(itemAtualizado.getPreco());
            validarItem(item);
            return itemCardapioRepository.save(item);
        } else {
            throw new RuntimeException("Item não encontrado com ID: " + id);
        }
    }

    
    public List<ItemCardapio> listarTodos() {
        return itemCardapioRepository.findAll();
    }

   
    public ItemCardapio buscarPorId(Integer id) {
        return itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));
    }

    public void removerItem(Integer id) {
        if (!itemCardapioRepository.existsById(id)) {
            throw new RuntimeException("Item não encontrado com ID: " + id);
        }
        itemCardapioRepository.deleteById(id);
    }

    public List<ItemCardapio> listarPorTipo(TipoItem tipo) {
        return itemCardapioRepository.findByTipo(tipo);
    }

   
    public List<ItemCardapio> listarPorNome(String nome) {
        return itemCardapioRepository.findByNomeContainingIgnoreCase(nome);
    }
}
