package br.com.alphapires.forum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alphapires.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	Page<Topico> findByCurso_Nome(String nomeCurso, Pageable pageable); // usa underline para navegar entre relacionamentos
	List<Topico> findByCursoNome(String nomeCurso); // outra possibilidade como acima

	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> objterPorNomeCurso(@Param("nomeCurso") String nomeCurso);
}
