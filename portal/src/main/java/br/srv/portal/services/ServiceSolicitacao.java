package br.srv.portal.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.srv.portal.constant.E_Mensagem;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.model.Newsletter;
import br.srv.portal.model.UrlSolicitacao;
import br.srv.portal.model.Usuario;
import br.srv.portal.repository.RepositoryNewsletter;
import br.srv.portal.repository.RepositoryUsuario;

@Service
public class ServiceSolicitacao {

	@Autowired
	private RepositoryUsuario repositoryUsuario;
	@Autowired
	private RepositoryNewsletter repositoryNewsletter;
	@Autowired
	private ServiceUrl serviceUrl;
	
	public ModelAndView validaCadastroUsuario(Model model, String id) {
		String mensagem = E_Mensagem.CADASTRO_CONFIRMADO.get();
		try {
			UrlSolicitacao urlSolicitacao = new UrlSolicitacao(id,"", false);
			urlSolicitacao = serviceUrl.validar(urlSolicitacao);
			Optional<Usuario> optionalUsuario = repositoryUsuario.findById(Long.valueOf(urlSolicitacao.getId()));
			Usuario usuario = optionalUsuario.get();
			if (!usuario.getStatus()) {
				usuario.setStatus(true);
				repositoryUsuario.save(usuario);
			}
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get() + mensagem);
			
		} catch (Exception e) {
			mensagem = E_Mensagem.CADASTRO_CONFIRMADO_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get() + mensagem);
		}
	}
	
	public ModelAndView validaUpdateSenha(Model model, String id, String validade, RedirectAttributes redirectAttributes) {

		String mensagem = E_Mensagem.SENHA_UPDATE_EXPIRADA.get();
		try {
			UrlSolicitacao urlSolicitacao = new UrlSolicitacao(id, validade, false);
			urlSolicitacao = serviceUrl.validar(urlSolicitacao);
			if(urlSolicitacao.getStatusOK()) {
				redirectAttributes.addFlashAttribute("modelAttributeIdUsuario", urlSolicitacao.getId());
				return new ModelAndView(E_Redirect.URL_MAP_REDEFINIR_SENHA.get());
			}else {
				return new ModelAndView(E_Redirect.URL_MAP_REDEFINIR_SENHA.get()+mensagem);
			}
			
		} catch (Exception e) {
			mensagem = E_Mensagem.SENHA_UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get()+mensagem);
		}
	}
	
	public ModelAndView validaCadastroNewsLetter(Model model, String id) {
		String mensagem = E_Mensagem.CADASTRO_CONFIRMADO.get();
		try {
			UrlSolicitacao urlSolicitacao = new UrlSolicitacao(id,"", false);
			urlSolicitacao = serviceUrl.validar(urlSolicitacao);
			Optional<Newsletter> optionalNewsletter = repositoryNewsletter.findById(Long.valueOf(urlSolicitacao.getId()));
			Newsletter newsletter = optionalNewsletter.get();
			if (!newsletter.isStatus()) {
				newsletter.setStatus(true);
				repositoryNewsletter.save(newsletter);
			}
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.CADASTRO_CONFIRMADO_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get() + mensagem);
		}		
	}

	public ModelAndView removeCadastroNewsLetter(Model model, String id) {
		String mensagem = E_Mensagem.NOTIFICACAO_CANCELADA.get();
		try {
			UrlSolicitacao urlSolicitacao = new UrlSolicitacao(id,"", false);
			urlSolicitacao = serviceUrl.validar(urlSolicitacao);
			repositoryNewsletter.deleteById(Long.valueOf(urlSolicitacao.getId()));
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.NOTIFICACAO_CANCELADA_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get() + mensagem);
		}
	}
	
}
