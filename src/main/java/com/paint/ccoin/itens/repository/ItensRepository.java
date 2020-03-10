package com.paint.ccoin.itens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paint.ccoin.itens.model.Item;

public interface ItensRepository extends JpaRepository<Item, Long> {

}
