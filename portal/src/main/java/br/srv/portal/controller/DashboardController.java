package br.srv.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.srv.portal.constant.E_Redirect;
import br.srv.portal.services.ServiceDashboard;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

	@Autowired
	private ServiceDashboard serviceDashboard;

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	private String page(Model model) {
		model.addAttribute("homeInfo", serviceDashboard.getHomeInfo());
		return E_Redirect.URL_DASHBOARD.get();
	}

}
