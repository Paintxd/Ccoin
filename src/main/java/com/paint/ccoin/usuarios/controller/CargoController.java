package com.paint.ccoin.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paint.ccoin.usuarios.model.Cargo;
import com.paint.ccoin.usuarios.repository.CargoRepository;

@CrossOrigin
@RestController
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoRepository cargoRepo;

	@GetMapping
	public ResponseEntity<?> cargos() {
		List<Cargo> cargos = cargoRepo.findAll();
		
		return ResponseEntity.ok(cargos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> cargo(@PathVariable Long id) {
		return ResponseEntity.ok(cargoRepo.findById(id));
	}
	
}
