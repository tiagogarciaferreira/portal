package br.srv.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.model.Newsletter;
import br.srv.portal.services.ServiceNoticia;

@Controller
public class IndexController {
	
	@Autowired
	private ServiceNoticia serviceNoticia;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String page(Model model, @ModelAttribute(name = "modelAttributeNewsletter") Newsletter newsletter ) {
		model.addAttribute("noticias_index", serviceNoticia.listaIndex());
		model.addAttribute("newsletter", newsletter); 
		return E_Redirect.INDEX.get();
	}

}
