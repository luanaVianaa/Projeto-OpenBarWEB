/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.openBarWebPI.DTO;

public class ItemMaisVendidoDTO {

    private Integer id;
    private String nome;
    private Integer totalVendido;

    // Construtores
    public ItemMaisVendidoDTO(Integer id, String nome, Integer totalVendido) {
        this.id = id;
        this.nome = nome;
        this.totalVendido = totalVendido;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Integer totalVendido) {
        this.totalVendido = totalVendido;
    }
}