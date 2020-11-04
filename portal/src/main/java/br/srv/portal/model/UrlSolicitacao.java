package br.srv.portal.model;

public class UrlSolicitacao {

	private String id;
	private String validade;
	private Boolean statusOK;

	public UrlSolicitacao(String id, String validade, Boolean statusOK) {
		this.id = id;
		this.validade = validade;
		this.statusOK = statusOK;
	}
	
	public Boolean getStatusOK() {
		return statusOK;
	}

	public void setStatusOK(Boolean statusOK) {
		this.statusOK = statusOK;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

}
