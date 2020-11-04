package br.srv.portal.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.srv.portal.constant.E_Mensagem;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.constant.E_Solicitacao;
import br.srv.portal.model.Noticia;
import br.srv.portal.model.Usuario;
import br.srv.portal.repository.RepositoryNoticia;

@Service
public class ServiceNoticia {

	@Autowired
	private RepositoryNoticia repositoryNoticia;
	@Autowired
	private ServiceSession serviceSession;
	@Autowired
	private ServiceUrl serviceUrl;
	@Value("${dominio.base.url}")
	private String DOMINIO;
	
	public Noticia getNoticia(Noticia noticia) {
		return (noticia.getIdNoticia() != null) ? repositoryNoticia.findById(noticia.getIdNoticia()).get() : noticia;
	}
	
	public List<Noticia> listaIndex(){
		return repositoryNoticia.findTop9ByDisponibilidade(true, Sort.by(Direction.DESC, "dataCadastro"));
	}
	
	public Page<Noticia> listar(){
		return repositoryNoticia.findAll(PageRequest.of(0, 10, Sort.by(Direction.DESC, "idNoticia")));
	}

	public Page<Noticia> pesquisar(String tituloBusca, Pageable pageable){
		return (tituloBusca.isEmpty()) ? repositoryNoticia.findAll(pageable) : repositoryNoticia.findByTituloPage(tituloBusca, pageable);
	}
	
	public Page<Noticia> listarTodas(Long categoriaBusca, Pageable pageable){
		return (categoriaBusca == null || categoriaBusca == 0) ? repositoryNoticia.findByDisponibilidadePage(true, pageable) : repositoryNoticia.findByDisponibilidadeCategoriaPage(true, categoriaBusca, pageable);
	}
	
	public String leitura(Model model, Long idnoticia, String token) {
		Optional<Noticia> optionalnoticia = repositoryNoticia.findById(idnoticia);
		if(!serviceUrl.verificar(idnoticia, token)) {
			model.addAttribute("code", 400);
			return E_Redirect.URL_ERRO.get();
		}
		else if(optionalnoticia.get().getDisponibilidade() != true){
			model.addAttribute("code", 404);
			return E_Redirect.URL_ERRO.get();
		}
		model.addAttribute("noticia_ler", optionalnoticia.get());
		model.addAttribute("dominio", DOMINIO + E_Solicitacao.LER_NOTICIA.get() + idnoticia);
		return E_Redirect.URL_NOTICIA_LER.get();
	}
	
	public ModelAndView salvar(Noticia noticia, RedirectAttributes redirectAttributes){
		String mensagem = (noticia.getIdNoticia() == null) ? E_Mensagem.CADASTRO_SUCESSO.get() : E_Mensagem.UPDATE_SUCESSO.get();
		try {
			  if(noticia.getIdNoticia() == null) {
				  noticia.setUsuario(new Usuario(serviceSession.getIdUsuarioLogado()));
				  repositoryNoticia.save(noticia);
				  return new ModelAndView(E_Redirect.URL_MAP_NOTICIA_CADASTRO.get() + mensagem);
			  } 
			  else {
				  Noticia noticiaUpdate = repositoryNoticia.findById(noticia.getIdNoticia()).get();
				  noticiaUpdate.setTitulo(noticia.getTitulo());
				  noticiaUpdate.setCategoria(noticia.getCategoria());
				  noticiaUpdate.setEmLocal(noticia.getEmLocal());
				  noticiaUpdate.setDisponibilidade(noticia.getDisponibilidade());
				  noticiaUpdate.setImagens(noticia.getImagens());
				  noticiaUpdate.setTexto(noticia.getTexto());
				  repositoryNoticia.save(noticiaUpdate); 
				  return new ModelAndView(E_Redirect.URL_MAP_NOTICIA_LISTAR.get() + mensagem);
			  }
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("modelAttributeNoticia", noticia);
			mensagem = (noticia.getIdNoticia() == null) ? E_Mensagem.CADASTRO_ERRO.get() : E_Mensagem.UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_NOTICIA_CADASTRO.get() + mensagem);
		}
	}
	
	public ModelAndView updateStatus(Long idnoticia, String token) {
		if(!serviceUrl.verificar(idnoticia, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.UPDATE_SUCESSO.get();
		try {
			Optional<Noticia> optionalnoticia = repositoryNoticia.findById(idnoticia);
			Noticia noticia = optionalnoticia.get();
			noticia.setDisponibilidade(noticia.getDisponibilidade() == true ? false : true);
			repositoryNoticia.save(noticia);
			return new ModelAndView(E_Redirect.URL_MAP_NOTICIA_LISTAR.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_NOTICIA_LISTAR.get() + mensagem);
		}
	}
	
	public ModelAndView remover(Long idnoticia, String token) {
		if(!serviceUrl.verificar(idnoticia, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.DELETE_SUCESSO.get();
		try {
			repositoryNoticia.deleteById(idnoticia);
			return new ModelAndView(E_Redirect.URL_MAP_NOTICIA_LISTAR.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.DELETE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_NOTICIA_LISTAR.get() + mensagem);
		}
	}

	public ModelAndView editar(Long idnoticia, RedirectAttributes redirectAttributes, String token) {
		if(!serviceUrl.verificar(idnoticia, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		redirectAttributes.addFlashAttribute("modelAttributeNoticia", new Noticia(idnoticia));
		return new ModelAndView(E_Redirect.URL_MAP_NOTICIA_CADASTRO.get());
	}
}
