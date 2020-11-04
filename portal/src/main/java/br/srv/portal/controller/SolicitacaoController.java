package br.srv.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.srv.portal.services.ServiceSolicitacao;

@Controller
@RequestMapping(value = "/solicitacao")
public class SolicitacaoController {

	@Autowired
	private ServiceSolicitacao serviceSolicitacao;

	@RequestMapping(method = RequestMethod.GET, value = "/valida-cadastro-usuario")
	private ModelAndView validaCadastroUsuario(Model model, @RequestParam(value = "id", required = true) String id) {
		return serviceSolicitacao.validaCadastroUsuario(model, id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/valida-update-senha")
	private ModelAndView validaUpdateSenha(Model model, @RequestParam(value = "id", required = true) String id, @RequestParam(value = "validade", required = true) String validade, RedirectAttributes redirectAttributes) {
		return serviceSolicitacao.validaUpdateSenha(model, id, validade, redirectAttributes);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/valida-cadastro-newsletter")
	private ModelAndView validaCadastroNewsLetter(Model model, @RequestParam(value = "id", required = true) String id) {
		return serviceSolicitacao.validaCadastroNewsLetter(model, id);	
	}

	@RequestMapping(method = RequestMethod.GET, value = "/remove-cadastro-newsletter")
	private ModelAndView removeCadastroNewsLetter(Model model,
			@RequestParam(value = "id", required = true) String id) {
		return serviceSolicitacao.removeCadastroNewsLetter(model, id);
	}

}
