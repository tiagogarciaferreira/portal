package br.srv.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.srv.portal.constant.E_Redirect;

@Controller
@RequestMapping(value = "/publico")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	private String login() {
		return E_Redirect.URL_LOGIN.get();
	}

}
