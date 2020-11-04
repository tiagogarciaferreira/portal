package br.srv.portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.srv.portal.model.HomeInfo;
import br.srv.portal.repository.RepositoryCategoria;
import br.srv.portal.repository.RepositoryContato;
import br.srv.portal.repository.RepositoryImagem;
import br.srv.portal.repository.RepositoryNewsletter;
import br.srv.portal.repository.RepositoryNoticia;
import br.srv.portal.repository.RepositoryUsuario;

@Service
public class ServiceDashboard {
	
	private HomeInfo homeInfo = new HomeInfo();
	@Autowired
	private RepositoryCategoria repositoryCategoria;
	@Autowired
	private RepositoryNoticia repositoryNoticia;
	@Autowired
	private RepositoryContato repositoryContato;
	@Autowired
	private RepositoryNewsletter repositoryNewsletter;
	@Autowired
	private RepositoryUsuario repositoryUsuario;
	@Autowired
	private RepositoryImagem repositoryImagem;
	
	public HomeInfo getHomeInfo(){
		homeInfo.setNumeroCategoria(repositoryCategoria.count());
		homeInfo.setNumeroContato(repositoryContato.count());
		homeInfo.setNumeroNewsLetter(repositoryNewsletter.count());
		homeInfo.setNumeroNoticia(repositoryNoticia.count());
		homeInfo.setNumeroUsuario(repositoryUsuario.count());
		homeInfo.setNumeroImagem(repositoryImagem.count());
		return homeInfo;	
	}
	
}
