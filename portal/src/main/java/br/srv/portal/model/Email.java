package br.srv.portal.model;

public class Email {

	private String destinatario;
	private String assunto;
	private String mensagem;

	public Email(String destinatario, String assunto, String mensagem) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}
	
	public String getDestinatario() {
		return destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario.toLowerCase();
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
