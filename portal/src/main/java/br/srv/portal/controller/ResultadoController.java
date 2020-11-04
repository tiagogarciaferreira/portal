package br.srv.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.srv.portal.constant.E_Redirect;

@Controller
@RequestMapping(value = "/resultado")
public class ResultadoController {

	@RequestMapping(method = RequestMethod.GET, value = "/visualizar")
	private String page() {
		return E_Redirect.URL_RESULTADO.get();
	}
}
