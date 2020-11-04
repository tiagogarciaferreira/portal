package br.srv.portal.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.srv.portal.constant.E_Mensagem;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.model.Contato;
import br.srv.portal.repository.RepositoryContato;

@Service
public class ServiceContato {

	@Autowired
	private RepositoryContato repositoryContato;
	@Autowired
	private ServiceUrl serviceUrl;
	
	public Page<Contato> listar(){
		return repositoryContato.findAll(PageRequest.of(0, 10, Sort.by(Direction.ASC, "respondido")));
	}
	
	public Page<Contato> pesquisar(String nomBusca, Pageable pageable){
		return (nomBusca.isEmpty()) ? repositoryContato.findAll(pageable) : repositoryContato.findByNomePage(nomBusca, pageable);
	}
	
	public ModelAndView salvar(Contato contato, RedirectAttributes redirectAttributes) {
		String mensagem = E_Mensagem.CADASTRO_SUCESSO.get();
		try {
			contato.setRespondido(false);
			repositoryContato.save(contato);
			return new ModelAndView(E_Redirect.URL_MAP_CONTATO.get() + mensagem);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("modelAttributeContato", contato);
			mensagem = E_Mensagem.CADASTRO_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_CONTATO.get() + mensagem);
		}
	}
	
	public ModelAndView updateStatus(Long idcontato, String token) {
		if(!serviceUrl.verificar(idcontato, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.UPDATE_SUCESSO.get();
		try {
			Optional<Contato> optionalContato = repositoryContato.findById(idcontato);
			Contato contato = optionalContato.get();
			contato.setRespondido((contato.getRespondido() == true) ? false : true);
			repositoryContato.save(contato);
			return new ModelAndView(E_Redirect.URL_MAP_CONTATO_LISTAR.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_CONTATO_LISTAR.get() + mensagem);
		}
	}
	
	public ModelAndView remover(Long idcontato, String token) {
		if(!serviceUrl.verificar(idcontato, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.DELETE_SUCESSO.get();
		try {
			repositoryContato.deleteById(idcontato);
			return new ModelAndView(E_Redirect.URL_MAP_CONTATO_LISTAR.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.DELETE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_CONTATO_LISTAR.get() + mensagem);
		}
	}
	
}
