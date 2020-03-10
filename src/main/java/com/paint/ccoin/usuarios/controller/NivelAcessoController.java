package com.paint.ccoin.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paint.ccoin.usuarios.model.NivelAcesso;
import com.paint.ccoin.usuarios.repository.NivelAcessoRepository;

@CrossOrigin
@RestController
@RequestMapping("/niveisAcesso")
public class NivelAcessoController {

	@Autowired
	private NivelAcessoRepository acessoRepo;
	
	@GetMapping
	public ResponseEntity<?> niveisAcessox() {
		List<NivelAcesso> niveis = acessoRepo.findAll();
		
		return ResponseEntity.ok(niveis);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> nivelAcesso(@PathVariable Long id) {
		return ResponseEntity.ok(acessoRepo.findById(id));
	}
	
}