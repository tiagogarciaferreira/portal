package br.srv.portal.repository;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.srv.portal.model.Newsletter;

@Repository
@Transactional
public interface RepositoryNewsletter extends JpaRepository<Newsletter, Long> {

	@Transactional(readOnly = true)
	public Newsletter findByEmail(String email);
	
	@Transactional(readOnly = true)
	public List<Newsletter> findByStatus(Boolean status);
	
	@Transactional(readOnly = true)
	default Page<Newsletter> findByEmailPage(String email, Pageable pageable) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Newsletter> example = Example.of(new Newsletter(email), exampleMatcher);
		return findAll(example, pageable);
	}
	
}
