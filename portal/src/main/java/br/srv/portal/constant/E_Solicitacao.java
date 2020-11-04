package br.srv.portal.constant;

public enum E_Solicitacao {

	VALIDA_CADASTRO_USUARIO("/solicitacao/valida-cadastro-usuario"), 
	VALIDA_UPDATE_SENHA("/solicitacao/valida-update-senha"), 
	VALIDA_CADASTRO_NEWSLETTER("/solicitacao/valida-cadastro-newsletter"),
	REMOVE_CADASTRO_NEWSLETTER("/solicitacao/remove-cadastro-newsletter"),
	LER_NOTICIA("/noticia/leitura/");

	private String value;

	private E_Solicitacao(String string) {
		this.value = string;
	}

	public String get() {
		return value;
	}

}
