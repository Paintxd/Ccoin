package com.paint.ccoin.itens.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paint.ccoin.itens.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	Optional<Estoque> findByItemIdAndTipoItemId(Long item, Long tipo);
	
}
