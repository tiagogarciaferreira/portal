package br.srv.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.srv.portal.constant.E_Redirect;

@Controller
@RequestMapping(value = "/estagio")
public class EstagioController {

	@RequestMapping(method = RequestMethod.GET, value = "/visualizar")
	private String visualizar() {
		return E_Redirect.URL_ESTAGIO_FUNCIONAMENTO.get();
	}

}
