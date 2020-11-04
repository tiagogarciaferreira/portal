package br.srv.portal.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.srv.portal.constant.E_Redirect;

@Controller
public class TypeErrorController implements ErrorController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/error")
	public String handleError(Model model, HttpServletRequest httpServletRequest) {
		Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			model.addAttribute("code", statusCode);
			return (statusCode == HttpStatus.NOT_FOUND.value() || statusCode == HttpStatus.BAD_REQUEST.value() || statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) ? E_Redirect.URL_ERRO.get() : "";
		}
		model.addAttribute("code", 400);
		return E_Redirect.URL_ERRO.get();
	}

	@Override
	public String getErrorPath() {
		return E_Redirect.URL_MAP_ERROR.get();
	}

}
