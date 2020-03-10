package com.paint.ccoin.usuarios.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paint.ccoin.security.TokenService;
import com.paint.ccoin.usuarios.controller.dto.TokenDto;
import com.paint.ccoin.usuarios.controller.form.LoginForm;
import com.paint.ccoin.usuarios.model.Usuario;
import com.paint.ccoin.usuarios.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.convert();
		
		try {
			Authentication authenticate = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticate);
			
			Optional<Usuario> usuario = userRepo.findByLogin(form.getLogin());
			if(usuario.isPresent()) {
				Usuario userCast = usuario.get();
				return ResponseEntity.ok(new TokenDto(token, "Bearer", userCast.getId()));
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
