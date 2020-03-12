package com.paint.ccoin.resgates.controller.form;

import com.paint.ccoin.itens.model.Estoque;
import com.paint.ccoin.itens.repository.EstoqueRepository;
import com.paint.ccoin.resgates.model.Resgate;
import com.paint.ccoin.resgates.model.ResgateItem;
import com.paint.ccoin.resgates.repository.ResgateItemRepository;

public class ResgateForm {

	private Long idEstoque;
	private Integer quantidade;

	public Long getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(Long idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ResgateItem converter(EstoqueRepository estoqueRepo, ResgateItemRepository resgateItemRepo, Resgate resgate) {
		try {
			Estoque estoque = estoqueRepo.getOne(idEstoque);
			estoque.reserva(quantidade);
	
			ResgateItem resgateItem = new ResgateItem(estoque, quantidade, resgate);
			resgateItemRepo.save(resgateItem);
			
			return resgateItem;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Double preco(EstoqueRepository estoqueRepo) {
		Estoque estoque = estoqueRepo.getOne(idEstoque);
		return Double.valueOf(estoque.getValor() * quantidade);
	}

}
