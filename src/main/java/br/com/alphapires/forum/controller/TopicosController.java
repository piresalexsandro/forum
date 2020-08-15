package br.com.alphapires.forum.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alphapires.forum.controller.request.AtualizarTopicoRequest;
import br.com.alphapires.forum.controller.request.TopicoRequest;
import br.com.alphapires.forum.dto.DetalhesTopicoDto;
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

	@GetMapping("/nome/{nomeCurso}")
	public List<TopicoDto> lista(String nomeCurso) {

		List<Topico> topicos = new ArrayList<Topico>();

		if (Objects.nonNull(nomeCurso)) {
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		}

		return TopicoDto.converter(topicos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoRequest request,
			UriComponentsBuilder uriBuilder) {
		Topico topico = request.converter(cursoRepository);
		topicoRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable("id") Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);

		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional // com esta notacao nao precisamos do .save
	public ResponseEntity<TopicoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid AtualizarTopicoRequest request) {

		Optional<Topico> optinal = topicoRepository.findById(id);

		if (optinal.isPresent()) {
			Topico topico = request.atualizar(id, topicoRepository);
			topicoRepository.save(topico);
			return ResponseEntity.ok(new TopicoDto(topico));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity remover(@PathVariable("id") Long id) {
		Optional<Topico> optinal = topicoRepository.findById(id);

		if (optinal.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
