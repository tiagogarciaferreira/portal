package br.srv.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.model.Newsletter;
import br.srv.portal.services.ServiceNewsletter;

@Controller
@RequestMapping(value = "/newsletter")
public class NewsletterController {
	
	@Autowired
	private ServiceNewsletter serviceNewsletter;
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	private String listar(Model model) {
		model.addAttribute("newsletters_lista", serviceNewsletter.listar());
		return E_Redirect.URL_NEWSLETTER.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pesquisar")
	private String pesquisar(Model model, @PageableDefault(size = 20, sort = {"nomeCompleto"}, direction = Direction.ASC) Pageable pageable, @RequestParam(value = "buscar_por", required = true) String buscar_por) {
		model.addAttribute("newsletters_lista", serviceNewsletter.pesquisar(buscar_por, pageable));
		model.addAttribute("buscar_por", buscar_por);
		return E_Redirect.URL_NEWSLETTER.get();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ModelAndView salvar(Newsletter newsletter, RedirectAttributes redirectAttributes) {
		return serviceNewsletter.salvar(newsletter, redirectAttributes);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/status/{idnewsletter}")
	private ModelAndView status(@PathVariable(value = "idnewsletter", required = true) Long idnewsletter, @RequestParam(value = "token", required = true) String token) {
		return serviceNewsletter.status(idnewsletter, token);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/remover/{idnewsletter}")
	private ModelAndView remover(@PathVariable(value = "idnewsletter", required = true) Long idnewsletter, @RequestParam(value = "token", required = true) String token) {
		return serviceNewsletter.remover(idnewsletter, token);
	}

}
