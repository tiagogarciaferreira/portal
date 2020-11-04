package br.srv.portal.services;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.srv.portal.constant.E_Mensagem;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.encrypt.Password;
import br.srv.portal.model.Usuario;
import br.srv.portal.repository.RepositoryUsuario;


@Service
public class ServicePerfil {

	@Autowired
	private RepositoryUsuario repositoryUsuario;
	@Autowired
	private ServiceSession serviceSession;
	
	public ModelAndView update(Usuario usuario) {
		String mensagem = E_Mensagem.UPDATE_SUCESSO.get();
		try {
			Optional<Usuario> optionalUsuario = repositoryUsuario.findById(serviceSession.getIdUsuarioLogado());
			optionalUsuario.get().setNomeCompleto(usuario.getNomeCompleto());
			optionalUsuario.get().setTelefone(usuario.getTelefone());
			usuario = optionalUsuario.get();
			repositoryUsuario.save(usuario);
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_PERFIL.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_PERFIL.get() + mensagem);
		}
	}
	
	public ModelAndView updateSenha(HttpServletRequest httpServletRequest) {
		String mensagem = E_Mensagem.SENHA_UPDATE_SUCESSO.get();
		try {
			String senha_atual = httpServletRequest.getParameter("senha_atual");
			String nova_senha = httpServletRequest.getParameter("nova_senha");
			Optional<Usuario> optionalUsuario = repositoryUsuario.findById(serviceSession.getIdUsuarioLogado());
			Usuario usuario = optionalUsuario.get();
			if(new BCryptPasswordEncoder().matches(senha_atual, usuario.getSenha())) {
				usuario.setSenha(new Password().encoder(nova_senha));
				repositoryUsuario.save(usuario);
				return new ModelAndView(E_Redirect.URL_MAP_USUARIO_PERFIL.get() + mensagem);
			}
			mensagem = E_Mensagem.SENHA_UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_PERFIL.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.SENHA_UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_USUARIO_PERFIL.get() + mensagem);
		}
	}
	
}
