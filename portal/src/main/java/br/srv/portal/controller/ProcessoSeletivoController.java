package br.srv.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.srv.portal.constant.E_Redirect;

@Controller
@RequestMapping(value = "/processo-seletivo")
public class ProcessoSeletivoController {

	@RequestMapping(method = RequestMethod.GET, value = "/visualizar")
	private String pageProcessoSeletivo() {
		return E_Redirect.URL_PROCESSO_SELETIVO.get();
	}

}
