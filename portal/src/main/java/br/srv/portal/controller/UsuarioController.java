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
import br.srv.portal.model.Usuario;
import br.srv.portal.services.ServiceTipoAcesso;
import br.srv.portal.services.ServiceUsuario;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	@Autowired
	private ServiceTipoAcesso serviceTipoAcesso;

	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	private String listar(Model model) {
		model.addAttribute("usuarios_lista", serviceUsuario.listar());
		return E_Redirect.URL_USUARIO_LISTAR.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrar")
	private String cadastrar(Model model, @ModelAttribute(name = "modelAttributeUsuario") Usuario usuario) {
		model.addAttribute("tipoAcessos_lista", serviceTipoAcesso.listar());
		model.addAttribute("usuario", usuario);
		return E_Redirect.URL_USUARIO_CADASTRO.get();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/pesquisar")
	private String pesquisar(Model model, @PageableDefault(size = 20, sort = { "nomeCompleto" }, direction = Direction.ASC) Pageable pageable, @RequestParam(value = "buscar_por", required = true) String buscar_por) {
		model.addAttribute("usuarios_lista", serviceUsuario.pesquisar(buscar_por, pageable));
		return E_Redirect.URL_USUARIO_LISTAR.get();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	private ModelAndView salvar(Usuario usuario, RedirectAttributes redirectAttributes) {
		return serviceUsuario.salvar(usuario, redirectAttributes);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/status/{idusuario}")
	private ModelAndView status(@PathVariable(value = "idusuario", required = true) Long idusuario, @RequestParam(value = "token", required = true) String token) {
		return serviceUsuario.updateStatus(idusuario, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/remover/{idusuario}")
	private ModelAndView remover(@PathVariable(value = "idusuario", required = true) Long idusuario, @RequestParam(value = "token", required = true) String token) {
		return serviceUsuario.remover(idusuario, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/editar/{idusuario}")
	private ModelAndView editar(@PathVariable(value = "idusuario", required = true) Long idusuario, RedirectAttributes redirectAttributes, @RequestParam(value = "token", required = true) String token) {
		return serviceUsuario.editar(idusuario, token, redirectAttributes);
	}
}
