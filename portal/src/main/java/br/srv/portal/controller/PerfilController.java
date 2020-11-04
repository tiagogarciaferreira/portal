package br.srv.portal.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.srv.portal.constant.E_Redirect;
import br.srv.portal.model.Usuario;
import br.srv.portal.services.ServicePerfil;
import br.srv.portal.services.ServiceUsuario;

@Controller
@RequestMapping(value = "/perfil")
public class PerfilController {
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	@Autowired
	private ServicePerfil servicePerfil;
	
	@RequestMapping(method = RequestMethod.GET, value = "/visualizar")
	public String visualizar(Model model) {
		model.addAttribute("usuario", serviceUsuario.getUsuarioLogado());
		return E_Redirect.URL_PERFIL.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView update(Usuario usuario) {
		return servicePerfil.update(usuario);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update-senha")
	public ModelAndView updateSenha(HttpServletRequest httpServletRequest) {
		return servicePerfil.updateSenha(httpServletRequest);
	}
}
