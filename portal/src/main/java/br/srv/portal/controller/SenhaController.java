package br.srv.portal.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.srv.portal.constant.E_Redirect;
import br.srv.portal.encrypt.Sha;
import br.srv.portal.services.ServiceSenha;

@Controller
@RequestMapping(value = "/senha")
public class SenhaController {
	
	@Autowired
	private ServiceSenha serviceSenha;
	
	@RequestMapping(method = RequestMethod.GET, value = "/redefinir-senha")
	public String redefinirSenha(Model model, @ModelAttribute(name = "modelAttributeIdUsuario") String idUsuario) {
		model.addAttribute("user", idUsuario);
		model.addAttribute("token", Sha.getSha(Long.valueOf(idUsuario)));
		return E_Redirect.URL_REDEFINIR_SENHA.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/email-redefinir-senha")
	public String emailRedefinirSenha() {
		return E_Redirect.URL_EMAIL_REDEFINIR_SENHA.get();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/email")
	private ModelAndView email(HttpServletRequest httpServletRequest) {
		return serviceSenha.email(httpServletRequest);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	private ModelAndView update(HttpServletRequest httpServletRequest) {
		return serviceSenha.update(httpServletRequest);
	}
	
}
