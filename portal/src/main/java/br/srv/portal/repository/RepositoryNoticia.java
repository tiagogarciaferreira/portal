package br.srv.portal.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.srv.portal.model.Categoria;
import br.srv.portal.model.Noticia;

@Repository
@Transactional
public interface RepositoryNoticia extends JpaRepository<Noticia, Long>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT *FROM noticia WHERE date(data_cadastro) =:datahoje and disponibilidade = true", nativeQuery = true)
	public List<Noticia> findByDataCadastro(@Param("datahoje") LocalDate datahoje);
	
	@Transactional(readOnly = true)
	public List<Noticia> findTop9ByDisponibilidade(boolean disponibilidade, Sort sort);
	
	@Transactional(readOnly = true)
	default Page<Noticia> findByTituloPage(String titulo, Pageable pageable) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("titulo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Noticia> example = Example.of(new Noticia(titulo), exampleMatcher);
		return findAll(example, pageable);
	}
	
	@Transactional(readOnly = true)
	default Page<Noticia> findByDisponibilidadePage(Boolean disponivel, Pageable pageable) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("disponibilidade", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Noticia> example = Example.of(new Noticia(disponivel), exampleMatcher);
		return findAll(example, pageable);
	}
	
	@Transactional(readOnly = true)
	default Page<Noticia> findByDisponibilidadeCategoriaPage(Boolean disponivel, Long idCategoria, Pageable pageable) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase(true)
				.withMatcher("categoria", ExampleMatcher.GenericPropertyMatchers.exact())
				.withMatcher("disponibilidade", ExampleMatcher.GenericPropertyMatchers.exact());
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		Example<Noticia> example = Example.of(new Noticia(categoria, disponivel), exampleMatcher);
		return findAll(example, pageable);
	}
	
}
