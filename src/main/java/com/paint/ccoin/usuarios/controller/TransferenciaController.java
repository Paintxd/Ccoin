package com.paint.ccoin.usuarios.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paint.ccoin.service.MailSenderService;
import com.paint.ccoin.usuarios.controller.dto.TransferenciaDto;
import com.paint.ccoin.usuarios.controller.form.TransferenciaForm;
import com.paint.ccoin.usuarios.model.Transferencia;
import com.paint.ccoin.usuarios.model.Usuario;
import com.paint.ccoin.usuarios.repository.TransferenciaRepository;
import com.paint.ccoin.usuarios.repository.UsuarioRepository;

@RestController
@RequestMapping("/transferir")
public class TransferenciaController {

	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private TransferenciaRepository transfRepo;
	
	@Autowired
	private MailSenderService mailSend;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TransferenciaDto> transfere(@RequestBody TransferenciaForm dadosTransf) {
		try {
			Usuario origem = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario origemJpa = userRepo.findByNome(origem.getNome());
			origemJpa.Saca(dadosTransf.getValor());
			
			Usuario destino = userRepo.findByNome(dadosTransf.getNomeDestinatario());
			destino.Deposita(dadosTransf.getValor());
			try {
			mailSend.origemTransferenciaMail(destino.getEmail(), origem.getEmail(), dadosTransf.getValor());
			mailSend.destinatarioTransferenciaMail(destino.getEmail(), origem.getEmail(), dadosTransf.getValor());
			} catch (Exception e) {
				e.printStackTrace();
			}
			Transferencia transferencia = new Transferencia(origem, destino, dadosTransf.getValor());
			transfRepo.save(transferencia);
			
		return ResponseEntity.ok(new TransferenciaDto(origem, destino, dadosTransf.getValor()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
