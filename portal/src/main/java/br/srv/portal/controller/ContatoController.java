package br.srv.portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.srv.portal.constant.E_Redirect;
import br.srv.portal.model.Contato;
import br.srv.portal.services.ServiceContato;

@Controller
@RequestMapping(value = "/contato")
public class ContatoController {

	
	@Autowired
	private ServiceContato serviceContato;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrar")
	private String cadastrar(Model model, @ModelAttribute(name = "modelAttributeContato") Contato contatoRedirect ) {
		model.addAttribute("contato", contatoRedirect);
		return E_Redirect.URL_CONTATO.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	private String listar(Model model) {
		model.addAttribute("contatos_lista", serviceContato.listar());
		return E_Redirect.URL_CONTATO_LISTAR.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pesquisar")
	private String pesquisar(Model model, @PageableDefault(size = 20, sort = {"respondido"}, direction = Direction.ASC) Pageable pageable, @RequestParam(value = "buscar_por", required = true) String buscar_por) {
		model.addAttribute("contatos_lista", serviceContato.pesquisar(buscar_por, pageable));
		model.addAttribute("buscar_por", buscar_por);
		return E_Redirect.URL_CONTATO_LISTAR.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	private ModelAndView salvar(Contato contato, RedirectAttributes redirectAttributes) {
		return serviceContato.salvar(contato, redirectAttributes);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/status/{idcontato}")
	private ModelAndView status(@PathVariable(value = "idcontato", required = true) Long idcontato, @RequestParam(value = "token", required = true) String token) {
		return serviceContato.updateStatus(idcontato, token);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/remover/{idcontato}")
	private ModelAndView remover(@PathVariable(value = "idcontato", required = true) Long idcontato, @RequestParam(value = "token", required = true) String token) {
		return serviceContato.remover(idcontato, token);
	}
}
