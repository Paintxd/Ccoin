package com.paint.ccoin.resgates.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paint.ccoin.resgates.controller.dto.StatusResgateDto;
import com.paint.ccoin.resgates.model.StatusResgate;
import com.paint.ccoin.resgates.repository.StatusResgateRepository;

@CrossOrigin
@RestController
@RequestMapping("/requerimentos")
public class StatusResgatesController {

	@Autowired
	private StatusResgateRepository statusResgateRepo;
	
	@GetMapping
	public List<StatusResgateDto> getStatusResgates() {
		List<StatusResgate> status = statusResgateRepo.findAll();
		
		List<StatusResgateDto> resgates = new ArrayList<StatusResgateDto>();
		
		status.forEach(e -> {
			resgates.add( new StatusResgateDto(e));
		});
		
		return resgates;
	}
	
}
