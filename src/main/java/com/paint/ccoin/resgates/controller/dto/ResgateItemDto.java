package com.paint.ccoin.resgates.controller.dto;

import com.paint.ccoin.resgates.model.ResgateItem;

public class ResgateItemDto {

	private String itens;

	private Integer valor;

	private Integer quantia;

	public ResgateItemDto(ResgateItem resgate) {
		this.itens = resgate.getEstoque().getItem().getDescricao() + " "
				+ resgate.getEstoque().getTipoItem().getDescricao();
		this.quantia = resgate.getQuantidade();
		this.valor = resgate.getEstoque().getValor() * resgate.getQuantidade();
	}

	public String getItens() {
		return itens;
	}

	public void setItens(String itens) {
		this.itens = itens;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getQuantia() {
		return quantia;
	}

	public void setQuantia(Integer quantia) {
		this.quantia = quantia;
	}

}
