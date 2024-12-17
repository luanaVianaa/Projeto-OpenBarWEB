package com.senac.openBarWebPI.controller;

import com.senac.openBarWebPI.DTO.RelatorioDTO;
import com.senac.openBarWebPI.service.RelatorioService;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioAPIController {

    @Autowired
    private RelatorioService relatorioService;

 @GetMapping("/diario")
    public ResponseEntity<?> gerarRelatorioDiario(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String data) {
        try {
            RelatorioDTO relatorio = relatorioService.gerarRelatorioDiario(data);
            return ResponseEntity.ok(relatorio);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    // Gerar relatório mensal
    @GetMapping("/mensal")
    public ResponseEntity<RelatorioDTO> gerarRelatorioMensal(@RequestParam int mes, @RequestParam int ano) {
        try {
            // Chama o serviço para gerar o relatório mensal
            RelatorioDTO relatorio = relatorioService.gerarRelatorioMensal(mes, ano);
            return ResponseEntity.ok(relatorio);  // Retorna o relatório mensal
        } catch (Exception e) {
            // Em caso de erro, retorna uma resposta com erro 400 (Bad Request)
            return ResponseEntity.badRequest().build();
        }
    }
}
