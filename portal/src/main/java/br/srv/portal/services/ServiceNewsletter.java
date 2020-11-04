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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.srv.portal.constant.E_Email;
import br.srv.portal.constant.E_Mensagem;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.constant.E_Solicitacao;
import br.srv.portal.model.Email;
import br.srv.portal.model.Newsletter;
import br.srv.portal.model.Noticia;
import br.srv.portal.model.UrlSolicitacao;
import br.srv.portal.repository.RepositoryNewsletter;
import br.srv.portal.repository.RepositoryNoticia;
import br.srv.portal.template.HtmlNewsletter;
import br.srv.portal.template.HtmlUsuario;
import br.srv.portal.useful.Data;

@Service
public class ServiceNewsletter {

	@Autowired
	private RepositoryNewsletter repositoryNewsletter;
	@Autowired
	private ServiceUrl serviceUrl;
	@Autowired
	private RepositoryNoticia repositoryNoticia;
	@Autowired
	private ServiceEmail serviceEmail;
	private StringBuilder cabecalhoHtml;
	@Value("${dominio.base.url}")
	private String DOMINIO;
	
	public Page<Newsletter> listar(){
		return repositoryNewsletter.findAll(PageRequest.of(0, 20, Sort.by(Direction.ASC, "nomeCompleto")));
	}
	
	public Page<Newsletter> pesquisar(String emailBusca, Pageable pageable){
		return (emailBusca.isEmpty()) ? repositoryNewsletter.findAll(pageable) : repositoryNewsletter.findByEmailPage(emailBusca, pageable);
	}
	
	public ModelAndView salvar(Newsletter newsletter, RedirectAttributes redirectAttributes) {
		String mensagem = E_Mensagem.CADASTRO_SUCESSO_NEWSLETTER.get();
		try {
			Newsletter newsletterExistente = repositoryNewsletter.findByEmail(newsletter.getEmail());
			if(newsletterExistente == null){
				newsletter.setStatus(false);
				repositoryNewsletter.save(newsletter);
				newsletter = repositoryNewsletter.findByEmail(newsletter.getEmail());
				emailConfirmarCadastro(newsletter);
			}
			else {
				mensagem = E_Mensagem.EMAIL_EXISTENTE.get();
				redirectAttributes.addFlashAttribute("modelAttributeNewsletter", newsletter);
			}
			
		} catch (Exception e) {
			mensagem = E_Mensagem.CADASTRO_ERRO.get();
			redirectAttributes.addFlashAttribute("modelAttributeNewsletter", newsletter);
			return new ModelAndView(E_Redirect.INDEX_REDIRECT.get() + mensagem);
		}
		return new ModelAndView(E_Redirect.INDEX_REDIRECT.get() + mensagem);
	}
	
	public ModelAndView status(Long idnewsletter, String token) {
		if(!serviceUrl.verificar(idnewsletter, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.UPDATE_SUCESSO.get();
		try {
			Optional<Newsletter> optionalNewsletter = repositoryNewsletter.findById(idnewsletter);
			Newsletter newsletter = optionalNewsletter.get();
			newsletter.setStatus(newsletter.isStatus() == true ? false : true);
			repositoryNewsletter.save(newsletter);
			return new ModelAndView(E_Redirect.URL_MAP_NEWSLETTER_LISTAR.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_NEWSLETTER_LISTAR.get() + mensagem);
		}
	}
	
	public ModelAndView remover(Long idnewsletter, String token) {
		if(!serviceUrl.verificar(idnewsletter, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.DELETE_SUCESSO.get();
		try {
			repositoryNewsletter.deleteById(idnewsletter);
			return new ModelAndView(E_Redirect.URL_MAP_NEWSLETTER_LISTAR.get() + mensagem);
		} catch (Exception e) {
			mensagem = E_Mensagem.DELETE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_NEWSLETTER_LISTAR.get() + mensagem);
		}
	}
	
	public void emailConfirmarCadastro(Newsletter newsletter) {
		UrlSolicitacao urlSolicitacao = new UrlSolicitacao(String.valueOf(newsletter.getIdNewsletter()),"", false);
		String urlDirecionar = DOMINIO + E_Solicitacao.VALIDA_CADASTRO_NEWSLETTER.get() + new ServiceUrl().cifrar(urlSolicitacao);
		Email email = new Email(newsletter.getEmail(), E_Email.CADASTRO_NEWSLETTER.get(), HtmlUsuario.novoUsuarioNewsletter(urlDirecionar, newsletter.getNomeCompleto(), DOMINIO));
		serviceEmail.enviar(email);
	}
	
	public void emailNovaNewsletter() {
		List<Newsletter> destinatarios = repositoryNewsletter.findByStatus(true);
		List<Noticia> noticias = repositoryNoticia.findByDataCadastro(Data.getAtual());
		if(!destinatarios.isEmpty() && !noticias.isEmpty()) {
			String htmlEmailNoticias = HtmlNewsletter.novaNewsletter(noticias, DOMINIO);
			for (Newsletter destinatario: destinatarios) {
				UrlSolicitacao urlCancelarNewslleter = new UrlSolicitacao(String.valueOf(destinatario.getIdNewsletter()), "", false);
				cabecalhoHtml = new StringBuilder();
				cabecalhoHtml.append("<a href=\""+DOMINIO+E_Solicitacao.REMOVE_CADASTRO_NEWSLETTER.get()+new ServiceUrl().cifrar(urlCancelarNewslleter)+"\">Não quero mais receber estes emails</a>");
				cabecalhoHtml.append("<hr>");
				cabecalhoHtml.append("<footer style=\"color: black;font-size:10px;\">3E Gestão de Pessoas - Não responda este email.</footer>");
				cabecalhoHtml.append("</div>");
				cabecalhoHtml.append("<hr>");
				Email email = new Email(destinatario.getEmail(), E_Email.NOVA_NEWSLETTER.get(), htmlEmailNoticias + cabecalhoHtml.toString());
				serviceEmail.enviar(email);
			}
		}
	}

}
