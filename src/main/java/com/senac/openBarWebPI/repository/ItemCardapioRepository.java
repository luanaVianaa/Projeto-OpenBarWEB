package com.senac.openBarWebPI.repository;

import com.senac.openBarWebPI.model.ItemCardapio;
import com.senac.openBarWebPI.model.ItemCardapio.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Integer> {

    
    List<ItemCardapio> findByTipo(TipoItem tipo);

   
    List<ItemCardapio> findByNomeContainingIgnoreCase(String nome);
}
