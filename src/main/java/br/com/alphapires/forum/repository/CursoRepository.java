package br.com.alphapires.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alphapires.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nome);

}
