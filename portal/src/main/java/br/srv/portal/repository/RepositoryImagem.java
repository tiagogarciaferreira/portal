package br.srv.portal.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.srv.portal.model.Imagem;

@Repository
@Transactional
public interface RepositoryImagem extends JpaRepository<Imagem, Long> {

	@Transactional(readOnly = true)
	public Imagem findByNome(String nome);
	
	@Transactional(readOnly = true)
	default Page<Imagem> findByDataNomePage(String data, Pageable pageable) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Imagem> example = Example.of(new Imagem(data), exampleMatcher);
		return findAll(example, pageable);
	}
	
}
