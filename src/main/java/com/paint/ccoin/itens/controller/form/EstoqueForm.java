package com.paint.ccoin.itens.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.paint.ccoin.itens.model.Estoque;
import com.paint.ccoin.itens.model.Item;
import com.paint.ccoin.itens.model.TipoItem;
import com.paint.ccoin.itens.repository.EstoqueRepository;
import com.paint.ccoin.itens.repository.ItensRepository;
import com.paint.ccoin.itens.repository.TipoItemRepository;

public class EstoqueForm {
	@NotNull
	private Long idItem;
	@NotNull
	private Long idTipoItem;
	@NotNull
	private Integer quantiaDisponivel;
	@NotNull
	private Integer valor;

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Long getIdTipoItem() {
		return idTipoItem;
	}

	public void setIdTipoItem(Long idTipoItem) {
		this.idTipoItem = idTipoItem;
	}

	public Integer getQuantiaDisponivel() {
		return quantiaDisponivel;
	}

	public void setQuantiaDisponivel(Integer quantiaDisponivel) {
		this.quantiaDisponivel = quantiaDisponivel;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Estoque converte(EstoqueRepository estoqueRepo, ItensRepository itemRepo, TipoItemRepository tipoRepo) {
		Optional<Estoque> estoque = estoqueRepo.findByItemIdAndTipoItemId(idItem, idTipoItem);

		if (estoque.isPresent()) {
			return null;
		} else {
			Item item = itemRepo.getOne(idItem);
			TipoItem tipo = tipoRepo.getOne(idTipoItem);

			return new Estoque(item, tipo, quantiaDisponivel, valor);
		}
	}

}
