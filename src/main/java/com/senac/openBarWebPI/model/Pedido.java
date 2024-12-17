package com.senac.openBarWebPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataCriacao;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    private Double valorTotal;
    private String status;

    private String nomeCliente;  
    private String telefoneCliente;  

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference 
    private List<ItemPedido> itens;  

    public boolean isEmAndamento() {
        return "EM_ANDAMENTO".equals(this.status);  
    }
}
