package com.senac.openBarWebPI.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String itemMaisVendido;
    private int quantidadeVendida;
    private BigDecimal faturamentoDia;
    private BigDecimal faturamentoMes;

    
}
