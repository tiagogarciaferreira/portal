package br.srv.portal.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.srv.portal.model.Permissao;
import br.srv.portal.model.Usuario;
import br.srv.portal.model.UsuarioSpringSecurity;
import br.srv.portal.repository.RepositoryUsuario;

@Service
public class UsuarioDetailsService implements UserDetailsService{

	@Autowired
	private RepositoryUsuario repositoryUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repositoryUsuario.findByEmail(username);
		if(usuario == null) {
			return new UsuarioSpringSecurity(null, null, null, null, null);
		}
		else if(usuario.getStatus().booleanValue() == false) {
			return new UsuarioSpringSecurity(null, null, null, null, null);
		}
		
		return new UsuarioSpringSecurity(usuario.getIdUsuario(), usuario.getNomeCompleto(), usuario.getEmail(), usuario.getSenha(), getAuthorities(usuario));
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(Permissao permissao: usuario.getTipoAcesso().getPermissoes()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(permissao.getNome()));
		}
	    return grantedAuthorities;
	  }
}
