package br.srv.portal.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSpringSecurity extends User{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	public UsuarioSpringSecurity(Long id, String nome, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.usuario = new Usuario(id, nome);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
