package br.srv.portal.repository;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.srv.portal.model.Usuario;

@Repository
@Transactional
public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{

	@Transactional(readOnly = true)
	public Usuario findByEmail(String email);
	
	@Transactional(readOnly = true)
	default Page<Usuario> findByNomePage(String nome, Pageable pageable) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nomeCompleto", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Usuario> example = Example.of(new Usuario(nome), exampleMatcher);
		return findAll(example, pageable);
	}
	
}
