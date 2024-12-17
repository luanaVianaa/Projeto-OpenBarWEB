package com.senac.openBarWebPI.repository;

import com.senac.openBarWebPI.DTO.ItemMaisVendidoDTO;
import com.senac.openBarWebPI.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Optional<Pedido> findByStatus(String status);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.dataPedido = :data")
    int countByDataPedido(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.dataPedido BETWEEN :inicio AND :fim")
    int countPedidosPorIntervalo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    public Optional<Pedido> findByDataPedidoAndStatus(LocalDate dataPedido, String status);

    @Query("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.dataCriacao BETWEEN :inicio AND :fim")
    BigDecimal findFaturamentoDia(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    @Query("SELECT DAY(p.dataCriacao), SUM(p.valorTotal) FROM Pedido p "
         + "WHERE MONTH(p.dataCriacao) = :mes AND YEAR(p.dataCriacao) = :ano "
         + "GROUP BY DAY(p.dataCriacao) "
         + "ORDER BY DAY(p.dataCriacao)")
    List<Object[]> findFaturamentoDiarioNoMes(@Param("mes") int mes, @Param("ano") int ano);

    @Query(value = "SELECT i.id, i.nome, SUM(ip.quantidade) FROM item_cardapio i "
            + "JOIN itens_pedido ip ON i.id = ip.item_cardapio_id "
            + "WHERE MONTH(ip.pedido_data_pedido) = :mes AND YEAR(ip.pedido_data_pedido) = :ano "
            + "GROUP BY i.id, i.nome ORDER BY SUM(ip.quantidade) DESC", nativeQuery = true)
    List<Object[]> findItensMaisVendidosNoMes(@Param("mes") int mes, @Param("ano") int ano);

    // Buscar pedidos por intervalo
    @Query("SELECT p FROM Pedido p WHERE p.dataCriacao BETWEEN :inicio AND :fim")
    List<Pedido> findPedidosPorIntervalo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
