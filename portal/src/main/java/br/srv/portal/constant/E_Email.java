package br.srv.portal.constant;

public enum E_Email{

	CONFIRMACAO_CADASTRO("Confirmação de Cadastro de Usuário"),
	REDEFINICAO_SENHA("Redefinição de Senha"),
	CADASTRO_NEWSLETTER("Confirmação de Cadastro Newsletter"),
	CANCELAMENTO_CADASTRO_NEWSLETTER("Cancelamento de Cadastro Newsletter"),
	NOVA_NEWSLETTER("Newsletter");

	private String value;

	E_Email(String value) {
		this.value = value;
	}

	public String get() {
		return value;
	}
}

