package br.srv.portal.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.srv.portal.constant.E_Redirect;
import br.srv.portal.services.ServiceImagem;

@Controller
@RequestMapping(value = "/imagem")
public class ImagemController {

	@Autowired
	private ServiceImagem serviceImagem;

	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	private String listar(Model model) {
		model.addAttribute("imagens_lista",	serviceImagem.listar());
		return E_Redirect.URL_IMAGEM.get();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/pesquisar")
	private String pesquisar(Model model, @PageableDefault(size = 20, sort = { "idImagem" }, direction = Direction.ASC) Pageable pageable, @RequestParam(value = "buscar_por", required = true) String buscar_por) {
		model.addAttribute("imagens_lista", serviceImagem.pesquisar(buscar_por, pageable));
		model.addAttribute("buscar_por", buscar_por);
		return E_Redirect.URL_IMAGEM.get();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	private ModelAndView salvar(@RequestParam("imagem") MultipartFile imagem, HttpServletRequest httpServletRequest) {
		return serviceImagem.salvar(imagem, httpServletRequest);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/remover")
	private ModelAndView remover(@RequestParam(value = "nameimagem", required = true) String nameimagem, HttpServletRequest httpServletRequest) {
		return serviceImagem.remover(nameimagem, httpServletRequest);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/download")
	private void download(HttpServletResponse httpServletResponse, @RequestParam(name = "namearquivo", required = true) String namearquivo, HttpServletRequest httpServletRequest) throws IOException {
		serviceImagem.download(httpServletResponse, namearquivo, httpServletRequest);
	}
}
