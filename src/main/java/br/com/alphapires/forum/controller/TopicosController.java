package br.com.alphapires.forum.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alphapires.forum.controller.request.TopicoRequest;
import br.com.alphapires.forum.dto.TopicoDto;
import br.com.alphapires.forum.model.Topico;
import br.com.alphapires.forum.repository.CursoRepository;
import br.com.alphapires.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> listaTodos(String nomeCurso) {
		
		List<Topico> topicos = new ArrayList<Topico>();
		
		if (Objects.isNull(nomeCurso)) {
			topicos = topicoRepository.findAll();
		} else {
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		}
		
		return TopicoDto.converter(topicos);
	}

	@GetMapping("/{nomeCurso}")
	public List<TopicoDto> lista(String nomeCurso) {
		
		List<Topico> topicos = new ArrayList<Topico>();
		
		if (Objects.nonNull(nomeCurso)) {
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		} 
		
		return TopicoDto.converter(topicos);
	}

	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoRequest request, UriComponentsBuilder uriBuilder) {
		Topico topico= request.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
}
