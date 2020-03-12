package com.paint.ccoin.resgates.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.paint.ccoin.usuarios.model.Usuario;

@Entity
@Table(name = "resgates")
public class Resgate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resgate")
	private List<ResgateItem> itensComprados = new ArrayList<ResgateItem>();

	public Resgate() {
	}

	public Resgate(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Resgate(Usuario usuario, List<ResgateItem> itens) {
		this.usuario = usuario;
		this.itensComprados = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ResgateItem> getItensComprados() {
		return itensComprados;
	}

	public void setItensComprados(List<ResgateItem> itensComprados) {
		this.itensComprados = itensComprados;
	}

	public void addItem(ResgateItem resgateItem) {
		this.itensComprados.add(resgateItem);
	}
	
}
