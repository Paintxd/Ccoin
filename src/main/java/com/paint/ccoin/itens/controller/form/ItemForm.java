package com.paint.ccoin.itens.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.paint.ccoin.itens.model.Item;
import com.paint.ccoin.itens.repository.ItensRepository;

public class ItemForm {

	@NotEmpty
	@NotNull
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Item converte() {
		return new Item(this.descricao);
	}

	public Item update(Long id, ItensRepository itensRepo) {
		Item item = itensRepo.getOne(id);
		item.setDescricao(descricao);
		
		return item;
	}
	
}
