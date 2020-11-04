package br.srv.portal.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.srv.portal.model.Categoria;

@Repository
@Transactional
public interface RepositoryCategoria extends JpaRepository<Categoria, Long> {
	
	@Transactional(readOnly = true)
	default Page<Categoria> findByNomePage(String nome, Pageable pageable) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		Example<Categoria> example = Example.of(categoria, exampleMatcher);
		return findAll(example, pageable);
	}
	
}
