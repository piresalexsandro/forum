package br.com.alphapires.forum.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alphapires.forum.controller.request.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest request){
		System.out.println(request.getEmail());
		System.out.println(request.getSenha());
		
		return ResponseEntity.ok().build();
	}
	
}
