package br.srv.portal.services;

import java.io.Serializable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.srv.portal.model.UsuarioSpringSecurity;

@Service
public class ServiceSession implements Serializable {

	private static final long serialVersionUID = 1L;

	public Long getIdUsuarioLogado() {
		return getUserLogado().getUsuario().getIdUsuario();
	}

	private String getPermissoes() {
		Authentication authentication = getAuthentication();
		String permissoes = authentication.getAuthorities().toString();
		return permissoes;
	}

	private UsuarioSpringSecurity getUserLogado() {
		return (UsuarioSpringSecurity) getAuthentication().getPrincipal();
	}

	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
