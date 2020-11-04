package br.srv.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.srv.portal.model.Permissao;

@Repository
@Transactional
public interface RepositoryPermissao extends JpaRepository<Permissao, Long>{

	
}
