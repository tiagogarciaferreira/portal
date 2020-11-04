package br.srv.portal.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

@Service
public class ServiceUsuario {
	
	@Autowired
	private RepositoryUsuario repositoryUsuario;
	@Autowired
	private ServiceUrl serviceUrl;
	@Autowired
	private ServiceSession serviceSession;
	@Autowired
	private ServiceEmail serviceEmail;
	@Value("${dominio.base.url}")
	private String DOMINIO;
	
	public Usuario getUsuarioLogado() {
		return repositoryUsuario.findById(serviceSession.getIdUsuarioLogado()).get();
	}	
	
	public Page<Usuario> pesquisar(String busca, Pageable pageable){
		return (busca.isEmpty()) ? repositoryUsuario.findAll(pageable)	: repositoryUsuario.findByNomePage(busca, pageable);
	}
	
	public Page<Usuario> listar(){
		return repositoryUsuario.findAll(PageRequest.of(0, 10, Sort.by(Direction.ASC, "nomeCompleto")));
	}
	
	public ModelAndView salvar(Usuario usuario, RedirectAttributes redirectAttributes) {
		String mensagem = "", senha_txt = "";
		try {
			if(usuario.getIdUsuario() == null){
				senha_txt = new Password().gerar();
				usuario.setSenha(new Password().encoder(senha_txt));
				repositoryUsuario.save(usuario);
				emailConfirmarCadastro(usuario, senha_txt);
				mensagem = E_Mensagem.CADASTRO_SUCESSO.get();
				return new ModelAndView(E_Redirect.URL_MAP_USUARIO_CADASTRO.get() + mensagem);
			}else {
				Usuario usuarioExistente = repositoryUsuario.findById(usuario.getIdUsuario()).get();
				senha_txt = usuario.getSenha();
				usuario.setSenha((usuarioExistente.getSenha().equals(usuario.getSenha())) ? usuario.getSenha() : new Password().encoder(usuario.getSenha()));
				repositoryUsuario.save(usuario);
				mensagem = E_Mensagem.UPDATE_SUCESSO.get();
				return new ModelAndView(E_Redirect.URL_MAP_USUARIO_LISTAR.get() + mensagem);
			}
		} catch (DataIntegrityViolationException e) {
			mensagem = E_Mensagem.EMAIL_EXISTENTE.get();
			usuario.setSenha(senha_txt);
			redirectAttributes.addFlashAttribute("modelAttributeUsuario", usuario);
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_CADASTRO.get() + mensagem);
		} catch (Exception e) {
			mensagem = (usuario.getIdUsuario() == null) ? E_Mensagem.CADASTRO_ERRO.get() : E_Mensagem.UPDATE_ERRO.get();
			redirectAttributes.addFlashAttribute("modelAttributeUsuario", usuario);
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_CADASTRO.get() + mensagem);
		}
	}
	
	public ModelAndView updateStatus(Long idusuario, String token) {
		if (!serviceUrl.verificar(idusuario, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.UPDATE_SUCESSO.get();
		try {
			Optional<Usuario> optionalUsuario = repositoryUsuario.findById(idusuario);
			Usuario usuario = optionalUsuario.get();
			usuario.setStatus(usuario.getStatus() == true ? false : true);
			repositoryUsuario.save(usuario);
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_LISTAR.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_LISTAR.get() + mensagem);
		}
	}
	
	public ModelAndView remover(Long idusuario, String token) {
		if (!serviceUrl.verificar(idusuario, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.DELETE_SUCESSO.get();
		try {
			repositoryUsuario.deleteById(idusuario);
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_LISTAR.get() + mensagem);
		} catch (DataIntegrityViolationException e1) {
			mensagem = E_Mensagem.DELETE_ERRO_DEPENDENCIA.get();
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_LISTAR.get() + mensagem);
		} catch (Exception e2) {
			mensagem = E_Mensagem.DELETE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_LISTAR.get() + mensagem);
		}
	}
	
	public ModelAndView editar(Long idusuario, String token, RedirectAttributes redirectAttributes) {
		if (!serviceUrl.verificar(idusuario, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		Optional<Usuario> optionalUsuario = repositoryUsuario.findById(idusuario);
		redirectAttributes.addFlashAttribute("modelAttributeUsuario", optionalUsuario.get());
		return new ModelAndView(E_Redirect.URL_MAP_USUARIO_CADASTRO.get());
	}
	
	public void emailConfirmarCadastro(Usuario usuario, String senhaTxt) {
		UrlSolicitacao urlSolicitacao = new UrlSolicitacao(String.valueOf(usuario.getIdUsuario()),"", false);
		String urlDirecionar = DOMINIO + E_Solicitacao.VALIDA_CADASTRO_USUARIO.get() + new ServiceUrl().cifrar(urlSolicitacao);
		Email email = new Email(usuario.getEmail(), E_Email.CONFIRMACAO_CADASTRO.get(), HtmlUsuario.novoUsuario(urlDirecionar, usuario.getNomeCompleto(), senhaTxt, DOMINIO));
		serviceEmail.enviar(email);
	}

}
