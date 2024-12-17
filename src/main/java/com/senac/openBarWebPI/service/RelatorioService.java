package com.senac.openBarWebPI.service;


import com.senac.openBarWebPI.DTO.RelatorioDTO;
import com.senac.openBarWebPI.repository.PedidoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RelatorioService {

    @Autowired
    private PedidoRepository pedidoRepository;

public RelatorioDTO gerarRelatorioDiario(String data) {
    try {
        // Conversão da string para LocalDate
        LocalDate dataPedido = LocalDate.parse(data);

        // Obtém o faturamento diário
        BigDecimal faturamentoDia = pedidoRepository.findFaturamentoDia(
                dataPedido.atStartOfDay(), dataPedido.atTime(23, 59, 59));

        // Buscar os itens mais vendidos do dia (ajuste a consulta, se necessário)
        List<Object[]> itensVendidos = pedidoRepository.findItensMaisVendidosNoMes(
                dataPedido.getMonthValue(), dataPedido.getYear());

        // Inicializa variáveis de resposta
        String itemMaisVendido = "Sem item";
        int quantidadeVendida = 0;

        if (!itensVendidos.isEmpty()) {
            Object[] primeiroItem = itensVendidos.get(0);
            itemMaisVendido = (String) primeiroItem[1]; // Nome do item
            quantidadeVendida = ((Number) primeiroItem[2]).intValue(); // Quantidade
        }

        // Retorna o DTO preenchido
        return new RelatorioDTO(itemMaisVendido, quantidadeVendida, faturamentoDia, faturamentoDia);

    } catch (Exception e) {
        throw new IllegalArgumentException("Erro ao gerar relatório diário: " + e.getMessage(), e);
    }
}


    // Gerar Relatório Mensal
    public RelatorioDTO gerarRelatorioMensal(int mes, int ano) {
        BigDecimal faturamentoMes = pedidoRepository.findFaturamentoDia(
                LocalDate.of(ano, mes, 1).atStartOfDay(),
                LocalDate.of(ano, mes, 1).withDayOfMonth(31).atTime(23, 59, 59));

        // Encontrar o item mais vendido no mês
        List<Object[]> itensVendidos = pedidoRepository.findItensMaisVendidosNoMes(mes, ano);
        
        String itemMaisVendido = "Sem item";
        int quantidadeVendida = 0;
        
        if (!itensVendidos.isEmpty()) {
            itemMaisVendido = (String) itensVendidos.get(0)[1]; // Nome do item
            quantidadeVendida = ((Long) itensVendidos.get(0)[2]).intValue(); // Quantidade
        }

        // Retorna o DTO
        return new RelatorioDTO(itemMaisVendido, quantidadeVendida, faturamentoMes, faturamentoMes);
    }
}
