package com.paint.ccoin.itens.controller.form;

import com.paint.ccoin.itens.model.Estoque;
import com.paint.ccoin.itens.repository.EstoqueRepository;

public class QuantiaEstoqueForm {

	private Integer quantia;

	public Integer getQuantia() {
		return quantia;
	}

	public void setQuantia(Integer quantia) {
		this.quantia = quantia;
	}

	public Estoque updateEstoqueDisponivel(Long id, EstoqueRepository repo) {
		Estoque estoque = repo.getOne(id);
		estoque.setQuantiaDisponivel(quantia);
		
		return estoque;
	}
	
	public Estoque updateEstoqueReservado(Long id, EstoqueRepository repo) {
		Estoque estoque = repo.getOne(id);
		estoque.setQuantiaReservado(quantia);
		
		return estoque;
	}
	
}
