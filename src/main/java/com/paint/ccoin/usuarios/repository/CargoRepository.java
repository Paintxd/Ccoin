package com.paint.ccoin.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paint.ccoin.usuarios.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

	Cargo findByDescricaoCargo(String descricaoCargo);

}
