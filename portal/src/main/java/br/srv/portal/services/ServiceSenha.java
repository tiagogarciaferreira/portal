package br.srv.portal.services;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import br.srv.portal.constant.E_Email;
import br.srv.portal.constant.E_Mensagem;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.constant.E_Solicitacao;
import br.srv.portal.encrypt.Password;
import br.srv.portal.model.Email;
import br.srv.portal.model.UrlSolicitacao;
import br.srv.portal.model.Usuario;
import br.srv.portal.repository.RepositoryUsuario;
import br.srv.portal.template.HtmlUsuario;
import br.srv.portal.useful.Data;

@Service
public class ServiceSenha {

	@Autowired
	private RepositoryUsuario repositoryUsuario;
	@Autowired
	private ServiceUrl serviceUrl;
	@Autowired
	private ServiceEmail serviceEmail; 
	@Value("${dominio.base.url}")
	private String DOMINIO;
	
	public ModelAndView email(HttpServletRequest httpServletRequest) {
		String mensagem = E_Mensagem.EMAIL_ENVIADO.get();
		try {
			String email = httpServletRequest.getParameter("email").toLowerCase();
			Usuario usuario = repositoryUsuario.findByEmail(email);
			if(usuario != null) {
				if(usuario.getStatus() == true) {
					emailRedefinirSenha(usuario);
				}
				else{
					mensagem = E_Mensagem.EMAIL_INATIVO.get();
				}
			}else {
				mensagem = E_Mensagem.EMAIL_NAO_EXISTE.get();
			}
		} catch (Exception e) {
			mensagem = E_Mensagem.EMAIL_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_EMAIL_REDEFINIR_SENHA.get() + mensagem);
		}
		return new ModelAndView(E_Redirect.URL_MAP_EMAIL_REDEFINIR_SENHA.get() + mensagem);
	}
	
	public ModelAndView update(HttpServletRequest httpServletRequest) {
		String mensagem = E_Mensagem.SENHA_UPDATE_SUCESSO.get();
		try {
			String id = httpServletRequest.getParameter("user");
			String token = httpServletRequest.getParameter("token");
			if(!serviceUrl.verificar(Long.valueOf(id), token)) {
				return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
			}
			String senha = httpServletRequest.getParameter("nova_senha");
			Optional<Usuario> optionalUsuario = repositoryUsuario.findById(Long.valueOf(id));
			Usuario usuario = optionalUsuario.get();
			usuario.setSenha(new Password().encoder(senha));
			repositoryUsuario.save(usuario);
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.SENHA_UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_RESULTADO.get() + mensagem);
		}
	}
	
	public void emailRedefinirSenha(Usuario usuario) {
		UrlSolicitacao urlSolicitacao = new UrlSolicitacao(String.valueOf(usuario.getIdUsuario()), Data.atualFormatada(), false);
		String urlDirecionar = DOMINIO + E_Solicitacao.VALIDA_UPDATE_SENHA.get() + new ServiceUrl().cifrar(urlSolicitacao);
		Email email = new Email(usuario.getEmail(), E_Email.REDEFINICAO_SENHA.get(), HtmlUsuario.novaSenha(urlDirecionar, usuario.getNomeCompleto(), DOMINIO));
		serviceEmail.enviar(email);
	}

}
