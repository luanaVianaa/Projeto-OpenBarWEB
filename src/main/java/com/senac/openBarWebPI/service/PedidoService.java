package com.senac.openBarWebPI.service;

import com.senac.openBarWebPI.DTO.ItemMaisVendidoDTO;
import com.senac.openBarWebPI.model.ItemCardapio;
import com.senac.openBarWebPI.model.ItemPedido;
import com.senac.openBarWebPI.model.Pedido;
import com.senac.openBarWebPI.repository.ItemCardapioRepository;
import com.senac.openBarWebPI.repository.PedidoRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

 public Pedido adicionarItemAoPedido(Integer itemId, int quantidade, LocalDate dataPedido) {
    Optional<Pedido> pedidoOptional = pedidoRepository.findByDataPedidoAndStatus(dataPedido, "EM_ANDAMENTO");

    Pedido pedido;
    if (pedidoOptional.isPresent()) {
        pedido = pedidoOptional.get();  
    } else {
        pedido = new Pedido();
        pedido.setDataPedido(dataPedido);
        pedido.setStatus("EM_ANDAMENTO");
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setValorTotal(0.0); 
        pedido.setItens(new ArrayList<>());  
        pedidoRepository.save(pedido);  
    }

    ItemCardapio itemCardapio = itemCardapioRepository.findById(itemId)
            .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

    if (quantidade <= 0) {
        throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
    }

    
    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setItemCardapio(itemCardapio);
    itemPedido.setQuantidade(quantidade);
    itemPedido.setPedido(pedido);  

    pedido.getItens().add(itemPedido);

    double valorItem = itemCardapio.getPreco() * quantidade;
    pedido.setValorTotal(pedido.getValorTotal() + valorItem);

   
    pedidoRepository.save(pedido);

    return pedido;
}

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public int buscarPedidosDoDia(LocalDate data) {
        return pedidoRepository.countByDataPedido(data);
    }

    public void deletar(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public List<ItemMaisVendidoDTO> findItensMaisVendidosNoMes(int mes, int ano) {
        List<Object[]> resultado = pedidoRepository.findItensMaisVendidosNoMes(mes, ano);

        List<ItemMaisVendidoDTO> itensMaisVendidos = new ArrayList<>();
        for (Object[] obj : resultado) {
            Integer id = (Integer) obj[0];
            String nome = (String) obj[1];
            Integer totalVendido = ((Number) obj[2]).intValue();

            ItemMaisVendidoDTO dto = new ItemMaisVendidoDTO(id, nome, totalVendido);
            itensMaisVendidos.add(dto);
        }

        return itensMaisVendidos;
    }

    
    public long contarPedidosPorData(LocalDate data) {
        return pedidoRepository.countByDataPedido(data); 
    }
}
