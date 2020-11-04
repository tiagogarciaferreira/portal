package br.srv.portal.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDetailsService usuarioDetailsService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().requireCsrfProtectionMatcher(getCsrfRequestMatcher())
		.and()
			.authorizeRequests().antMatchers("/").permitAll()
		.and()
			.authorizeRequests().antMatchers("/publico/**").permitAll()
		.and()
			.authorizeRequests().antMatchers("/componente/**").permitAll()
		.and()
			.authorizeRequests().antMatchers("/senha/**").permitAll()
		.and()
			.authorizeRequests().antMatchers("/solicitacao/**").permitAll()
		.and()
			.authorizeRequests().antMatchers("/sobre/**").permitAll()
		.and()
			.authorizeRequests().antMatchers("/estagio/**").permitAll()
		.and()
			.authorizeRequests().antMatchers("/processo-seletivo/**").permitAll()
		.and()
			.authorizeRequests().antMatchers("/dashboard/home").hasAnyAuthority("ROLE_DASHBOARD")
		.and()
			.authorizeRequests()
				.antMatchers("/categoria/listar").hasAnyAuthority("ROLE_CATEGORIA_MENU")
				.antMatchers("/categoria/pesquisar").hasAnyAuthority("ROLE_CATEGORIA_LISTAR")
				.antMatchers("/categoria/salvar").hasAnyAuthority("ROLE_CATEGORIA_CADASTRO")
				.antMatchers("/categoria/remover/**").hasAnyAuthority("ROLE_CATEGORIA_DELETAR")
				.antMatchers("/categoria/editar/**").hasAnyAuthority("ROLE_CATEGORIA_EDITAR")
		.and()
			.authorizeRequests()
				.antMatchers("/contato/cadastrar").permitAll()
				.antMatchers("/contato/listar").hasAnyAuthority("ROLE_CONTATO_MENU")
				.antMatchers("/contato/pesquisar").hasAnyAuthority("ROLE_CONTATO_LISTAR")
				.antMatchers("/contato/salvar").permitAll()
				.antMatchers("/contato/status/**").hasAnyAuthority("ROLE_CONTATO_EDITAR")
				.antMatchers("/contato/remover/**").hasAnyAuthority("ROLE_CONTATO_DELETAR")
		.and()
			.authorizeRequests()
				.antMatchers("/imagem/listar").hasAnyAuthority("ROLE_IMAGEM_MENU")
				.antMatchers("/imagem/pesquisar").hasAnyAuthority("ROLE_IMAGEM_LISTAR")
				.antMatchers("/imagem/salvar").hasAnyAuthority("ROLE_IMAGEM_CADASTRO")
				.antMatchers("/imagem/remover").hasAnyAuthority("ROLE_IMAGEM_DELETAR")
				.antMatchers("/imagem/download").hasAnyAuthority("ROLE_IMAGEM_DOWNLOAD")
		.and()
			.authorizeRequests()
				.antMatchers("/newsletter/listar").hasAnyAuthority("ROLE_NEWSLETTER_MENU")
				.antMatchers("/newsletter/pesquisar").hasAnyAuthority("ROLE_NEWSLETTER_LISTAR")
				.antMatchers("/newsletter/salvar").permitAll()
				.antMatchers("/newsletter/status").hasAnyAuthority("ROLE_NEWSLETTER_EDITAR")
				.antMatchers("/newsletter/remover/**").hasAnyAuthority("ROLE_NEWSLETTER_DELETAR")
		.and()
			.authorizeRequests()
				.antMatchers("/noticia/listar").hasAnyAuthority("ROLE_NOTICIA_MENU")
				.antMatchers("/noticia/cadastrar").hasAnyAuthority("ROLE_NOTICIA_MENU")
				.antMatchers("/noticia/pesquisar").hasAnyAuthority("ROLE_NOTICIA_LISTAR")
				.antMatchers("/noticia/listar-noticias").permitAll()
				.antMatchers("/noticia/leitura/**").permitAll()
				.antMatchers("/noticia/salvar").hasAnyAuthority("ROLE_NOTICIA_CADASTRO")
				.antMatchers("/noticia/status/**").hasAnyAuthority("ROLE_NOTICIA_LIBERAR")
				.antMatchers("/noticia/remover/**").hasAnyAuthority("ROLE_NOTICIA_DELETAR")
				.antMatchers("/noticia/editar/**").hasAnyAuthority("ROLE_NOTICIA_EDITAR")
		.and()
			.authorizeRequests()
				.antMatchers("/perfil/visualizar").hasAnyAuthority("ROLE_PERFIL_MENU")
				.antMatchers("/perfil/update").hasAnyAuthority("ROLE_PERFIL_EDITAR")
				.antMatchers("/perfil/update-senha").hasAnyAuthority("ROLE_PERFIL_EDITAR_SENHA")
		.and()
			.authorizeRequests()
				.antMatchers("/usuario/listar").hasAnyAuthority("ROLE_USUARIO_MENU")
				.antMatchers("/usuario/cadastrar").hasAnyAuthority("ROLE_USUARIO_MENU")
				.antMatchers("/usuario/pesquisar").hasAnyAuthority("ROLE_USUARIO_LISTAR")
				.antMatchers("/usuario/salvar").hasAnyAuthority("ROLE_USUARIO_CADASTRO")
				.antMatchers("/usuario/status/**").hasAnyAuthority("ROLE_USUARIO_LIBERAR")
				.antMatchers("/usuario/remover/**").hasAnyAuthority("ROLE_USUARIO_DELETAR")
				.antMatchers("/usuario/editar/**").hasAnyAuthority("ROLE_USUARIO_EDITAR")
		.and()
			.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMINISTRACAO")
		.and()
		.formLogin()
			.passwordParameter("password")
			.usernameParameter("username")
			.loginPage("/publico/login?acesso=restrito")
			.loginProcessingUrl("/logar")
			.failureUrl("/publico/login?login=erro")
			.defaultSuccessUrl("/dashboard/home", true)
		.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutSuccessUrl("/publico/login?logout=sucesso")
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.enableSessionUrlRewriting(false)
			.sessionFixation()
			.migrateSession()
			.maximumSessions(1)
			.expiredUrl("/publico/login?session=expired");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
			.userDetailsService(usuarioDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/bootstrap/**");
		webSecurity.ignoring().antMatchers("/calendar/**");
		webSecurity.ignoring().antMatchers("/css/**");
		webSecurity.ignoring().antMatchers("/editor/**");
		webSecurity.ignoring().antMatchers("/img/**");
		webSecurity.ignoring().antMatchers("/jquery/**");
		webSecurity.ignoring().antMatchers("/js/**");
		webSecurity.ignoring().antMatchers("/mask/**");
		webSecurity.ignoring().antMatchers("/validation/**");
		webSecurity.ignoring().antMatchers("/images/noticias/**");
		webSecurity.ignoring().antMatchers("/select/**");
	}
	
	private RequestMatcher getCsrfRequestMatcher() {
		RequestMatcher csrfRequestMatcher = new RequestMatcher() {
		private AntPathRequestMatcher[] antPathRequestMatchers = {
			      new AntPathRequestMatcher("/senha/email"),
			      new AntPathRequestMatcher("/senha/update"),
			      new AntPathRequestMatcher("/contato/salvar"),
			      new AntPathRequestMatcher("/noticia/salvar"),
			      new AntPathRequestMatcher("/perfil/update"),
			      new AntPathRequestMatcher("/perfil/update-senha"),
			      new AntPathRequestMatcher("/usuario/salvar"),
			      new AntPathRequestMatcher("**/logar"),
		};
				
		@Override
		public boolean matches(HttpServletRequest request) {
			for (AntPathRequestMatcher antPath : antPathRequestMatchers) {
				if (antPath.matches(request)) { 
			    	  return true; 
				}
			}
			    return false;
		}
	};
		return csrfRequestMatcher;
	}
}
