package com.paint.ccoin.itens.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.paint.ccoin.itens.model.TipoItem;
import com.paint.ccoin.itens.repository.TipoItemRepository;

public class TipoItemForm {

	@NotEmpty
	@NotNull
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoItem converte() {
		return new TipoItem(this.descricao);
	}

	public TipoItem update(Long id, TipoItemRepository tipoRepo) {
		TipoItem tipo = tipoRepo.getOne(id);
		tipo.setDescricao(descricao);
		
		return tipo;
	}
	
}
