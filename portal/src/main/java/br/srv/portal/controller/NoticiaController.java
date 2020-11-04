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
import br.srv.portal.model.Noticia;
import br.srv.portal.services.ServiceCategoria;
import br.srv.portal.services.ServiceImagem;
import br.srv.portal.services.ServiceNoticia;

@Controller
@RequestMapping(value = "/noticia")
public class NoticiaController {

	@Autowired
	private ServiceNoticia serviceNoticia;
	@Autowired
	private ServiceCategoria serviceCategoria;
	@Autowired
	private ServiceImagem serviceImagem;

	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	private String listar(Model model) {
		model.addAttribute("noticias_lista", serviceNoticia.listar());
		return E_Redirect.URL_NOTICIA_LISTAR.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrar")
	private String cadastrar(Model model, @ModelAttribute(name = "modelAttributeNoticia") Noticia noticia) {
		model.addAttribute("imagens_lista", serviceImagem.listar());
		model.addAttribute("categorias_lista", serviceCategoria.listar());
		model.addAttribute("noticia", serviceNoticia.getNoticia(noticia));
		return E_Redirect.URL_NOTICIA_CADASTRO.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pesquisar")
	private String pesquisar(Model model, @PageableDefault(size = 20, sort = {"idNoticia"}, direction = Direction.DESC) Pageable pageable, @RequestParam(value = "buscar_por", required = true) String buscar_por) {
		model.addAttribute("noticias_lista", serviceNoticia.pesquisar(buscar_por, pageable));
		model.addAttribute("buscar_por", buscar_por);
		return E_Redirect.URL_NOTICIA_LISTAR.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar-noticias")
	private String listarNoticias(Model model, @PageableDefault(page = 0, size = 9, sort = {"idNoticia"}, direction = Direction.DESC) Pageable pageable, @RequestParam(value = "buscar_por", required = true) Long buscar_por) {
		model.addAttribute("noticias_todas", serviceNoticia.listarTodas(buscar_por, pageable));
		model.addAttribute("categorias_lista", serviceCategoria.listar());
		model.addAttribute("buscar_por", buscar_por);
		return E_Redirect.URL_ALL_NOTICIA.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/leitura/{idnoticia}")
	private String leitura(Model model, @PathVariable(value = "idnoticia", required = true) Long idnoticia, @RequestParam(value = "token", required = true) String token) {
		return serviceNoticia.leitura(model, idnoticia, token);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	private ModelAndView salvar(Noticia noticia, RedirectAttributes redirectAttributes){
		return serviceNoticia.salvar(noticia, redirectAttributes);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/status/{idnoticia}")
	private ModelAndView status(@PathVariable(value = "idnoticia", required = true) Long idnoticia, @RequestParam(value = "token", required = true) String token) {
		return serviceNoticia.updateStatus(idnoticia, token);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/remover/{idnoticia}")
	private ModelAndView remover(@PathVariable(value = "idnoticia", required = true) Long idnoticia, @RequestParam(value = "token", required = true) String token) {
		return serviceNoticia.remover(idnoticia, token);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editar/{idnoticia}")
	private ModelAndView editar(@PathVariable(value = "idnoticia", required = true) Long idnoticia, RedirectAttributes redirectAttributes, @RequestParam(value = "token", required = true) String token) {
		return serviceNoticia.editar(idnoticia, redirectAttributes, token); 
	}

}
