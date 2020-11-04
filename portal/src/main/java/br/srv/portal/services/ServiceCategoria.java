package br.srv.portal.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.srv.portal.constant.E_Mensagem;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.model.Categoria;
import br.srv.portal.repository.RepositoryCategoria;

@Service
public class ServiceCategoria {

	@Autowired
	private RepositoryCategoria repositoryCategoria;
	@Autowired
	private ServiceUrl serviceUrl;
	
	public Page<Categoria> listar(){
		return repositoryCategoria.findAll(PageRequest.of(0, 20, Sort.by(Direction.ASC, "nome")));
	}

	public Page<Categoria> pesquisar(String nomeBusca, Pageable pageable){
		return (nomeBusca.isEmpty()) ? repositoryCategoria.findAll(pageable) : repositoryCategoria.findByNomePage(nomeBusca, pageable);
	}
	
	public ModelAndView salvar(Categoria categoria, RedirectAttributes redirectAttributes){
		String mensagem = (categoria.getIdCategoria() == null) ? E_Mensagem.CADASTRO_SUCESSO.get() : E_Mensagem.UPDATE_SUCESSO.get();
    	try {
			repositoryCategoria.save(categoria);
			return new ModelAndView(E_Redirect.URL_MAP_CATEGORIA_LISTAR.get() + mensagem);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("modelAttributeCategoria", categoria);
			mensagem = (categoria.getIdCategoria() == null) ? E_Mensagem.CADASTRO_ERRO.get() : E_Mensagem.UPDATE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_CATEGORIA_LISTAR.get() + mensagem);
		}
	}
	
	public ModelAndView remover(Long idcategoria, String token){
		if(!serviceUrl.verificar(idcategoria, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		String mensagem = E_Mensagem.DELETE_SUCESSO.get();
		try {
			repositoryCategoria.deleteById(idcategoria);
			return new ModelAndView(E_Redirect.URL_MAP_CATEGORIA_LISTAR.get() + mensagem);
		}
		catch (DataIntegrityViolationException e1) {
			mensagem = E_Mensagem.DELETE_ERRO_DEPENDENCIA.get();
			return new ModelAndView(E_Redirect.URL_MAP_CATEGORIA_LISTAR.get() + mensagem);
		} 
		catch (Exception e2) {
			mensagem = E_Mensagem.DELETE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_CATEGORIA_LISTAR.get() + mensagem);
		}
	}
	
	public ModelAndView editar(Long idcategoria, RedirectAttributes redirectAttributes, String token) {
		if(!serviceUrl.verificar(idcategoria, token)) {
			return new ModelAndView(E_Redirect.URL_MAP_ERROR.get());
		}
		Optional<Categoria> optionalCategoria = repositoryCategoria.findById(idcategoria);
		redirectAttributes.addFlashAttribute("modelAttributeCategoria", optionalCategoria.get());
		return new ModelAndView(E_Redirect.URL_MAP_CATEGORIA_LISTAR.get());
	}
}
