package com.paint.ccoin.resgates.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.paint.ccoin.itens.model.Estoque;

@Entity
@Table(name = "itens_resgates")
public class ResgateItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Estoque estoque;

	private Integer quantidade;

	@ManyToOne(fetch = FetchType.EAGER)
	private Resgate resgate;

	public ResgateItem() {
	}
	
	public ResgateItem(Long id, Estoque estoque, Integer quantidade, Resgate resgate) {
		this.id = id;
		this.estoque = estoque;
		this.quantidade = quantidade;
		this.resgate = resgate;
	}

	public ResgateItem(Estoque estoque, Integer quantidade, Resgate resgate) {
		this.estoque = estoque;
		this.quantidade = quantidade;
		this.resgate = resgate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Resgate getResgate() {
		return resgate;
	}

	public void setResgate(Resgate resgate) {
		this.resgate = resgate;
	}

}
