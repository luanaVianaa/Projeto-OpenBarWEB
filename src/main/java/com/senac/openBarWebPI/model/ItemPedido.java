package com.senac.openBarWebPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private ItemCardapio itemCardapio;

    private int quantidade;
    
    @ManyToOne(cascade = CascadeType.ALL) // Isso pode ajudar a salvar o relacionamento corretamente
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido pedido;

    public Double getPreco() {
        return itemCardapio.getPreco();
    }
}
