package com.paint.ccoin.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paint.ccoin.usuarios.model.NivelAcesso;

public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, Long> {

	NivelAcesso findByDescricaoAcesso(String descricaoAcesso);

}
