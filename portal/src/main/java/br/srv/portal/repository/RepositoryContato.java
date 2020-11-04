package br.srv.portal.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.srv.portal.model.Contato;

@Repository
@Transactional
public interface RepositoryContato extends JpaRepository<Contato, Long> {
	
	@Transactional(readOnly = true)
	default Page<Contato> findByNomePage(String nome, Pageable pageable) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nomeCompleto", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Contato> example = Example.of(new Contato(nome), exampleMatcher);
		return findAll(example, pageable);
	}
	
}
