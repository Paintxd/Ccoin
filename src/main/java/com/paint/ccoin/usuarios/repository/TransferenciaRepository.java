package com.paint.ccoin.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paint.ccoin.usuarios.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}
