package com.paint.ccoin.resgates.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.paint.ccoin.exception.FoundsException;
import com.paint.ccoin.itens.repository.EstoqueRepository;
import com.paint.ccoin.resgates.controller.dto.StatusResgateDto;
import com.paint.ccoin.resgates.controller.form.ResgateForm;
import com.paint.ccoin.resgates.model.Resgate;
import com.paint.ccoin.resgates.model.StatusResgate;
import com.paint.ccoin.resgates.model.TiposStatus;
import com.paint.ccoin.resgates.repository.ResgateItemRepository;
import com.paint.ccoin.resgates.repository.ResgateRepository;
import com.paint.ccoin.resgates.repository.StatusResgateRepository;
import com.paint.ccoin.resgates.repository.TiposStatusRepository;
import com.paint.ccoin.service.MailSenderService;
import com.paint.ccoin.usuarios.model.Usuario;
import com.paint.ccoin.usuarios.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/resgate")
public class ResgatesController {

	@Autowired
	private StatusResgateRepository statusResgateRepo;
	
	@Autowired
	private EstoqueRepository estoqueRepo;
	
	@Autowired
	private ResgateRepository resgateRepo;
	
	@Autowired
	private TiposStatusRepository tipoStatusRepo;
	
	@Autowired
	private ResgateItemRepository resgateItemRepo;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private MailSenderService mailSend;
	
	private Double valorCompra = 0.0;
	
	@PostMapping
	@Transactional
	public ResponseEntity<StatusResgateDto> resgatar(@RequestBody @Valid List<ResgateForm> form, 
			UriComponentsBuilder uriBuilder) throws FoundsException {
		try {
			Usuario comprador = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario usuario = userRepo.findByNome(comprador.getNome());
			Resgate resgate = new Resgate(comprador);
			form.forEach(faz -> {
				resgate.addItem(faz.converter(estoqueRepo, resgateItemRepo, resgate));
				valorCompra += faz.preco(estoqueRepo);
			});
			usuario.Saca(valorCompra);
			resgateRepo.save(resgate);
			
			TiposStatus status = tipoStatusRepo.getOne(Long.valueOf(1));
	
			StatusResgate statusResgate = new StatusResgate(resgate, status);
			statusResgateRepo.save(statusResgate);
			
			URI uri = uriBuilder.path("/resgate/{id}").buildAndExpand(statusResgate.getId()).toUri();
			
			mailSend.compraMail(usuario.getEmail(), valorCompra);
			
			return ResponseEntity.created(uri).body(new StatusResgateDto(statusResgate));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
}
