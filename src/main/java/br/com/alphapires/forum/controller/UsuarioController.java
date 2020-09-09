package br.com.alphapires.forum.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alphapires.forum.model.Usuario;
import br.com.alphapires.forum.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@Cacheable(value = "listaDeUsuarios")
	public Page<Usuario> listaTodos(@RequestParam(required = false) String nomeCurso, 
			@PageableDefault(direction = Direction.DESC, sort = "id") Pageable paginacao) {
		
		if (Objects.isNull(nomeCurso)) {
			Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
			return (Page<Usuario>) usuarios.get();
		}
		return null; 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> detalhar(@PathVariable("id") Long id) {
		Usuario usuario = usuarioRepository.findById(id).get();

		if (Objects.nonNull(usuario)) {
			return ResponseEntity.ok(usuario);
		}
		return ResponseEntity.notFound().build();
	}
}
