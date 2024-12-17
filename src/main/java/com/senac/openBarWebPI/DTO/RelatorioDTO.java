
package com.senac.openBarWebPI.DTO;

import java.util.List;


import java.math.BigDecimal;

public class RelatorioDTO {

    private String itemMaisVendido;
    private int quantidadeVendida;
    private BigDecimal faturamentoDia;
    private BigDecimal faturamentoMes;

    public RelatorioDTO(String itemMaisVendido, int quantidadeVendida, BigDecimal faturamentoDia, BigDecimal faturamentoMes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters e Setters
    public String getItemMaisVendido() {
        return itemMaisVendido;
    }

    public void setItemMaisVendido(String itemMaisVendido) {
        this.itemMaisVendido = itemMaisVendido;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public BigDecimal getFaturamentoDia() {
        return faturamentoDia;
    }

    public void setFaturamentoDia(BigDecimal faturamentoDia) {
        this.faturamentoDia = faturamentoDia;
    }

    public BigDecimal getFaturamentoMes() {
        return faturamentoMes;
    }

    public void setFaturamentoMes(BigDecimal faturamentoMes) {
        this.faturamentoMes = faturamentoMes;
    }

  
}
