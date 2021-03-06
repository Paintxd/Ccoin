package com.paint.ccoin.usuarios.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.paint.ccoin.usuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Page<Usuario> findByNome(String nomeUsuario, Pageable paginacao);
	
	Optional<Usuario> findByLogin(String login);
	
	Usuario findByNome(String nome);
	
}
