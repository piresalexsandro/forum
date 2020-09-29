/*package br.com.alphapires.forum.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alphapires.forum.model.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // nao muda config padrao
@ActiveProfiles(profiles = "test")
class CursoRepositoryTest {

	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private TestEntityManager manager;
	
	@Test
	public void DeveCarregarCursoPeloNome() {
		String nomeCurso = "HTML 5";
		
		Curso cursoMock = new Curso();
		cursoMock.setNome(nomeCurso);
		cursoMock.setCategoria("Programacao");
		
		manager.persist(cursoMock);
		
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNotNull(curso);
		Assert.assertEquals(nomeCurso, curso.getNome());
	}
	
	@Test
	public void NaoDeveCarregarCursoPeloNome() {
		String nomeCurso = "Fisica Quantica";
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNull(curso);
	}
}
*/