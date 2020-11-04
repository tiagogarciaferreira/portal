package br.srv.portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.srv.portal.model.TipoAcesso;
import br.srv.portal.repository.RepositoryTipoAcesso;

@Service
public class ServiceTipoAcesso {

	@Autowired
	private RepositoryTipoAcesso repositoryTipoAcesso;
	
	public Iterable<TipoAcesso> listar(){
		return repositoryTipoAcesso.findAll();
	}
	
}
