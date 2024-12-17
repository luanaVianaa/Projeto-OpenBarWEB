/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.openBarWebPI.DTO;

import java.util.List;

public class PedidoRequest {
    private String nomeCliente;
    private Double total;
    private List<ItemPedidoRequest> itens;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Double getTotal() {
        return total;
    }

    public PedidoRequest() {
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ItemPedidoRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoRequest> itens) {
        this.itens = itens;
    }

   
}
