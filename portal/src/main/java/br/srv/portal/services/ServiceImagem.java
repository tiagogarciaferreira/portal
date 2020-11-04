package br.srv.portal.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.amazonaws.services.s3.model.PutObjectRequest;
import br.srv.portal.constant.E_Mensagem;
import br.srv.portal.constant.E_Redirect;
import br.srv.portal.model.Imagem;
import br.srv.portal.repository.RepositoryImagem;
import br.srv.portal.useful.Data;
import br.srv.portal.useful.Hora;

@Service
public class ServiceImagem {

	@Autowired 
	private RepositoryImagem repositoryImagem;
	private PutObjectRequest objectRequest = null;
	@Autowired
	private ServiceAmazonS3 serviceAmazonS3;
	
	public Page<Imagem> listar(){
		return repositoryImagem.findAll(PageRequest.of(0, 500, Sort.by(Direction.DESC, "idImagem")));
	}
	
	public Page<Imagem> pesquisar(String dataBusca, Pageable pageable){
		return (dataBusca.isEmpty()) ? repositoryImagem.findAll(pageable) : repositoryImagem.findByDataNomePage(dataBusca.replaceAll("-", ""), pageable);
	}
	
	public ModelAndView salvar(MultipartFile imagem, HttpServletRequest httpServletRequest) {
		String mensagem = E_Mensagem.CADASTRO_SUCESSO.get();
		try {
			String caminho_upload = httpServletRequest.getServletContext().getRealPath("/images/noticias");
			upload(imagem, caminho_upload+File.separator);
			return new ModelAndView(E_Redirect.URL_MAP_IMAGEM_LISTAR.get() + mensagem);
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = E_Mensagem.CADASTRO_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_IMAGEM_LISTAR.get() + mensagem);
		}
	}
	
	public ModelAndView remover(String nameimagem, HttpServletRequest httpServletRequest) {
		String mensagem = E_Mensagem.DELETE_SUCESSO.get();
		try {
			String caminho_upload = httpServletRequest.getServletContext().getRealPath("/images/noticias/");
			Imagem imagem = repositoryImagem.findByNome(nameimagem);
			repositoryImagem.deleteById(imagem.getIdImagem());
			new File(caminho_upload + nameimagem).delete();
			return new ModelAndView(E_Redirect.URL_MAP_IMAGEM_LISTAR.get() + mensagem);
		} catch (DataIntegrityViolationException e1) {
			mensagem = E_Mensagem.DELETE_ERRO_DEPENDENCIA.get();
			return new ModelAndView(E_Redirect.URL_MAP_IMAGEM_LISTAR.get() + mensagem);
		} catch (Exception e2) {
			mensagem = E_Mensagem.DELETE_ERRO.get();
			return new ModelAndView(E_Redirect.URL_MAP_IMAGEM_LISTAR.get() + mensagem);
		}
	}
	
	public void download(HttpServletResponse httpServletResponse, String namearquivo, HttpServletRequest httpServletRequest) throws IOException {
		String caminho_upload = httpServletRequest.getServletContext().getRealPath("/images/noticias/");
		File file = new File(caminho_upload + namearquivo);
		httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
		bufferedOutputStream.write(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		bufferedOutputStream.flush();
	}

	public void upload(MultipartFile multipartFile, String diretorio) throws Exception{
		File file = new File(diretorio);
		if(!file.exists()) {
			file.mkdirs();
		}
		String nameArquivo = Data.atualFormatada().replaceAll("-", "") + Hora.atualFormatada().replaceAll(":", "") + multipartFile.getOriginalFilename();
		byte[] bytes = multipartFile.getBytes();
		Path path = Paths.get(diretorio + nameArquivo);
		Files.write(path, bytes);
		repositoryImagem.save(new Imagem(nameArquivo));
		serviceAmazonS3.upload(multipartFile, nameArquivo);
	}
	
}
