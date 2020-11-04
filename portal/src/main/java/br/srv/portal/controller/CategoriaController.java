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
import br.srv.portal.model.Categoria;
import br.srv.portal.services.ServiceCategoria;

@Controller
@RequestMapping(value = "/categoria")
public class CategoriaController {
	
	@Autowired
	private ServiceCategoria serviceCategoria;
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	private String listar(Model model, @ModelAttribute(name = "modelAttributeCategoria") Categoria categoria) {
		model.addAttribute("categorias_lista", serviceCategoria.listar());
		model.addAttribute("categoria", categoria);
		model.addAttribute("modal", (categoria.getIdCategoria() != null) ? true : false);
		return E_Redirect.URL_CATEGORIA_PESQUISA.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pesquisar")
	private String pesquisar(Model model, @PageableDefault(size = 20, sort = {"nome"}, direction = Direction.ASC) Pageable pageable, @RequestParam(value = "buscar_por", required = true) String buscar_por) {
		model.addAttribute("categorias_lista", serviceCategoria.pesquisar(buscar_por, pageable));
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("buscar_por", buscar_por);
		return E_Redirect.URL_CATEGORIA_PESQUISA.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ModelAndView salvar(Categoria categoria, RedirectAttributes redirectAttributes){
		return serviceCategoria.salvar(categoria, redirectAttributes);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/remover/{idcategoria}")
	private ModelAndView remover(@PathVariable(value = "idcategoria", required = true) Long idcategoria, @RequestParam(value = "token", required = true) String token){
		return serviceCategoria.remover(idcategoria, token);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editar/{idcategoria}")
	private ModelAndView editar(@PathVariable(value = "idcategoria", required = true) Long idcategoria, RedirectAttributes redirectAttributes, @RequestParam(value = "token", required = true) String token) {
		return serviceCategoria.editar(idcategoria, redirectAttributes, token);
	}

}
