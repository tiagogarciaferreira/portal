package br.srv.portal.model;

import java.io.Serializable;

public class HomeInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long numeroNoticia;
	private Long numeroCategoria;
	private Long numeroContato;
	private Long numeroUsuario;
	private Long numeroNewsLetter;
	private Long numeroImagem;
	
	public HomeInfo() {
	}
	
	public Long getNumeroNoticia() {
		return numeroNoticia;
	}
	public void setNumeroNoticia(Long numeroNoticia) {
		this.numeroNoticia = numeroNoticia;
	}
	public Long getNumeroCategoria() {
		return numeroCategoria;
	}
	public void setNumeroCategoria(Long numeroCategoria) {
		this.numeroCategoria = numeroCategoria;
	}
	public Long getNumeroContato() {
		return numeroContato;
	}
	public void setNumeroContato(Long numeroContato) {
		this.numeroContato = numeroContato;
	}
	public Long getNumeroUsuario() {
		return numeroUsuario;
	}
	public void setNumeroUsuario(Long numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}
	public Long getNumeroNewsLetter() {
		return numeroNewsLetter;
	}
	public void setNumeroNewsLetter(Long numeroNewsLetter) {
		this.numeroNewsLetter = numeroNewsLetter;
	}
	public Long getNumeroImagem() {
		return numeroImagem;
	}
	public void setNumeroImagem(Long numeroImagem) {
		this.numeroImagem = numeroImagem;
	}
	
}
