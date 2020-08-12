package br.com.alphapires.forum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alphapires.forum.dto.TopicoDto;
import br.com.alphapires.forum.model.Topico;
import br.com.alphapires.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository repository;

	@GetMapping("/nomeCursos")
	public List<TopicoDto> listaTodos(String nomeCurso) {
		
		List<Topico> topicos = new ArrayList<Topico>();
		
		if (Objects.isNull(nomeCurso)) {
			topicos = repository.findAll();
		} else {
			topicos = repository.findByCurso_Nome(nomeCurso);
		}
		
		return TopicoDto.converter(topicos);
	}

	@GetMapping("/{nomeCurso}")
	public List<TopicoDto> lista(String nomeCurso) {
		
		List<Topico> topicos = new ArrayList<Topico>();
		
		if (Objects.nonNull(nomeCurso)) {
			topicos = repository.findByCurso_Nome(nomeCurso);
		} 
		
		return TopicoDto.converter(topicos);
	}


}
